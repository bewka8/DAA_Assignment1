package edu.aitu.daa.algorithms;

import edu.aitu.daa.metrics.Metrics;

import java.util.Random;

public final class Partition {
    private static final Random RANDOM = new Random();

    private Partition() {
    }

    public static int[] threeWay(int[] array, int left, int right, Metrics metrics) {
        int pivotIndex = left + RANDOM.nextInt(right - left + 1);
        int pivot = array[pivotIndex];

        int less = left;
        int current = left;
        int greater = right;

        while (current <= greater) {
            metrics.incrementComparisons();

            if (array[current] < pivot) {
                swap(array, less++, current++);
            } else {
                metrics.incrementComparisons();

                if (array[current] > pivot) {
                    swap(array, current, greater--);
                } else {
                    current++;
                }
            }
        }

        return new int[]{less, greater};
    }

    public static void swap(int[] array, int first, int second) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }
}