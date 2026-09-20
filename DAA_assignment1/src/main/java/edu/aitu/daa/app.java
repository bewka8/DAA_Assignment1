package edu.aitu.daa;

import edu.aitu.daa.algorithms.MergeSort;
import edu.aitu.daa.algorithms.QuickSelect;
import edu.aitu.daa.algorithms.QuickSort;
import edu.aitu.daa.metrics.Metrics;

import java.util.Arrays;

public class App {

    public static void main(String[] args) {
        int[] original = {8, 3, 5, 3, 9, 1, 7, 3, 2, 6, 4};

        System.out.println("Original array: " + Arrays.toString(original));
        System.out.println();

        runMergeSort(original);
        runQuickSort(original);
        runQuickSelect(original, 4);
    }

    private static void runMergeSort(int[] original) {
        int[] array = original.clone();
        Metrics metrics = new Metrics();

        long start = System.nanoTime();
        MergeSort.sort(array, metrics);
        long end = System.nanoTime();

        printResult("MergeSort", array, metrics, end - start);
    }

    private static void runQuickSort(int[] original) {
        int[] array = original.clone();
        Metrics metrics = new Metrics();

        long start = System.nanoTime();
        QuickSort.sort(array, metrics);
        long end = System.nanoTime();

        printResult("QuickSort", array, metrics, end - start);
    }

    private static void runQuickSelect(int[] original, int k) {
        int[] array = original.clone();
        Metrics metrics = new Metrics();

        long start = System.nanoTime();
        int result = QuickSelect.select(array, k, metrics);
        long end = System.nanoTime();

        System.out.println("QuickSelect");
        System.out.println("k = " + k);
        System.out.println("k-th smallest element: " + result);
        System.out.println("Comparisons: " + metrics.getComparisons());
        System.out.println("Maximum depth: " + metrics.getMaxDepth());
        System.out.printf("Time: %.4f ms%n%n", (end - start) / 1_000_000.0);
    }

    private static void printResult(
            String algorithm,
            int[] sortedArray,
            Metrics metrics,
            long timeNanos
    ) {
        System.out.println(algorithm);
        System.out.println("Sorted array: " + Arrays.toString(sortedArray));
        System.out.println("Comparisons: " + metrics.getComparisons());
        System.out.println("Maximum depth: " + metrics.getMaxDepth());
        System.out.printf("Time: %.4f ms%n%n", timeNanos / 1_000_000.0);
    }
}