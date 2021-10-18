import tasks.FiftyThreads;
import tasks.PrimeNumbers;

public class MainThreads {

    public static void main(String[] args) {
        FiftyThreads fiftyThreads = new FiftyThreads();
        fiftyThreads.startApp();

        PrimeNumbers primeNumbers = new PrimeNumbers();
        primeNumbers.count();
    }
}
