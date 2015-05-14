package recitation2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Basically see if it's a square.
 */
public class TPrimes {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int length = Integer.parseInt(in.readLine().trim());
        StringBuilder report = new StringBuilder();
        StringTokenizer st = new StringTokenizer(in.readLine());
        double num, sqrt;

        for (int i = 0; i < length; i ++) {
            num = Double.parseDouble(st.nextToken());
            if (num <= 3) {
                report.append("NO\n");
            } else {
                sqrt = Math.sqrt(num);

                if (Math.ceil(sqrt) == Math.floor(sqrt))
                    report.append("YES\n");
                else
                    report.append("NO\n");
            }
        }

        System.out.print(report);

    }
}
