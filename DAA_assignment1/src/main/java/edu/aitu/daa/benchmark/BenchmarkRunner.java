package edu.aitu.daa.benchmark;

import edu.aitu.daa.algorithms.MergeSort;
import edu.aitu.daa.algorithms.QuickSelect;
import edu.aitu.daa.algorithms.QuickSort;
import edu.aitu.daa.metrics.Metrics;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public final class BenchmarkRunner {
    private static final int[] SIZES = {1_000, 10_000, 100_000, 1_000_000};
    private static final String[] INPUT_TYPES = {
            "random", "sorted", "duplicates"
    };

    private static final int MEASURED_RUNS = 5;
    private static final int WARMUP_RUNS = 2;

    private BenchmarkRunner() {
    }

    public static void runAll() throws IOException {
        Random random = new Random(42);

        try (CsvWriter csv = new CsvWriter("results.csv")) {
            for (int size : SIZES) {
                for (String inputType : INPUT_TYPES) {
                    int[] original = InputGenerator.generate(inputType, size, random);

                    warmUp(original);

                    saveSortResult("MergeSort", inputType, original, csv, true);
                    saveSortResult("QuickSort", inputType, original, csv, false);
                    saveQuickSelectResult(inputType, original, csv);
                }
            }
        }

        System.out.println("Benchmark completed: results.csv was created.");
    }

    private static void warmUp(int[] original) {
        for (int i = 0; i < WARMUP_RUNS; i++) {
            int[] mergeArray = original.clone();
            MergeSort.sort(mergeArray, new Metrics());

            int[] quickArray = original.clone();
            QuickSort.sort(quickArray, new Metrics());

            int[] selectArray = original.clone();
            QuickSelect.select(selectArray, selectArray.length / 2, new Metrics());
        }
    }

    private static void saveSortResult(
            String algorithm,
            String inputType,
            int[] original,
            CsvWriter csv,
            boolean isMergeSort
    ) throws IOException {
        double[] times = new double[MEASURED_RUNS];
        long[] comparisons = new long[MEASURED_RUNS];
        int[] depths = new int[MEASURED_RUNS];

        for (int i = 0; i < MEASURED_RUNS; i++) {
            int[] array = original.clone();
            Metrics metrics = new Metrics();

            long start = System.nanoTime();

            if (isMergeSort) {
                MergeSort.sort(array, metrics);
            } else {
                QuickSort.sort(array, metrics);
            }

            long end = System.nanoTime();

            times[i] = (end - start) / 1_000_000.0;
            comparisons[i] = metrics.getComparisons();
            depths[i] = metrics.getMaxDepth();
        }

        csv.writeRow(
                algorithm,
                inputType,
                original.length,
                median(times),
                median(comparisons),
                median(depths)
        );
    }

    private static void saveQuickSelectResult(
            String inputType,
            int[] original,
            CsvWriter csv
    ) throws IOException {
        double[] times = new double[MEASURED_RUNS];
        long[] comparisons = new long[MEASURED_RUNS];
        int[] depths = new int[MEASURED_RUNS];

        int k = original.length / 2;

        for (int i = 0; i < MEASURED_RUNS; i++) {
            int[] array = original.clone();
            Metrics metrics = new Metrics();

            long start = System.nanoTime();
            QuickSelect.select(array, k, metrics);
            long end = System.nanoTime();

            times[i] = (end - start) / 1_000_000.0;
            comparisons[i] = metrics.getComparisons();
            depths[i] = metrics.getMaxDepth();
        }

        csv.writeRow(
                "QuickSelect",
                inputType,
                original.length,
                median(times),
                median(comparisons),
                median(depths)
        );
    }

    private static double median(double[] values) {
        double[] copy = values.clone();
        Arrays.sort(copy);
        return copy[copy.length / 2];
    }

    private static long median(long[] values) {
        long[] copy = values.clone();
        Arrays.sort(copy);
        return copy[copy.length / 2];
    }

    private static int median(int[] values) {
        int[] copy = values.clone();
        Arrays.sort(copy);
        return copy[copy.length / 2];
    }
}