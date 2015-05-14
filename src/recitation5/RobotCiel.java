package recitation5;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by hwentworth23 on 4/24/15.
 */
public class RobotCiel {

    public static void main(String[] args) {

        Scanner s = new Scanner(new InputStreamReader(System.in));
        Point dest = new Point(s.nextLong(), s.nextLong());
        Point move = new Point(0, 0);
        char[] dirs = s.next().trim().toCharArray();
        for (char d : dirs) {
            switch (d) {
                case 'U':
                    move.up();
                    break;
                case 'D':
                    move.down();
                    break;
                case 'R':
                    move.right();
                    break;
                case 'L':
                    move.left();
                    break;
            }
        }

        if (move.x == 0) move.x = 1;
        if (move.y == 0) move.y = 1;

        if (dest.x / move.x == dest.y / move.y ) {
            if (dest.x % move.x == 0 && dest.y % move.y == 0) {
                System.out.println("Yes");
                System.exit(0);
            } else {
                Point from = new Point(dest.x % move.x, dest.y % move.y);
                for (int i = (int)(dest.x % move.x); i < dirs.length; i ++) {
                    switch (dirs[i]) {
                        case 'U':
                            from.up();
                            break;
                        case 'D':
                            from.down();
                            break;
                        case 'R':
                            from.right();
                            break;
                        case 'L':
                            from.left();
                            break;
                    }
                    if (from.x == dest.x && from.y == dest.y) {
                        System.out.println("Yes");
                        System.exit(0);
                    }
                }
            }
        }

        System.out.println("No");

    }

    static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        private void up() { y ++; }
        private void down() { y --; }
        private void right() { x ++; }
        private void left() { x --; }
    }
}
