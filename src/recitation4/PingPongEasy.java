package recitation4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 * Created by hwentworth23 on 4/17/15.
 */
public class PingPongEasy {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        BitSet totalRange = new BitSet();
        StringTokenizer st;
        StringBuilder report = new StringBuilder();
        ArrayList<int[]> ranges = new ArrayList<int[]>();

        int numQueries = Integer.parseInt(in.readLine().trim());

        for (int i = 0; i < numQueries; i ++) {
            st = new StringTokenizer(in.readLine());

            int q = Integer.parseInt(st.nextToken());
            if (q == 1) {
                int[] range = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
                ranges.add(range);
                totalRange.set(range[0], range[1] + 1);
            } else {
                int r1 = Integer.parseInt(st.nextToken()) - 1;
                int r2 = Integer.parseInt(st.nextToken()) - 1;

                int[] range1 = ranges.get(r1);
                int[] range2 = ranges.get(r2);
                int a = range1[1];
                int b = range2[0];
                if (a > b) {
                    int tmp = a;
                    a = b;
                    b = tmp;
                }

                if (totalRange.get(a, b + 1).cardinality() == b - a) {
                    report.append("YES\n");
                } else {
                    report.append("NO\n");
                }
            }
        }

        System.out.print(report);
    }
}
