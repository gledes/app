package com.example;

public class BinarySearch {

    public static void main(String args[]) {
        System.out.println("你好");
        int [] array = {1, 2, 3, 5, 6, 7, 8, 13, 15, 20, 28};

        int result = binarySearch(array, 0, array.length, 28);
        System.out.println(result);


    }

    public static int binarySearch(int[] array,int fromIndex, int toIndex, int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high)/2;
            int midVal = array[mid];
            if (key > midVal) {
                low = mid + 1;
            } else if (key < midVal) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
