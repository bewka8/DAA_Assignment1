package edu.aitu.daa.benchmark;

import java.util.Arrays;
import java.util.Random;

public final class InputGenerator {
    private InputGenerator() {
    }

    public static int[] generate(String inputType, int size, Random random) {
        int[] array = new int[size];

        switch (inputType) {
            case "random" -> {
                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt();
                }
            }

            case "sorted" -> {
                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt();
                }
                Arrays.sort(array);
            }

            case "duplicates" -> {
                for (int i = 0; i < size; i++) {
                    array[i] = random.nextInt(10); // values from 0 to 9
                }
            }

            default -> throw new IllegalArgumentException(
                    "Unknown input type: " + inputType
            );
        }

        return array;
    }
}