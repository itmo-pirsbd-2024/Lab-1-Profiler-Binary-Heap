package ru.highloadjava.profilerlab;

import java.util.*;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap = new ArrayList<>();

    public Comparator<? super T> comparator() {
        return null;
    }

    public BinaryHeap() {
        heap.clear();
    }

    public BinaryHeap(List<T> list) {
        heap.clear();
        heapFromArray(list, list.size());
        heap = new ArrayList<>(list);
        //Collections.copy(heap, list);

    }

    private static <T> int compare(T val1, T val2) {
        return ((Comparable<? super T>) val1).compareTo(val2);
    }

    public List<T> getHeap() {
        return heap;
    }

    public void adder(T x) {
        heap.add(x);
        bubbleUp(heap.size() - 1);
    }

    private void bubbleUp(int index) {
        int parentIndex = (index - 1) / 2;
        if (index > 0 && (heap.get(index).compareTo(heap.get(parentIndex)) < 0)) {
            swap(heap, parentIndex, index);
            bubbleUp(parentIndex);
        }
    }

    public T deleteMin() { //извлечение минимального элемента(находится в корне)
        T deleteValue = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        bubleDown(0); // поскольку при удалении корня нужно менять кучу, то для её восстановления напишем метод
        return deleteValue;
    }

    private void bubleDown(int index) {
        int x = 2 * index + 1;
        if (x < heap.size()) {
            if ((x + 1 < heap.size()) && (heap.get(x + 1).compareTo(heap.get(x)) < 0)) { //если ребёнок справа меньше
                x++;
            }
            if (heap.get(index).compareTo(heap.get(x)) > 0) { // если наш элемент больше потомка(правого или левого мы определили ранее), опускаем его
                swap(heap, index, x);
                bubleDown(x);
            }
        }
    }

    public void swap(List<T> list, int index, int x) { //менял static
        T temp = list.get(index);
        list.set(index, list.get(x));
        list.set(x, (T) temp);
    }

    public void sortOurHeap(List<T> list, int size, int i) { //упорядочивает кучу относительно i вершины //менял static
        int indexLeft = 2 * i + 1;
        int indexRight = 2 * i + 2;
        int indexOfMinimum = i;
        if ((indexLeft < size) && (compare(list.get(indexLeft), list.get(indexOfMinimum)) < 0)) {
            indexOfMinimum = indexLeft;
        }
        if ((indexRight < size) && (compare(list.get(indexRight), list.get(indexOfMinimum)) < 0)) {
            indexOfMinimum = indexRight;
        }

        if (indexOfMinimum != i) {
            swap(list, indexOfMinimum, i);
            sortOurHeap(list, size, indexOfMinimum);
        }
    }

    private void heapFromArray(List<T> list, int size) { //преобразуем массив в min-heap
        //int startIndex = list.size() / 2 - 1;
        for (int i = list.size() - 1; i >= 0; i--) {
            sortOurHeap(list, size, i);
        }
    }

    public void heapSort(List<T> list, int size) {
        heapFromArray(list, size);

        for (int i = list.size() - 1; i >= 0; i--) {
            swap(list, 0, i);//!!!
            sortOurHeap(list, i, 0);
        }
    }

    public void clearHeap() {
        heap.clear();
    }

    public T search(int index) {
        if (index >= heap.size()) throw new IndexOutOfBoundsException();
        return heap.get(index);
    }

    public boolean contains(T element) {
        if (heap.size() == 0) throw new IllegalStateException("Куча не заполнена, там ничего нет");
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i).compareTo(element) == 0) return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
    public String outInArray() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < heap.size(); i++) {
            stringBuilder.append(heap.get(i)).append(" ");
            //System.out.print(heap.get(i) + " ");
        }
        return stringBuilder.toString().trim();
    }

    public String outInHeap() {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 1;
        int i = 0;
        while (i < heap.size()) {
            while (i < index && i < heap.size()) {
                stringBuilder.append(heap.get(i)).append(" ");
                //System.out.print(heap.get(i) + " ");
                i++;
            }
            index = 2 * index + 1;
            stringBuilder.append("\n");
            //System.out.println();
        }
        return stringBuilder.toString().trim();
    }
}
