package homework10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.StringTokenizer;

public class HardlyHard {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder report = new StringBuilder();
        NumberFormat formatter = new DecimalFormat("#0.000");

        int numCases = Integer.parseInt(in.readLine().trim());
        for (int i = 0; i < numCases; i ++) {
            st = new StringTokenizer(in.readLine());
            Point a = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            Point b = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            double perimeter = b.reflectAcrossY().distanceTo(a.reflectAcrossX());
            perimeter += a.distanceTo(b);
            report.append(formatter.format(perimeter)).append('\n');
        }

        System.out.print(report);
    }

    static class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Point reflectAcrossX() {
            return new Point(x, -y);
        }

        public Point reflectAcrossY() {
            return new Point(-x, y);
        }

        public double distanceTo(Point p) {
            return Math.hypot(x - p.x, y - p.y);
        }
    }

}
