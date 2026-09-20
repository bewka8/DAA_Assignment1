package edu.aitu.daa.algorithms;

import edu.aitu.daa.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MergeSortTest {

    @Test
    void shouldSort100RandomArraysLikeArraysSort() {
        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(1_001);
            int[] actual = new int[size];

            for (int i = 0; i < size; i++) {
                actual[i] = random.nextInt(20_001) - 10_000;
            }

            int[] expected = actual.clone();
            Arrays.sort(expected);

            MergeSort.sort(actual, new Metrics());

            assertArrayEquals(expected, actual,
                    "Failed on random test number " + test);
        }
    }

    @Test
    void shouldHandleEmptyArray() {
        int[] array = {};

        assertDoesNotThrow(() -> MergeSort.sort(array, new Metrics()));
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void shouldHandleOneElement() {
        int[] array = {42};

        MergeSort.sort(array, new Metrics());

        assertArrayEquals(new int[]{42}, array);
    }

    @Test
    void shouldHandleAllEqualElements() {
        int[] array = {7, 7, 7, 7, 7, 7, 7};

        MergeSort.sort(array, new Metrics());

        assertArrayEquals(new int[]{7, 7, 7, 7, 7, 7, 7}, array);
    }

    @Test
    void shouldHandleAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};

        MergeSort.sort(array, new Metrics());

        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, array);
    }

    @Test
    void shouldHandleReverseSortedArray() {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        MergeSort.sort(array, new Metrics());

        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, array);
    }

    @Test
    void shouldCountComparisonsAndDepth() {
        int[] array = {5, 2, 8, 1, 9, 3};
        Metrics metrics = new Metrics();

        MergeSort.sort(array, metrics);

        assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, array);
        org.junit.jupiter.api.Assertions.assertTrue(metrics.getComparisons() > 0);
        org.junit.jupiter.api.Assertions.assertTrue(metrics.getMaxDepth() > 0);
    }
}