package kdu.homework4.q3;

import kdu.homework4.logging.LoggingSystem;


import java.util.Arrays;

public class Exchange{

    private static final LoggingSystem ls = new LoggingSystem();
    //generic method
    public static <T> void swap(int i, int j, T[] arr){
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args){
        Integer[] integers = {1,3,5,2,4};
        ls.logInfo("before swap : " + Arrays.toString(integers));
        swap(0,1,integers);
        ls.logInfo("after swap : " + Arrays.toString(integers));
    }
}
