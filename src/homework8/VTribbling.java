package homework8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class VTribbling {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numCases = Integer.parseInt(in.readLine().trim());
        StringTokenizer st;
        StringBuilder report = new StringBuilder();
        NumberFormat formatter = new DecimalFormat("#0.0000000");

        for (int c = 1; c <= numCases; c ++) {

            st = new StringTokenizer(in.readLine());

            int kids = Integer.parseInt(st.nextToken());
            int tribbles = Integer.parseInt(st.nextToken());
            int generations = Integer.parseInt(st.nextToken());

            double[] probKids = new double[kids];
            for (int i = 0; i < kids; i ++) {
                probKids[i] = Double.parseDouble(in.readLine().trim());
            }

            if (tribbles == 0) {
                report.append("Case #").append(c).append(": 1.0000000\n");
            } else if (generations == 0) {
                report.append("Case #").append(c).append(": 0.0000000\n");
            } else {
                double[] probAllDead = new double[generations + 1];

                probAllDead[0] = 0;
                probAllDead[1] = probKids[0];

                for (int i = 2; i <= generations; i++) {

                    probAllDead[i] = probKids[0];
                    for (int j = 1; j < kids; j++) {
                        probAllDead[i] += (Math.pow(probAllDead[i - 1], j) * probKids[j]);
                    }
                }

                report.append("Case #").append(c).append(": ").append(formatter.format(Math.pow(probAllDead[generations], tribbles))).append('\n');
            }
        }

        System.out.print(report);
    }
}
