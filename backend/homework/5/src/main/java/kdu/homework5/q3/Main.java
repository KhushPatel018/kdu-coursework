package kdu.homework5.q3;

import kdu.homework5.logging.LoggingSystem;

public class Main {
    private static final LoggingSystem ls = new LoggingSystem();
    public static void main(String[] args) {

        int num = 7;

        Thread factorialThread = new Thread(new FactorialThread(num));
        Thread factorsThread = new Thread(new FactorThread(num));
        factorialThread.start();
        factorsThread.start();

        try {
            // Wait for both threads to finish
            factorialThread.join();
            factorsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Main thread finishes last
        ls.logInfo("Main thread finished.");
    }
}
