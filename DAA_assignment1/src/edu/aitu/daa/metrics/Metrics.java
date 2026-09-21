package edu.aitu.daa.metrics;

public class Metrics {
    private long comparisons;
    private int maxDepth;
    private long startedAt;
    private long elapsedNanos;

    public void incrementComparisons() {
        comparisons++;
    }

    public void updateDepth(int depth) {
        maxDepth = Math.max(maxDepth, depth);
    }

    public void startTimer() {
        startedAt = System.nanoTime();
    }

    public void stopTimer() {
        elapsedNanos = System.nanoTime() - startedAt;
    }

    public long getComparisons() { return comparisons; }
    public int getMaxDepth() { return maxDepth; }
    public long getElapsedNanos() { return elapsedNanos; }
    public double getElapsedMillis() { return elapsedNanos / 1_000_000.0; }
}
