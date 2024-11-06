package ru.highloadjava.profilerlab;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainBinaryHeap {
    public static void main(String[] args) throws InterruptedException {
        BinaryHeap binaryHeap = new BinaryHeap();
        List<Integer> lst = new ArrayList<>();
        int size = 100;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            //binaryHeap.adder(random.nextInt(size));
            lst.add(random.nextInt(size));
            //Thread.sleep(800);;
        }
        binaryHeap.heapSort(lst, lst.size());
        String res = lst.toString();
        //String res = binaryHeap.outInArray();
        System.out.println(res);
    }
}
