package ru.highloadjava.profilerlab;

import java.util.Random;

public class RandomizedQuickSort {
    //Функция разбиения Ломуто
    public static int lomuto(int[] arr, int start, int end) {
        // Выбор случайного пивота
        Random random = new Random();
        int pivotIndex = random.nextInt(end - start + 1);
        int pivot = arr[pivotIndex];
        Utils.swap(arr, start, pivotIndex); // Меняем местами пивот и первый элемент
        int i = start + 1; // Начальная позиция для элементов, меньших пивота

        for (int j = start + 1; j < end + 1; j++) {
            if (arr[j] < pivot) {
                Utils.swap(arr, i, j); // Меняем местами элементы, меньшие пивота
                i++;
            }
        }
        Utils.swap(arr, start, i - 1); // Ставим пивот на своё место

        return i - 1; // Возвращаем индекс пивота
    }

    // Функция быстрой сортировки с рандомизацией
    public static void randomizedQuickSort(int[] arr, int start, int end) {
        if (start < end) {
            int splitIndex = lomuto(arr, start, end);
            randomizedQuickSort(arr, start, splitIndex - 1); // Сортируем левую часть
            randomizedQuickSort(arr, splitIndex + 1, end); // Сортируем правую часть
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 100;
        int[] arr = new int[N];
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            arr[i] = random.nextInt(100);
            Thread.sleep(800);;
        }
        Utils.printArray(arr);
        System.out.println();
        RandomizedQuickSort.randomizedQuickSort(arr, 0, arr.length - 1);
        Utils.printArray(arr);
    }

}
