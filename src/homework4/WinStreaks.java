package homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Helena on 2/28/2015.
 */
public class WinStreaks {

    static final int UPPER_BOUND = 5;
    static double[][] prob;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder report = new StringBuilder();
        int numGames, longestWS;
        double winProb;

        st = new StringTokenizer(in.readLine());
        numGames = Integer.parseInt(st.nextToken());
        winProb = Double.parseDouble(st.nextToken());

        while (numGames != 0) {
            prob = new double[numGames + 1][numGames + 1];


            st = new StringTokenizer(in.readLine());
            numGames = Integer.parseInt(st.nextToken());
            winProb = Double.parseDouble(st.nextToken());
        }

        System.out.print(report);
    }

    private static double computeProbabilities(int games, double winProb) {
        for (int k = 0; k <= games; k ++) {
            for (int m = 0; m <= k; m ++) {
                if (m == k)
                    prob[k][m] = Math.pow(winProb, m);
                else if (m == k - 1) {
                    prob[k][m] = prob[k - 1][m];
                } else {
                    prob[k][m] = prob[k - 1][m] - prob[k - m - 2][m] * (1 - winProb) * Math.pow(winProb, m + 1);
                }
            }
        }

        return prob[games][games];
    }
}
