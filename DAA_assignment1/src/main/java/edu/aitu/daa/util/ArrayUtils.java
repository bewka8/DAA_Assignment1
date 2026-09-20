package edu.aitu.daa.util;

import java.util.Arrays;
import java.util.Random;

public final class ArrayUtils {

    private ArrayUtils() {
        // Utility class
    }

    public static int[] copy(int[] array) {
        return Arrays.copyOf(array, array.length);
    }

    public static boolean isSorted(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    public static int[] randomArray(int n, Random random) {
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt();
        }

        return array;
    }

    public static int[] sortedArray(int n, Random random) {
        int[] array = randomArray(n, random);
        Arrays.sort(array);
        return array;
    }

    public static int[] duplicateArray(int n, Random random) {
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(10);
        }

        return array;
    }

    public static int[] generate(String type, int n, Random random) {
        return switch (type.toLowerCase()) {
            case "random" -> randomArray(n, random);
            case "sorted" -> sortedArray(n, random);
            case "duplicates" -> duplicateArray(n, random);
            default -> throw new IllegalArgumentException(
                    "Unknown input type: " + type
            );
        };
    }
}