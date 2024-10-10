package ru.highloadjava.profilerlab;

import java.util.List;

public class Utils {
    public static void swap(int[] arr, int minIndex, int otherIndex) {
        int temp = arr[minIndex];
        arr[minIndex] = arr[otherIndex];
        arr[otherIndex] = temp;
    }

    public static int[] convertListToArray(List<Integer> list) {
        // Создаём массив нужного размера
        int[] array = new int[list.size()];

        // Копируем элементы из List в массив
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i); // Автоматическое распаковка Integer -> int
        }
        return array;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
