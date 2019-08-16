package main.java.com.cranberry.cases;

import main.java.com.cranberry.annotations.Benchmark;
import main.java.com.cranberry.annotations.Measurement;
import org.junit.Test;

import java.util.Arrays;

@Measurement(iterations = 10,group = 3)
public class SortCases {
    @Benchmark
    @Measurement(iterations = 10,group = 3)
    public void Sort(){
        Arrays.sort(radom.generateRandomArray(100,0,10000));
        System.out.println("sdn");
    }
}

