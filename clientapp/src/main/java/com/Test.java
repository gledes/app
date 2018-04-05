package com;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jin on 2018/2/25.
 */

public class Test {
    public static void main(String args[]) {
        System.out.println("hello world");
        calculating(null);
    }

    public static void calculating(int [] arr) {
        int sum;
        int subtract;
        int multiply;
        int divide;

//        for (int i = 0; i < 4; i++) {
//
//        }
        List<Integer> list = new ArrayList<Integer>();
        list.add(2);
        list.add(2);
        list.add(10);
        compute(list, 2, new StringBuffer().append(2));
    }

    public static int compute(List<Integer> list, int curr, StringBuffer sb) {
        Integer next = list.get(0);
        if (next == null) {
            System.out.print(curr);
            return curr;
        }
        int sum = sum(curr, next);
        int subtract = subtract(curr, next);
        int multiply = multiply(curr, next);
        int divide = divide(curr, next);

        if (list.size() > 1) {
            List<Integer> subList = new ArrayList<Integer>();
            for (int i = 1; i < list.size(); i++) {
                subList.add(list.get(i));
            }
            StringBuffer sumSB = new StringBuffer(sb);
            StringBuffer subtractSB = new StringBuffer(sb);
            StringBuffer multiplySB = new StringBuffer(sb);
            StringBuffer divideSB = new StringBuffer(sb);
            compute(subList, sum, sumSB.append("+").append(next));
            compute(subList, subtract, subtractSB.append("-").append(next));
            compute(subList, multiply, multiplySB.append("*").append(next));
            compute(subList, divide, divideSB.append("/").append(next));
        } else {
            if (sum == 24) {
                System.out.println(sb.append("+").append(next));
            }
            if (subtract == 24) {
                System.out.println(sb.append("-").append(next));
            }
            if (multiply == 24) {
                System.out.println(sb.append("*").append(next));
            }
            if (divide == 24) {
                System.out.println(sb.append("/").append(next));
            }
        }
        return curr;
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b){
        return a / b;
    }
}
