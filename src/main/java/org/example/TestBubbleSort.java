package org.example;

import java.util.Arrays;

class TestBubbleSort {
    public static void main(String[] args) {
        int[] ar = new int[]{5, 6, 1,3};
        var t = new  TestBubbleSort();
        int[] ints = t.bubbleSort(ar);
        System.out.println(Arrays.toString(ints));
    }

    int[] bubbleSort(int[] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int i = 0; i < arr.length; i++) {
                if (i + 1 < arr.length) {
                    if (arr[i + 1] < arr[i]) {
                        swap(arr, i + 1, i);
                    }
                }
            }
        }
        return arr;
    }

    void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
