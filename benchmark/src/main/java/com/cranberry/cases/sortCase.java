package main.java.com.cranberry.cases;


import main.java.com.cranberry.annotations.Benchmark;
import main.java.com.cranberry.annotations.Case;
import main.java.com.cranberry.annotations.Measurement;
import org.junit.Test;

import java.util.Arrays;
//@Benchmark
//@Measurement(iterations = 10,group = 3)
public class sortCase implements Case {
   @Benchmark
    @Measurement(iterations = 8,group = 7)
   public void testString() {
       Arrays.sort(radom.generateRandomArray(1000,0,100000));
       //System.out.println("haha");
    }
    @Benchmark
    @Measurement(iterations = 10,group = 3)
    public void Sort(){
        Arrays.sort(radom.generateRandomArray(100,0,10000));
    }
}
//     private void quickSort(int[] a){
//         quickSortInternal(a,0,a.length-1);
//
//     }
//     private void quickSortInternal(int[] a ,int low,int high){
//         if(high <= low){
//             return;
//         }
//
//         int pivotIndex = parition(a,low,high);
//         quickSortInternal(a,low,pivotIndex);
//         quickSortInternal(a,pivotIndex+1,high);
//     }
//     private int parition(int[] a,int low ,int high){
//         int pivot = a[high];
//         int less = low;
//         int i = low;
//         int more  = high;
//         while (i < more){
//             if(a[i] == pivot){
//                 i++;
//             }else if(a[i] < pivot){
//                 swap(a[i],);
//             }
//         }
//
//     }
//     private void swap(int a,int b){
//         int temp = a;
//          a = b;
//          b = temp;
//     }

