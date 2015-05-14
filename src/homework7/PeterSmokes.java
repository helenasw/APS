package homework7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PeterSmokes {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numCigs, k, totalCigs, numButts;
        String line;
        StringTokenizer st;
        StringBuilder report = new StringBuilder();

        line = in.readLine();
        while (line != null && !line.isEmpty()) {
            st = new StringTokenizer(line);
            numCigs = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            totalCigs = 0;
            numButts = numCigs;
            while ((numButts + numCigs) >= k) {
                totalCigs += numCigs;
                numCigs = numButts / k;
                numButts = numCigs + numButts % k;

            }
            totalCigs += numCigs;
            line = in.readLine();

            report.append(totalCigs).append('\n');
        }

        System.out.print(report);

    }
}
