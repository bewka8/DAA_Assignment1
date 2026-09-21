package edu.aitu.daa.benchmark;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CsvWriter implements AutoCloseable {
    private final BufferedWriter writer;

    public CsvWriter(String fileName) throws IOException {
        writer = Files.newBufferedWriter(Path.of(fileName));
        writer.write("algorithm,input,n,time_ms,comparisons,max_depth");
        writer.newLine();
    }

    public void writeRow(
            String algorithm,
            String input,
            int size,
            double timeMs,
            long comparisons,
            int maxDepth
    ) throws IOException {
        writer.write("%s,%s,%d,%.6f,%d,%d%n".formatted(
                algorithm,
                input,
                size,
                timeMs,
                comparisons,
                maxDepth
        ));
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}