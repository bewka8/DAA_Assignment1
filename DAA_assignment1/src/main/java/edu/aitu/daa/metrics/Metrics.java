package edu.aitu.daa.metrics;

public class Metrics {

    private long comparisons;
    private int currentDepth;
    private int maxDepth;

    private long startTime;
    private long endTime;

    public Metrics() {
        reset();
    }

    public void reset() {
        comparisons = 0;
        currentDepth = 0;
        maxDepth = 0;
        startTime = 0;
        endTime = 0;
    }

    // -------------------------
    // Comparisons
    // -------------------------

    public void incrementComparisons() {
        comparisons++;
    }

    public void addComparisons(long value) {
        comparisons += value;
    }

    public long getComparisons() {
        return comparisons;
    }

    // -------------------------
    // Recursion depth
    // -------------------------

    public void enterRecursion() {
        currentDepth++;

        if (currentDepth > maxDepth) {
            maxDepth = currentDepth;
        }
    }

    public void exitRecursion() {
        currentDepth--;

        if (currentDepth < 0) {
            currentDepth = 0;
        }
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    // -------------------------
    // Time
    // -------------------------

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    public long getTimeNanos() {
        return endTime - startTime;
    }

    public double getTimeMillis() {
        return getTimeNanos() / 1_000_000.0;
    }

    // -------------------------
    // Utility
    // -------------------------

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return "Metrics{" +
                "comparisons=" + comparisons +
                ", maxDepth=" + maxDepth +
                ", timeMs=" + getTimeMillis() +
                '}';
    }
}