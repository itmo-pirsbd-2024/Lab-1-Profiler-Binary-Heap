package ru.highloadjava.profilerlab;

import java.util.Random;

public class InsertionSort {
    /* Function to sort array using insertion sort */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 100;
        int[] arr = new int[N];
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            arr[i] = random.nextInt(100);
            Thread.sleep(800);
        }
        Utils.printArray(arr);
        System.out.println();
        //Thread.sleep(10000);
        InsertionSort.insertionSort(arr);
        //Thread.sleep(10000);
        Utils.printArray(arr);
    }
}

