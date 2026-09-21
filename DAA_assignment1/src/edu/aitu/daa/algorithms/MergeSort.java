package edu.aitu.daa.algorithms;

import edu.aitu.daa.metrics.Metrics;

public final class MergeSort {
    private static final int CUTOFF = 15;

    private MergeSort() {
    }

    public static void sort(int[] array, Metrics metrics) {
        if (array == null) {
            throw new IllegalArgumentException("Array must not be null.");
        }

        if (array.length < 2) {
            return;
        }

        int[] buffer = new int[array.length]; // создаётся ровно один раз
        sort(array, buffer, 0, array.length - 1, 1, metrics);
    }

    private static void sort(
            int[] array,
            int[] buffer,
            int left,
            int right,
            int depth,
            Metrics metrics
    ) {
        metrics.updateDepth(depth);

        if (right - left + 1 <= CUTOFF) {
            insertionSort(array, left, right, metrics);
            return;
        }

        int middle = left + (right - left) / 2;

        sort(array, buffer, left, middle, depth + 1, metrics);
        sort(array, buffer, middle + 1, right, depth + 1, metrics);

        metrics.incrementComparisons();
        if (array[middle] <= array[middle + 1]) {
            return;
        }

        merge(array, buffer, left, middle, right, metrics);
    }

    private static void merge(
            int[] array,
            int[] buffer,
            int left,
            int middle,
            int right,
            Metrics metrics
    ) {
        System.arraycopy(array, left, buffer, left, right - left + 1);

        int i = left;
        int j = middle + 1;

        for (int k = left; k <= right; k++) {
            if (i > middle) {
                array[k] = buffer[j++];
            } else if (j > right) {
                array[k] = buffer[i++];
            } else {
                metrics.incrementComparisons();

                if (buffer[i] <= buffer[j]) {
                    array[k] = buffer[i++];
                } else {
                    array[k] = buffer[j++];
                }
            }
        }
    }

    private static void insertionSort(int[] array, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int value = array[i];
            int j = i - 1;

            while (j >= left) {
                metrics.incrementComparisons();

                if (array[j] <= value) {
                    break;
                }

                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = value;
        }
    }
}