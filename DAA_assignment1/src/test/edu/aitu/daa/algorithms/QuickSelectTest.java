package test.edu.aitu.daa.algorithms;

import edu.aitu.daa.metrics.Metrics;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuickSelectTest {
    @Test void findsKthElementOn100RandomArrays() {
        Random random = new Random(42);
        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(1_000) + 1;
            int[] array = new int[size];
            for (int i = 0; i < size; i++) array[i] = random.nextInt(20_001) - 10_000;
            int[] sorted = array.clone(); Arrays.sort(sorted);
            int k = random.nextInt(size);
            assertEquals(sorted[k], QuickSelect.select(array, k, new Metrics()), "Random case " + test);
        }
    }

    @Test void handlesDuplicatesAndSingleElement() {
        assertEquals(4, QuickSelect.select(new int[]{4, 2, 4, 1, 4, 3, 2}, 4, new Metrics()));
        assertEquals(42, QuickSelect.select(new int[]{42}, 0, new Metrics()));
    }

    @Test void rejectsInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{}, 0, new Metrics()));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{1, 2, 3}, -1, new Metrics()));
        assertThrows(IllegalArgumentException.class, () -> QuickSelect.select(new int[]{1, 2, 3}, 3, new Metrics()));
    }
}
