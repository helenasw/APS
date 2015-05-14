package homework9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SecretWord {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder report = new StringBuilder();

        int numCases = Integer.parseInt(in.readLine().trim());

        for (int i = 0; i < numCases; i ++) {
            String pattern = in.readLine().trim();
            String text = new StringBuilder(pattern).reverse().toString();
            int[] table = buildKMPTable(pattern);
            int length = simulate(table, text, pattern);
            report.append(new StringBuilder(pattern.substring(0, length)).reverse()).append('\n');
        }

        System.out.print(report);
    }

    public static int[] buildKMPTable(String pattern) {

        int[] table = new int[pattern.length() + 1];
        for (int i = 2; i < table.length; i ++) {
            int j = table[i - 1];

            while (true) {
                if (pattern.charAt(j) == pattern.charAt(i-1)) {
                    table[i] = j + 1;
                    break;
                } else if (j == 0) {
                    break;
                } else {
                    j = table[j];
                }
            }
        }
        return table;
    }

    /**
     * Returns the final state when simulating the DFA built using pattern on the string text
     **/
    public static int simulate(int[] table, String text, String pattern) {

        int state = 0, maxState = 0;

        for (int i = 0; i < text.length(); i ++) {
            while (true) {
                if (text.charAt(i) == pattern.charAt(state)) {
                    state ++;
                    maxState = Math.max(state, maxState);
                    break;
                } else if (state == 0) {
                    break;
                }
                state = table[state];
            }

            if (state == table.length -1)  {
                break;
            }
        }
        return maxState;
    }

}
