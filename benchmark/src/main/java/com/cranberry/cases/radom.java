package main.java.com.cranberry.cases;

import java.util.Random;

public class radom {
    public static int[] generateRandomArray(int n,int rangeL,int rangR) {
        assert rangeL <= rangR;
        int[] arr = new int[n];
        for (int i = 0;i < n;i++) {
            arr[i] = new Integer((new Random().nextInt(rangR-rangeL+1)+rangeL));
        }
        return arr;
    }
}
