package tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrimeNumbers {

    private static final Logger LOG = LoggerFactory.getLogger(PrimeNumbers.class);
    private volatile int count = 0;
    private volatile int index = 0;
    private static final int LIST_SIZE = 50;
    private Random random = new Random();

    public void count() {
        LOG.info("Start task Counting Prime");

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < LIST_SIZE; i++) {
            list.add(random.nextInt(LIST_SIZE));
        }

        for (int i = 0; i < 2; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    synchronized (this) {
                        if (index >= LIST_SIZE) break;
                        if (isPrime(list.get(index))) {
                            count++;
                        }
                        index++;
                    }
                }
            });
            thread.start();
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            LOG.error("InterruptedException {} in Thread {}", e.getCause(), Thread.currentThread().getName());
        }

        System.out.println(count);
    }

    private static boolean isPrime(int number) {
        if (number == 2) return true;
        if (number == 3) return true;
        if (number == 5) return true;
        if (number == 7) return true;
        return number % 2 != 0 && number % 3 != 0 && number % 5 != 0 && 7 != 0;
    }
}
