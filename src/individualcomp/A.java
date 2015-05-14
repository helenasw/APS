package individualcomp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class A {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numSteps = Integer.parseInt(in.readLine().trim());
        NumberFormat formatter = new DecimalFormat("#0.000");
        StringBuilder report = new StringBuilder();
        StringTokenizer st;


        while (numSteps != 0) {

            int myX = 0, myY = 0, x, y;
            for (int i = 0; i < numSteps; i ++) {
                st = new StringTokenizer(in.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                if (Math.hypot(myX + x, myY + y) > Math.hypot(myX - x, myY - y)) {
                    myX += x;
                    myY += y;
                } else {
                    myX -= x;
                    myY -= y;
                }
            }

            double distance = Math.hypot(myX, myY);
            report.append("Maximum distance = ").append(formatter.format(distance)).append(" meters.\n");

            numSteps = Integer.parseInt(in.readLine().trim());

        }

        System.out.print(report);

    }
}
