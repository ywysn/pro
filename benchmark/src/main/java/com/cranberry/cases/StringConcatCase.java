package main.java.com.cranberry.cases;

import main.java.com.cranberry.annotations.Benchmark;
import main.java.com.cranberry.annotations.Measurement;

@Measurement(iterations = 1000,group = 5)
public class StringConcatCase {
    private void doNothing(){

    }
    @Benchmark
    public String testStringAdd(){
        String s = "";
        for (int i = 0; i < 10;i++){
            s+=i;
        }
        return s;
    }
    @Benchmark
    public String testStringBuilderAdd(){
       StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10;i++){
            sb.append(i);
        }
        return sb.toString();
    }
}
