package homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Think of this as breaking it up into groups of the largest denomination that you allow,
 * a quarter, dime, nickel, or penny. The remainder can be treated in the same way, but
 * treat the next smallest denomination as your largest denomination.
 * Notes:
 * - To make memo things smaller, make them the UPPER_BOUND / denom size
 * - Don't keep a cache for pennies, the answer is always 1 way
 */
public class MakinChange {

    final static int UPPER_BOUND = 30000, HALF_DOLLAR = 50, QUARTER = 25, DIME = 10, NICKEL = 5, PENNY = 1;
    static HashMap<Integer, long[]> numWays;

    public static void main(String[] args) throws IOException {

        long ways;
        numWays = new HashMap<Integer, long[]>();
        numWays.put(HALF_DOLLAR, new long[UPPER_BOUND / NICKEL + 1]);
        numWays.put(QUARTER, new long[UPPER_BOUND / NICKEL + 1]);
        numWays.put(DIME, new long[UPPER_BOUND / NICKEL + 1]);
        numWays.put(NICKEL, new long[UPPER_BOUND / NICKEL + 1]);

        StringBuilder report = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        int amount;

        while (line != null && !line.isEmpty()) {
            amount = Integer.parseInt(line.trim());

            ways = getNumWays(amount, largestPossibleDenom(amount));
            if (ways > 1) {
                report.append("There are ").append(ways).append(" ways to produce ").append(amount).append(" cents change.\n");
            } else {
                report.append("There is only 1 way to produce ").append(amount).append(" cents change.\n");
            }
            line = in.readLine();
        }

        System.out.print(report);
    }

    private static long getNumWays(int amount, int highestDenom) {
        int remainder, nextD, index = amount / NICKEL;
        long ways;
        if (highestDenom == PENNY)
            return 1;

        ways = numWays.get(highestDenom)[index];
        if (ways != 0)
            return ways;

        ways = 0;
        for (int i = 0; i * highestDenom <= amount; i ++) {
            //increase num of highestDenom coins we use one by one
            //with each increase add num ways we can make change of remainder using lower denom
            remainder = amount - (i * highestDenom);
            nextD = nextDenom(highestDenom);
            nextD = nextD < remainder ? nextD : largestPossibleDenom(remainder);
            ways += getNumWays(remainder, nextD);
        }

        numWays.get(highestDenom)[index] = ways;

        return ways;
    }


    /**
     * Get the largest possible denomination less than amount.
     */
    private static int largestPossibleDenom(int amount) {
        if (amount >= HALF_DOLLAR)
            return HALF_DOLLAR;
        else if (amount >= QUARTER)
            return QUARTER;
        else if (amount >= DIME)
            return DIME;
        else if (amount >= NICKEL)
            return NICKEL;
        else
            return PENNY;
    }

    /**
     * Given a denomination, return the next smallest one.
     */
    private static int nextDenom(int denom) {
        switch (denom) {
            case HALF_DOLLAR:
                return QUARTER;
            case QUARTER:
                return DIME;
            case DIME:
                return NICKEL;
            case NICKEL:
                return PENNY;
            default:
                return PENNY;
        }
    }
}
