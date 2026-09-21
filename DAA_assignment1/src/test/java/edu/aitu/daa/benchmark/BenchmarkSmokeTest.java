package edu.aitu.daa.benchmark;

import edu.aitu.daa.algorithms.MergeSort;
import edu.aitu.daa.algorithms.QuickSelect;
import edu.aitu.daa.algorithms.QuickSort;
import edu.aitu.daa.metrics.Metrics;
import edu.aitu.daa.util.ArrayUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BenchmarkSmokeTest {

    private static final Random RANDOM = new Random(42);

    @Test
    void benchmarkComponentsShouldWork() {

        int[] sizes = {100, 1_000};

        for (int n : sizes) {

            int[] original =
                    ArrayUtils.randomArray(n, RANDOM);

            // -------------------------
            // MergeSort
            // -------------------------

            int[] mergeArray =
                    Arrays.copyOf(original, original.length);

            Metrics mergeMetrics = new Metrics();

            mergeMetrics.startTimer();

            MergeSort.sort(mergeArray, mergeMetrics);

            mergeMetrics.stopTimer();

            assertTrue(
                    ArrayUtils.isSorted(mergeArray),
                    "MergeSort failed for n=" + n
            );

            assertTrue(
                    mergeMetrics.getTimeNanos() >= 0,
                    "MergeSort time is invalid"
            );

            assertTrue(
                    mergeMetrics.getComparisons() >= 0,
                    "MergeSort comparisons are invalid"
            );

            // -------------------------
            // QuickSort
            // -------------------------

            int[] quickArray =
                    Arrays.copyOf(original, original.length);

            Metrics quickMetrics = new Metrics();

            quickMetrics.startTimer();

            QuickSort.sort(quickArray, quickMetrics);

            quickMetrics.stopTimer();

            assertTrue(
                    ArrayUtils.isSorted(quickArray),
                    "QuickSort failed for n=" + n
            );

            assertTrue(
                    quickMetrics.getTimeNanos() >= 0,
                    "QuickSort time is invalid"
            );

            assertTrue(
                    quickMetrics.getComparisons() >= 0,
                    "QuickSort comparisons are invalid"
            );

            // -------------------------
            // QuickSelect
            // -------------------------

            int[] selectArray =
                    Arrays.copyOf(original, original.length);

            int k = n / 2;

            int[] sorted =
                    Arrays.copyOf(original, original.length);

            Arrays.sort(sorted);

            Metrics selectMetrics = new Metrics();

            selectMetrics.startTimer();

            int result =
                    QuickSelect.select(
                            selectArray,
                            k,
                            selectMetrics
                    );

            selectMetrics.stopTimer();

            assertEquals(
                    sorted[k],
                    result,
                    "QuickSelect returned wrong result"
            );

            assertTrue(
                    selectMetrics.getTimeNanos() >= 0,
                    "QuickSelect time is invalid"
            );

            assertTrue(
                    selectMetrics.getComparisons() >= 0,
                    "QuickSelect comparisons are invalid"
            );
        }
    }

    @Test
    void inputGeneratorShouldProduceCorrectInputTypes() {

        int n = 1000;

        int[] random =
                ArrayUtils.generate(
                        "random",
                        n,
                        RANDOM
                );

        int[] sorted =
                ArrayUtils.generate(
                        "sorted",
                        n,
                        RANDOM
                );

        int[] duplicates =
                ArrayUtils.generate(
                        "duplicates",
                        n,
                        RANDOM
                );

        assertEquals(n, random.length);
        assertEquals(n, sorted.length);
        assertEquals(n, duplicates.length);

        assertTrue(
                ArrayUtils.isSorted(sorted),
                "Sorted input is not sorted"
        );

        for (int value : duplicates) {
            assertTrue(
                    value >= 0 && value <= 9,
                    "Duplicate input contains value outside 0..9"
            );
        }
    }
}