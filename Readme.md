# Lab1 Profiler

В качестве задания была взята бинарная куча (BinaryHeap) и произведено ее сравнение с сортировками выбором, вставкой, 
рандомизированной сортировкой. 

Было осущестлено профилирование сортировок, написан бенчмарк для сравнения производительности
сортировки кучей и классических алгоритмов сортировки, осуществлено профилирование как бенчмарка, так и классов с алгоритмами сортировки,
построены flamegraph для классических алгоритмов сортировки и jmh.

В качестве инструмента профилирования был выбран async-profiler.

## Результаты бенчмарка сравнения алгоритма сортировки в куче с классическими алгоритмами
* Результаты бенчмарка сравнения алгоритмов сортировки и бинарной кучи

Как  мы видим наиболее хорошо с росто числа элементов чувствует себя RandomizedQuickSort и heapSort 
![compare_sort_algorithms.png](assets/compare_sort_algorithms.png)

Как  мы видим наиболее хорошо с росто числа элементов чувствует себя RandomizedQuickSort и heapSort


Также обратим внимание, что поиск элемента  в куче происходит быстрее.

## Результаты профилирования алгоритмов сортировок

Продемонстрируем flamegraph алгоритмов сортировок.

* Insertion sort flamegraph
![benchmark_flamegraph_insertionSort.png](assets/benchmark_flamegraph_insertionSort.png)


* Selection sort flamegraph
![benchmark_flamegraph_selectionSort.png](assets/benchmark_flamegraph_selectionSort.png)


* Randomized quick sort flamegraph
Виден рекурсивный характер алгоритма
![benchmark_flamegraph_randomizedQuickSort.png](assets/benchmark_flamegraph_randomizedQuickSort.png)


* Heap sort flamegraph
![benchmark_flamegraph_heapsort.png](assets/benchmark_flamegraph_heapsort.png)


## Динамический вывод JIT при профилировании JMH
Пример фрагмента вывода (видно как инициализируется объект)
![jmh_JIT_example.png](assets/jmh_JIT_example.png)