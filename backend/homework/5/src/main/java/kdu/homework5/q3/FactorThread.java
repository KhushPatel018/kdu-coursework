package kdu.homework5.q3;

import kdu.homework5.logging.LoggingSystem;

import java.util.ArrayList;

public class FactorThread implements Runnable{
    private static final LoggingSystem ls = new LoggingSystem();
    private int number;
    FactorThread(int number)
    {
        this.number = number;
    }


    @Override
    public synchronized void run() {
        ls.logInfo("Factors of " + number + " ");
        ArrayList<Integer> factors = new ArrayList<>();
        for(int i = 1;i <= number;i++)
        {
            if(number % i == 0){
                factors.add(i);
            }
        }
        ls.logInfo(factors.toString());
    }


}
