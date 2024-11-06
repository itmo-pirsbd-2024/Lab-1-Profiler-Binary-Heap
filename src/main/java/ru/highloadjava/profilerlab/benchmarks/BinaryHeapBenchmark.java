package ru.highloadjava.profilerlab.benchmarks;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.highloadjava.profilerlab.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 3 ,time = 300, timeUnit = TimeUnit.MILLISECONDS)
public class BinaryHeapBenchmark {


    @Param({"1000", "100000"})
    private int size;

    private List<Integer> list = new ArrayList<>();
    private Random random = new Random(1234L);

    private BinaryHeap binaryHeap = new BinaryHeap();

    private BinaryHeap expectedBinaryHeap = new BinaryHeap<>();
    private List<Integer> expectedList = new ArrayList<>();

    // Сортировки для сравнения
    private RandomizedQuickSort quickSort = new RandomizedQuickSort();
    private InsertionSort insertionSort = new InsertionSort();
    private SelectionSort selectionSort = new SelectionSort();

    private List<Integer> copyList = new ArrayList<>();
    private int[] array = new int[size];

    public static void main(String[]args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BinaryHeapBenchmark.class.getSimpleName())
                .forks(1)
                .jvmArgs("-Xms4G", "-Xmx8G", "-XX:+UnlockDiagnosticVMOptions",
                //"-XX:+PrintCompilation", "-XX:+PrintInlining",
                "-Djmh.stack.profiles=true")  // Включение профилирования стека  // Включение профилирования стека
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        list.clear();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(size));
            expectedBinaryHeap.adder(list.get(i));
        }
    }

    //@TearDown(Level.Iteration)
    //public void teardown() {
        //System.gc();
    //}

    // Бенчмарк для вставки в BinaryHeap
    @Benchmark
    public void addInBinaryHeap(Blackhole bh) {
        //BinaryHeap heap = new BinaryHeap();
        for (int i = 0; i < size; i++) {
            binaryHeap.adder(list.get(random.nextInt(size)));
        }
        bh.consume(binaryHeap);
    }

    // Бенчмарк для поиска в BinaryHeap
    @Benchmark
    public void searchInBinaryHeap(Blackhole bh) {
        //for (int i = 0; i < size; i++) {
        //    expectedBinaryHeap.contains(list.get(random.nextInt(size)));
        //}
        expectedBinaryHeap.contains(list.get(random.nextInt(size)));
        bh.consume(expectedBinaryHeap);
    }

    // Бенчмарк для вставки в ArrayList
    @Benchmark
    public void addInList(Blackhole bh) {
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            tempList.add(list.get(random.nextInt(size)));
        }
        bh.consume(tempList);
    }

    // Бенчмарк для поиска в ArrayList
    @Benchmark
    public void searchInList(Blackhole bh) {
        for (int i = 0; i < size; i++) {
            expectedList.add(list.get(random.nextInt(size)));
            expectedList.contains(list.get(random.nextInt(size)));
        }
        bh.consume(expectedList);
    }

    // Бенчмарк для heapSort
    @Benchmark
    public void heapSort(Blackhole bh) {
        BinaryHeap binaryHeap = new BinaryHeap();
        /*for (int i = 0; i < size; i++) {
            List<Integer> copyList = new ArrayList<>(list); // Создаем копию списка
            int[] array = Utils.convertListToArray(copyList); // Преобразование списка в массив ()
            binaryHeap.heapSort(copyList, copyList.size());
            bh.consume(copyList);
        }*/
        List<Integer> copyList = new ArrayList<>(list); // Создаем копию списка
        int[] array = Utils.convertListToArray(copyList); // Преобразование списка в массив ()
        binaryHeap.heapSort(copyList, copyList.size());
        bh.consume(copyList);
    }

    // Бенчмарк для RandomizedQuickSort
    @Benchmark
    public void benchmarkRandomizedQuickSort(Blackhole bh) {
        /*for (int i = 0; i < size; i++) {
            List<Integer> copyList = new ArrayList<>(list); // Создаем копию списка
            int[] array = Utils.convertListToArray(copyList); // Преобразование списка в массив
            quickSort.randomizedQuickSort(array, 0, array.length - 1);
            bh.consume(array);
        }*/
        List<Integer> copyList = new ArrayList<>(list); // Создаем копию списка
        int[] array = Utils.convertListToArray(copyList); // Преобразование списка в массив
        quickSort.randomizedQuickSort(array, 0, array.length - 1);
        bh.consume(array);
    }

    // Бенчмарк для InsertionSort
    @Benchmark
    public void benchmarkInsertionSort(Blackhole bh) {
        /*for (int i = 0; i < size; i++) {
            List<Integer> copyList = new ArrayList<>(list); // Создаем копию списка
            int[] array = Utils.convertListToArray(copyList); // Преобразование списка в массив
            insertionSort.insertionSort(array);
            bh.consume(array);
        }*/
        List<Integer> copyList = new ArrayList<>(list); // Создаем копию списка
        int[] array = Utils.convertListToArray(copyList); // Преобразование списка в массив
        insertionSort.insertionSort(array);
        bh.consume(array);
    }

    // Бенчмарк для SelectionSort
    @Benchmark
    public void benchmarkSelectionSort(Blackhole bh) {
        /*for (int i = 0; i < size; i++) {
            List<Integer> copyList = new ArrayList<>(list); // Создаем копию списка
            int[] array = Utils.convertListToArray(copyList); // Преобразование списка в массив
            selectionSort.selectionSort(array);
            bh.consume(array);
        }*/
        List<Integer> copyList = new ArrayList<>(list); // Создаем копию списка
        int[] array = Utils.convertListToArray(copyList); // Преобразование списка в массив
        selectionSort.selectionSort(array);
        bh.consume(array);
    }

}
