package homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Think of this as breaking it up into groups of the largest denomination that you allow,
 * a quarter, dime, nickel, or penny. The remainder can be treated in the same way, but
 * treat the next smallest denomination as your largest denomination.
 * Notes:
 * - To make memo things smaller, make them the UPPER_BOUND / denom size
 * - Don't keep a cache for pennies, the answer is always 1 way
 */
public class MakinChange {

    final static int UPPER_BOUND = 30000, QUARTER = 25, DIME = 10, NICKEL = 5;
    static int[] quarters = new int[UPPER_BOUND / QUARTER];
    static int[] dimes = new int[UPPER_BOUND / DIME];
    static int[] nickels = new int[UPPER_BOUND / NICKEL];

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        int amount;

        while (line != null) {
            amount = Integer.parseInt(line);

            line = in.readLine();
        }
    }

    private static int getNumCombinations(int amount, int highestDenom) {
        
    }

}
