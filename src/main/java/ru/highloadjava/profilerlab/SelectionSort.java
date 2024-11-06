package ru.highloadjava.profilerlab;

import java.util.Random;

public class SelectionSort {
    public static void selectionSort(int[] arr) {
        // Алгоритм сортировки выбором
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Обмен значений
            Utils.swap(arr, minIndex, i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 100;
        int[] arr = new int[N];
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            arr[i] = random.nextInt(100);
            //Thread.sleep(800);;
        }
        Utils.printArray(arr);
        System.out.println();
        SelectionSort.selectionSort(arr);
        Utils.printArray(arr);
    }
}
