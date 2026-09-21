package test.edu.aitu.daa.algorithms;

import edu.aitu.daa.metrics.Metrics;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {
    @Test void sorts100RandomArrays() {
        Random random = new Random(42);
        for (int test = 0; test < 100; test++) {
            int[] actual = randomArray(random, random.nextInt(1_001));
            int[] expected = actual.clone(); Arrays.sort(expected);
            QuickSort.sort(actual, new Metrics());
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

    @Test void hasBoundedDepthForSorted100000Array() {
        int n = 100_000;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = i;
        Metrics metrics = new Metrics();
        assertDoesNotThrow(() -> QuickSort.sort(array, metrics));
        assertTrue(metrics.getMaxDepth() <= 2 * Math.log(n) / Math.log(2), "Actual depth: " + metrics.getMaxDepth());
        for (int i = 0; i < n - 1; i++) assertTrue(array[i] <= array[i + 1]);
    }

    private void assertSorted(int[] actual) { int[] expected = actual.clone(); Arrays.sort(expected); assertDoesNotThrow(() -> QuickSort.sort(actual, new Metrics())); assertArrayEquals(expected, actual); }
    private int[] randomArray(Random random, int size) { int[] a = new int[size]; for (int i = 0; i < size; i++) a[i] = random.nextInt(20_001) - 10_000; return a; }
}
