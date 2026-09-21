package edu.aitu.daa.algorithms;

import edu.aitu.daa.metrics.Metrics;

public final class QuickSort {
    private QuickSort() {
    }

    public static void sort(int[] array, Metrics metrics) {
        if (array == null) {
            throw new IllegalArgumentException("Array must not be null.");
        }

        sort(array, 0, array.length - 1, 1, metrics);
    }

    private static void sort(
            int[] array,
            int left,
            int right,
            int depth,
            Metrics metrics
    ) {
        while (left < right) {
            metrics.updateDepth(depth);

            int[] equalRange = Partition.threeWay(array, left, right, metrics);
            int lessEnd = equalRange[0] - 1;
            int greaterStart = equalRange[1] + 1;

            int leftPartSize = lessEnd - left + 1;
            int rightPartSize = right - greaterStart + 1;

            if (leftPartSize < rightPartSize) {
                sort(array, left, lessEnd, depth + 1, metrics);
                left = greaterStart;
            } else {
                sort(array, greaterStart, right, depth + 1, metrics);
                right = lessEnd;
            }
        }

        metrics.updateDepth(depth);
    }
}