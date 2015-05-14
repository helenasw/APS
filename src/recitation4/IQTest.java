package recitation4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by hwentworth23 on 4/17/15.
 */
public class IQTest {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        char[][] grid = new char[6][6];
        Arrays.fill(grid[0], 'x');
        Arrays.fill(grid[4], 'x');

        for (int i = 1; i < 5; i ++) {
            char[] line = in.readLine().toCharArray();
            grid[i][0] = 'x';
            grid[i][5] = 'x';

            for (int j = 1; j < 5; j ++) {
                grid[i][j] = line[j - 1];
            }
        }

        for (int i = 1; i < 5; i ++) {
            for (int j = 2; j < 5; j ++) {
                if (grid[i][j] == grid[i][j - 1]) {
                    if (grid[i][j] == grid[i - 1][j]
                            || grid[i][j] == grid[i - 1][j - 1]
                            || grid[i][j] == grid[i + 1][j]
                            || grid[i][j] == grid[i + 1][j - 1]) {
                        System.out.println("YES");
                        System.exit(0);
                    }
                }
            }
        }

        System.out.println("NO");
    }
}
