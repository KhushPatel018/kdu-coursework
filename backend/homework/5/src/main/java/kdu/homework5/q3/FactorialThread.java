package kdu.homework5.q3;

import kdu.homework5.logging.LoggingSystem;

public class FactorialThread implements Runnable{
    private static final LoggingSystem ls = new LoggingSystem();

    private int number;
    FactorialThread(int number)
    {
        this.number = number;
    }
    @Override
    public synchronized void run() {
        ls.logInfo("Factorial of " + number + " : " + calculateFactorial(number));
    }
    private int calculateFactorial(int num)
    {
        int result = 1;
        for(int i  = 2; i <= num;i++)
        {
            result *= i;
        }
        return result;
    }
}
