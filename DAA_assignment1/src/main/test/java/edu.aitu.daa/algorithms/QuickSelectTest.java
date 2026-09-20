package edu.aitu.daa.algorithms;

import edu.aitu.daa.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuickSelectTest {

    @Test
    void shouldFindKthSmallestElementOn100RandomArrays() {
        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(1_000) + 1;
            int[] array = new int[size];

            for (int i = 0; i < size; i++) {
                array[i] = random.nextInt(20_001) - 10_000;
            }

            int[] sorted = array.clone();
            Arrays.sort(sorted);

            int k = random.nextInt(size);
            int actual = QuickSelect.select(array.clone(), k, new Metrics());

            assertEquals(
                    sorted[k],
                    actual,
                    "Failed on random test %d with k = %d".formatted(test, k)
            );
        }
    }

    @Test
    void shouldFindSmallestElement() {
        int[] array = {8, 3, 5, 1, 9, 2};

        int result = QuickSelect.select(array, 0, new Metrics());

        assertEquals(1, result);
    }

    @Test
    void shouldFindLargestElement() {
        int[] array = {8, 3, 5, 1, 9, 2};

        int result = QuickSelect.select(array, array.length - 1, new Metrics());

        assertEquals(9, result);
    }

    @Test
    void shouldHandleDuplicateValues() {
        int[] array = {4, 2, 4, 1, 4, 3, 2};

        int result = QuickSelect.select(array, 4, new Metrics());

        assertEquals(4, result);
    }

    @Test
    void shouldHandleOneElement() {
        int[] array = {42};

        int result = QuickSelect.select(array, 0, new Metrics());

        assertEquals(42, result);
    }

    @Test
    void shouldThrowExceptionForEmptyArray() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{}, 0, new Metrics())
        );

        assertTrue(exception.getMessage().toLowerCase().contains("empty"));
    }

    @Test
    void shouldThrowExceptionForNegativeK() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{1, 2, 3}, -1, new Metrics())
        );

        assertTrue(exception.getMessage().contains("k"));
    }

    @Test
    void shouldThrowExceptionForKOutsideArrayRange() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{1, 2, 3}, 3, new Metrics())
        );

        assertTrue(exception.getMessage().contains("k"));
    }

    @Test
    void shouldCountComparisonsAndDepth() {
        int[] array = {5, 2, 8, 1, 9, 3};
        Metrics metrics = new Metrics();

        int result = QuickSelect.select(array, 2, metrics);

        assertEquals(3, result);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
    }
}