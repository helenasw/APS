package homework10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class UselessTilePackers {

    static final double EPS = 1e-9;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numCorners = Integer.parseInt(in.readLine().trim());
        StringTokenizer st;
        StringBuilder report = new StringBuilder();
        int currCase = 1;
        NumberFormat formatter = new DecimalFormat("#0.00");


        while (numCorners != 0) {
            Polygon tile = new Polygon();
            for (int i = 0; i < numCorners; i ++) {
                st = new StringTokenizer(in.readLine());
                tile.corners.add(new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
            }

            double tileArea = tile.computeArea();
            double packArea = tile.getConvexHull().computeArea();

            report.append("Tile #").append(currCase);
            report.append("\nWasted Space = ").append(formatter.format((packArea - tileArea) / packArea * 100)).append(" %\n\n");

            numCorners = Integer.parseInt(in.readLine().trim());
            currCase ++;
        }

        System.out.print(report);
    }

    static class Polygon {
        ArrayList<Point> corners = new ArrayList<Point>();
        Point pivot = new Point(0.0, 0.0);

        public double computeArea() {
            double result = 0.0, x1, y1, x2, y2;
            for (int i = 0; i < corners.size() - 1; i ++) {
                x1 = corners.get(i).x; x2 = corners.get(i + 1).x;
                y1 = corners.get(i).y; y2 = corners.get(i + 1).y;
                result += (x1 * y2 - x2 * y1);
            }

            result += (corners.get(corners.size() - 1).x * corners.get(0).y - corners.get(0).x * corners.get(corners.size() - 1).y);

            return Math.abs(result) / 2.0;
        }

        public Polygon getConvexHull() {

            int i, j, n = corners.size();
            if (n <= 3) {
                if (corners.get(0).compareTo(corners.get(n - 1)) != 0)
                    corners.add(corners.get(0)); // safeguard from corner case
                return this; // special case, the CH is P itself
            }

            // first, find P0 = point with lowest Y and if tie: rightmost X
            int P0 = 0;
            for (i = 1; i < n; i++) {
                if (corners.get(i).y < corners.get(P0).y
                        || (corners.get(i).y == corners.get(P0).y && corners.get(i).x > corners.get(P0).x))
                    P0 = i;
            }

            // swap P[P0] with P[0]
            Point temp = corners.get(0);
            corners.set(0, corners.get(P0));
            corners.set(P0 ,temp);

            // second, sort points by angle w.r.t. P0
            pivot = corners.get(0); // use this global variable as reference
            Collections.sort(corners, new Comparator<Point>() {
                public int compare(Point a, Point b) { // angle-sorting function
                    if (pivot.collinear(a, b))
                        return pivot.distTo(a) < pivot.distTo(b) ? -1 : 1; // which one is closer?
                    double d1x = a.x - pivot.x, d1y = a.y - pivot.y;
                    double d2x = b.x - pivot.x, d2y = b.y - pivot.y;
                    return (Math.atan2(d1y, d1x) - Math.atan2(d2y, d2x)) < 0 ? -1 : 1;
                }
            });

            // third, the ccw tests
            Polygon hull = new Polygon();
            // initial hull
            hull.corners.add(corners.get(n - 1));
            hull.corners.add(corners.get(0));
            hull.corners.add(corners.get(1));
            i = 2; // then, we check the rest
            while (i < n) { // note: n must be >= 3 for this method to work
                j = hull.corners.size() - 1;
                if (Point.isCCW(hull.corners.get(j - 1), hull.corners.get(j), corners.get(i)))
                    hull.corners.add(corners.get(i++)); // left turn, accept
                else
                    hull.corners.remove(hull.corners.size() - 1); // or pop the top of S until we have a left turn
            }

            return hull;
        } // return the result
    }

    static class Point implements Comparable<Point>{
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public boolean collinear(Point a, Point b) {
            return Math.abs(Vect.cross(new Vect(this, a), new Vect(this, b))) < EPS;
        }

        public double distTo(Point b) {
            return Math.hypot(x - b.x, y - b.y);
        }

        @Override
        public int compareTo(Point o) {
            if (Math.abs(x - o.x) > EPS)                // useful for sorting
                return (int)Math.ceil(x - o.x);       // first: by x-coordinate
            else if (Math.abs(y - o.y) > EPS)
                return (int)Math.ceil(y - o.y);      // second: by y-coordinate
            else
                return 0;                                   // they are equal
        }

        static boolean isCCW(Point p, Point q, Point r) {
            return Vect.cross(new Vect(p, q), new Vect(p, r)) > 0;
        }
    }


    static class Vect {
        double x, y;

        public Vect(Point a, Point b) {
            x = b.x - a.x;
            y = b.y - a.y;
        }


        static double cross(Vect a, Vect b) { return a.x * b.y - a.y * b.x; }
    }
}
