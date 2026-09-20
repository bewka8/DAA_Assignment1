package edu.aitu.daa.algorithms;

import edu.aitu.daa.metrics.Metrics;

public final class QuickSelect {
    private QuickSelect() {
    }

    public static int select(int[] array, int k, Metrics metrics) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }

        if (k < 0 || k >= array.length) {
            throw new IllegalArgumentException(
                    "k must be between 0 and " + (array.length - 1) + "."
            );
        }

        int left = 0;
        int right = array.length - 1;
        int depth = 1;

        while (left <= right) {
            metrics.updateDepth(depth++);

            int[] equalRange = Partition.threeWay(array, left, right, metrics);
            int equalStart = equalRange[0];
            int equalEnd = equalRange[1];

            if (k < equalStart) {
                right = equalStart - 1;
            } else if (k > equalEnd) {
                left = equalEnd + 1;
            } else {
                return array[k];
            }
        }

        throw new IllegalStateException("QuickSelect could not find the requested element.");
    }
}