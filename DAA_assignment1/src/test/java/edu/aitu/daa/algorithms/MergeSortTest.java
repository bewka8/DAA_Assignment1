package edu.aitu.daa.algorithms;

import edu.aitu.daa.metrics.Metrics;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
    @Test void sorts100RandomArrays() {
        Random random = new Random(42);
        for (int test = 0; test < 100; test++) {
            int[] actual = randomArray(random, random.nextInt(1_001));
            int[] expected = actual.clone();
            Arrays.sort(expected);
            MergeSort.sort(actual, new Metrics());
            assertArrayEquals(expected, actual, "Random case " + test);
        }
    }

    @Test void handlesRequiredEdgeCases() {
        assertSorted(new int[]{});
        assertSorted(new int[]{42});
        assertSorted(new int[]{7, 7, 7, 7});
        assertSorted(new int[]{1, 2, 3, 4, 5});
        assertSorted(new int[]{5, 4, 3, 2, 1});
    }

    @Test void collectsMetrics() {
        Metrics metrics = new Metrics();
        MergeSort.sort(new int[]{5, 1, 4, 2, 3}, metrics);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
    }

    private void assertSorted(int[] actual) {
        int[] expected = actual.clone(); Arrays.sort(expected);
        assertDoesNotThrow(() -> MergeSort.sort(actual, new Metrics()));
        assertArrayEquals(expected, actual);
    }
    private int[] randomArray(Random random, int size) { int[] a = new int[size]; for (int i = 0; i < size; i++) a[i] = random.nextInt(20_001) - 10_000; return a; }
}
