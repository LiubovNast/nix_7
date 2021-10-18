package tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class FiftyThreads {

    private static final int COUNT_THREADS = 50;
    private static final Logger LOG = LoggerFactory.getLogger(FiftyThreads.class);

    public void startApp() {
        LOG.info("Start task Fifty Threads");
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < COUNT_THREADS; i++) {
            Thread thread = new Thread(() -> System.out.println("Hello from thread " + Thread.currentThread().getName()));
            thread.setName(String.valueOf(i));
            threads.add(thread);
        }

        for (int i = COUNT_THREADS - 1; i >= 0; i--) {
            threads.get(i).start();
            try {
                threads.get(i).join();
            } catch (InterruptedException e) {
                LOG.error("Join throws exception {} in thread {}", e, Thread.currentThread().getName());
            }
        }
    }
}
