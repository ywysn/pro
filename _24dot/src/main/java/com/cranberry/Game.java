package com.cranberry;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    /**
     * randomNumber：存储四个随机数
     */
    static int[] randomNumber = new int[4];
    private static int[] temp = new int[4];
    private static double[] temp3 = new double[3];
    private static double[] temp2 = new double[2];
    private static double[] scard = new double[4];
    private static double sum;
    private static boolean isCorrect = false;
    private static char[] operator = {'+','-','*','/'};

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("***********24点**********");
        System.out.println("*****Are you ready******");
        randomNumber();
        System.out.print("四个随机值分别为：");
        print(randomNumber);
        System.out.println();
               System.out.println("所有运算值为24的结果:");
        if(getExpression() == null){
            System.out.println("没有正确结果");
        }else {
           ArrayList<String> res =  getExpression();
           for(String s : res){
               System.out.println(s);
           }
        }
        long end = System.currentTimeMillis();
        System.out.println("所消耗的时间为："+ (end-start) + "ms");
    }
    /**
     * 判断用户输入的表达式是否正确
     */
    private static void judge(String str){
        for(String s : getExpression()){
            if(str.equals(s)) {
                System.out.println("恭喜你，答对了");
                return;
            }
        }
        System.out.println("失败了");
    }
    /**
     * 生成四个随机数
     */
    public static void randomNumber(){
       Random random = new Random();
       for(int i = 0;i < randomNumber.length;i++){
           randomNumber[i] = random.nextInt(13)+1;
       }
   }


    /**
     * 两个数之间的基本运算
     */
    public static double  calcute(double a,double b,char operator){
        if(operator == '+'){
            return a+b;
        }else if(operator == '-'){
            return a-b;
        }else if(operator == '*'){
            return a*b;
        }else if(operator == '/' && b != 0){
            return a/b;
        }else {
            return -1;
        }
    }

    /**
     *四个数字的排列组合运算,共计24种
     */
    private static ArrayList<String> getExpression(){
        ArrayList<String> exp = new ArrayList<String>();
        for(int i = 0;i < 4;i++){
            for(int j = 0;j < 4;j++){
                if(j == i){
                    continue;
                }
                for(int m = 0;m < 4;m++){
                    if(m == i || m == j){
                        continue;
                    }
                    for(int n = 0;n < 4;n++){
                        if(n == i || n == j || n == m){
                            continue;
                        }
                        temp[i] = randomNumber[0];
                        temp[j] = randomNumber[1];
                        temp[m] = randomNumber[2];
                        temp[n] = randomNumber[3];
                        for(int k = 0;k < 4;k++){
                            scard[k] = (double)temp[k]%13;
                            if(temp[k]%13 == 0){
                                scard[k] = 13;
                            }
                        }
                        exp = search();
                        if(isCorrect){
                            isCorrect = false;
                            return exp;
                        }

                    }
                }
            }
        }
        return null;
    }

    /**
     * 4个数字进行排列组合，找到等于24的表达式
     * @return
     */
    private static ArrayList<String> search(){
        ArrayList<String> exp = new ArrayList<String>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {

                    /**
                     * 将前两个数字进行计算，并将计算结果及剩余数字保存到temp3中
                     * 此时缩短为3个数字
                     */
                    for (int m = 0; m < 3; m++) {
                        if (scard[m + 1] == 0 && operator[i] == '/') {
                            break;
                        }
                        temp3[m] = calcute(scard[m], scard[m + 1], operator[i]);

                        temp3[(m + 1) % 3] = scard[(m + 2) % 4];
                        temp3[(m + 2) % 3] = scard[(m + 3) % 4];

                        /**
                         * 将前两个数字进行计算，并将计算结果及剩余数字保存到temp2中
                         * 此时缩短为2个数字
                         */
                        for (int n = 0; n < 2; n++) {
                            if (temp3[n + 1] == 0 && operator[j] == '/') {
                                break;
                            }
                            temp2[n] = calcute(temp3[n], temp3[n + 1], operator[j]);
                            temp2[(n + 1) % 2] = temp3[(n + 2) % 3];

                            /**
                             * 将temp2中的两个数字进行计算，等到sum值
                             */
                            if (temp2[1] == 0 && operator[k] == '/') {
                                break;
                            }
                            sum = calcute(temp2[0], temp2[1], operator[k]);

                            /**
                             * 进行排列组合运算
                             */
                            if (sum == 24) {
                                isCorrect = true;
                                //expression存储表达式
                                String expression = "";
                                if (m == 0 && n == 0) {
                                    expression = "((" + (int) scard[0] + operator[i] + (int) scard[1] + ")"
                                            + operator[j] + (int) scard[2] + ")" + operator[k] + (int) scard[3] + "="
                                            + (int) sum;
                                } else if (m == 0 && n == 1) {
                                    expression = "(" + (int) scard[0] + operator[i] + (int) scard[1] + ")" + operator[k]
                                            + "(" + (int) scard[2] + operator[j] + (int) scard[3] + ")=" + (int) sum;
                                } else if (m == 1 && n == 0) {
                                    expression = "(" + (int) scard[0] + operator[j] + "(" + (int) scard[1] + operator[i]
                                            + (int) scard[2] + "))" + operator[k] + (int) scard[3] + "=" + (int) sum;
                                } else if (m == 2 && n == 0) {
                                    expression = "(" + (int) scard[0] + operator[j] + (int) scard[1] + ")" + operator[k]
                                            + "(" + (int) scard[2] + operator[i] + (int) scard[3] + ")=" + (int) sum;
                                } else if (m == 2 && n == 0) {
                                    expression = (int) scard[0] + operator[k] + "(" + (int) scard[1] + operator[j] + "("
                                            + (int) scard[2] + operator[i] + (int) scard[3] + "))=" + (int) sum;
                                }
                               // System.out.println(expression);
                                exp.add(expression);
                            }

                        }


                    }
                }
            }
        }
        return exp;
    }

    /**
     * 辅助打印函数
     */
   public static void print(int[] nums){
        for(int i : nums){
            System.out.print(i + " ");
        }
   }
    public static void print(double[] nums){
        for(double i : nums){
            System.out.print(i + " ");
        }
    }
}
