package homework9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntegerGame {



    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder report = new StringBuilder();

        int numCases = Integer.parseInt(in.readLine().trim());
        for (int i = 1; i <= numCases; i ++) {
            char[] number = in.readLine().trim().toCharArray();
            report.append("Case ").append(i);
            if (number.length == 1) {
                report.append(": S\n");
                continue;
            }

            int[] numWRem = digitsModThree(number);

            int moves = 0;
            if ((numWRem[1] + (2 * numWRem[2])) % 3 == 0) {
                moves = numWRem[0];
            } else if (numWRem[1] > 0 && (numWRem[1] - 1 + (2 * numWRem[2])) % 3 == 0) {
                moves = 1 + numWRem[0];
            } else if (numWRem[2] > 0 && (numWRem[1] + (2 * (numWRem[2] - 1))) % 3 == 0) {
                moves = 1 + numWRem[0];
            }

            if (moves % 2 == 0) {
                report.append(": T\n");
            } else {
                report.append(": S\n");
            }
        }

        System.out.print(report);
    }

    private static int[] digitsModThree(char[] arr) {
        int[] mods = new int[3];
        for (char c : arr) {
            mods[Character.getNumericValue(c) % 3] ++;
        }

        return mods;
    }
}
