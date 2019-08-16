package main.java.com.cranberry.cases;

import main.java.com.cranberry.annotations.Benchmark;
import main.java.com.cranberry.annotations.Measurement;

@Measurement(iterations = 10,group = 5)
public class testString {
    @Benchmark
    @Measurement(iterations = 10,group = 5)
    public static void main(String[] args) {
        String str = "";
    }
}
