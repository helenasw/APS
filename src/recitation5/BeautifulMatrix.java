package recitation5;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by hwentworth23 on 4/24/15.
 */
public class BeautifulMatrix {

    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(new InputStreamReader(System.in));
        int row = 1, col = 1;

        for (int i = 1; i <= 5; i ++) {
            for (int j = 1; j <= 5; j ++) {
                if (s.nextInt() == 1) {
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        int moves = Math.abs(row - 3) + Math.abs(col - 3);

        System.out.println(moves);

    }
}
