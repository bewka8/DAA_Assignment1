package edu.aitu.daa.algorithms;

import edu.aitu.daa.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuickSortTest {

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

            QuickSort.sort(actual, new Metrics());

            assertArrayEquals(expected, actual,
                    "Failed on random test number " + test);
        }
    }

    @Test
    void shouldHandleEmptyArray() {
        int[] array = {};

        assertDoesNotThrow(() -> QuickSort.sort(array, new Metrics()));
        assertArrayEquals(new int[]{}, array);
    }

    @Test
    void shouldHandleOneElement() {
        int[] array = {42};

        QuickSort.sort(array, new Metrics());

        assertArrayEquals(new int[]{42}, array);
    }

    @Test
    void shouldHandleAllEqualElements() {
        int[] array = {5, 5, 5, 5, 5, 5, 5, 5, 5};

        QuickSort.sort(array, new Metrics());

        assertArrayEquals(new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5}, array);
    }

    @Test
    void shouldHandleAlreadySortedArray() {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8};

        QuickSort.sort(array, new Metrics());

        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, array);
    }

    @Test
    void shouldHandleReverseSortedArray() {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};

        QuickSort.sort(array, new Metrics());

        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, array);
    }

    @Test
    void shouldHaveBoundedDepthOnSortedArrayOf100000Elements() {
        int size = 100_000;
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = i;
        }

        Metrics metrics = new Metrics();

        assertDoesNotThrow(() -> QuickSort.sort(array, metrics));

        double allowedDepth = 2 * (Math.log(size) / Math.log(2));

        assertTrue(
                metrics.getMaxDepth() <= allowedDepth,
                "Depth was %d but must be <= %.2f"
                        .formatted(metrics.getMaxDepth(), allowedDepth)
        );

        for (int i = 0; i < size - 1; i++) {
            assertTrue(array[i] <= array[i + 1]);
        }
    }

    @Test
    void shouldCountComparisonsAndDepth() {
        int[] array = {5, 2, 8, 1, 9, 3};
        Metrics metrics = new Metrics();

        QuickSort.sort(array, metrics);

        assertArrayEquals(new int[]{1, 2, 3, 5, 8, 9}, array);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
    }
}