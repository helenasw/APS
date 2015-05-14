package homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MeasuringSort {

    static int[] values;
    static int[] helper;
    static int number;
    static long numSwaps;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int length;
        StringBuilder report = new StringBuilder();

        length = Integer.parseInt(in.readLine().trim());

        while (length != 0) {

            values = new int[length];
            helper = new int[length];
            for (int i = 0; i < length; i++) {
                values[i] = Integer.parseInt(in.readLine().trim());
            }

            number = values.length;
            helper = new int[number];
            mergesort(0, number - 1);

            report.append(numSwaps).append("\n");

            //reset everything
            numSwaps = 0;
            length = Integer.parseInt(in.readLine().trim());
        }

        System.out.print(report);
    }

    private static void mergesort(int low, int high) {
        // check if low is smaller then high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
            // Sort the left side of the array
            mergesort(low, middle);
            // Sort the right side of the array
            mergesort(middle + 1, high);
            // Combine them both
            merge(low, middle, high);
        }
    }

    private static void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = values[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                values[k] = helper[i];
                i++;
            } else {
                values[k] = helper[j];
                numSwaps += (j-k);
                j++;
            }
            k++;
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            values[k] = helper[i];
            k++;
            i++;
        }

    }
}
