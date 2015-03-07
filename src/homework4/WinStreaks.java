package homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by Helena on 2/28/2015.
 */
public class WinStreaks {

    static double[][] prob;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder report = new StringBuilder();
        int numGames;
        double winProb, expectedWins;

        st = new StringTokenizer(in.readLine());
        numGames = Integer.parseInt(st.nextToken());
        winProb = Double.parseDouble(st.nextToken());

        while (numGames != 0) {
            prob = new double[numGames + 1][numGames + 1];

            computeProbabilities(numGames, winProb);

            expectedWins = findLongestWinStreak(numGames);
            report.append(expectedWins).append('\n');

            st = new StringTokenizer(in.readLine());
            numGames = Integer.parseInt(st.nextToken());
            winProb = Double.parseDouble(st.nextToken());
        }

        System.out.print(report);
    }

    private static void computeProbabilities(int games, double winProb) {

        for (int k = 0; k <= games; k ++) {
            for (int m = games; m >= k; m --) {
                prob[k][m] = 1;
            }
        }

        for (int k = 0; k <= games; k ++) {
            for (int m = 0; m < k; m ++) {
               if (m == k - 1) //probability that you win every game but the first or last one
                    prob[k][m] = prob[k - 1][m] - Math.pow(winProb, m + 1);
                else //general case
                    prob[k][m] = prob[k - 1][m] - (prob[k - m - 2][m] * (1 - winProb) * Math.pow(winProb, m + 1));
            }
        }
    }

    private static double findLongestWinStreak(int games) {
        double answer = 0;
        for (int k = 1; k <= games; k ++) {
            answer += ((prob[games][k] - prob[games][k - 1]) * k);
        }

        return answer;
    }
}
