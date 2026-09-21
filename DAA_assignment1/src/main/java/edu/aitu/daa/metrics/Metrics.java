package edu.aitu.daa.metrics;

public class Metrics {

    private long comparisons;
    private int maxDepth;
    private long startedAt;
    private long elapsedNanos;

    // Counts one comparison
    public void incrementComparisons() {
        comparisons++;
    }

    // Updates maximum recursion depth
    public void updateDepth(int depth) {
        maxDepth = Math.max(maxDepth, depth);
    }

    // Starts timer
    public void startTimer() {
        startedAt = System.nanoTime();
    }

    // Stops timer
    public void stopTimer() {
        elapsedNanos = System.nanoTime() - startedAt;
    }

    // Returns number of comparisons
    public long getComparisons() {
        return comparisons;
    }

    // Returns maximum recursion depth
    public int getMaxDepth() {
        return maxDepth;
    }

    // Returns elapsed time in nanoseconds
    public long getElapsedNanos() {
        return elapsedNanos;
    }

    // Returns elapsed time in nanoseconds
    // Required by BenchmarkSmokeTest
    public long getTimeNanos() {
        return elapsedNanos;
    }

    // Returns elapsed time in milliseconds
    public double getElapsedMillis() {
        return elapsedNanos / 1_000_000.0;
    }
}