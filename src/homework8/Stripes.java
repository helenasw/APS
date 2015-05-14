package homework8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * Created by hwentworth23 on 4/14/15.
 */
public class Stripes {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder report = new StringBuilder();
        int totalWhite, groups, numCases;

        numCases = Integer.parseInt(in.readLine().trim());

        for (int c = 0; c < numCases; c ++) {

            st = new StringTokenizer(in.readLine());

            totalWhite = Integer.parseInt(st.nextToken());
            groups = Integer.parseInt(st.nextToken());

            for (int g = 0; g < groups; g ++) {
                totalWhite -= Integer.parseInt(st.nextToken());
            }

            //number of ways to place groups separators into totalWhite + 1 spaces
            report.append(choose(totalWhite + 1, groups)).append('\n');

        }

        System.out.print(report);
    }

    private static BigInteger choose(int n, int k) {
        BigInteger result = new BigInteger(Integer.toString(n));
        if (k > n) {
            return BigInteger.ZERO;
        }
        if (k * 2 > n) {
            k = n - k;
        }
        if (k == 0) {
            return BigInteger.ONE;
        }

        for (int i = 2; i <= k; i ++ ) {
            result = result.multiply(new BigInteger(Integer.toString(n - i + 1)));
            result = result.divide(new BigInteger(Integer.toString(i)));
        }

        return result;
    }
}
