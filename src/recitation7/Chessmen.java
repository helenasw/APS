package recitation7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by hwentworth23 on 5/8/15.
 */
public class Chessmen {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int rows = Integer.parseInt(st.nextToken());
        int cols = Integer.parseInt(st.nextToken());
        char[][] board = new char[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i ++) {
            char[] row = in.readLine().trim().toCharArray();
            for (int j = 1; j <= cols; j ++) {
                if (row[j - 1] == '-') {
                    board[i][j] = '-';
                } else {
                    if (board[i - 1][j] == 'B') {
                        board[i][j] = 'W';
                    } else {
                        if (board[i][j - 1] == 'B')
                            board[i][j] = 'W';
                        else
                            board[i][j] = 'B';
                    }
                }
            }
        }
        String answer = "";

        for (int i = 1; i <= rows; i ++) {
            for (int j = 1; j <= cols; j++) {
                answer += board[i][j];
            }
            answer += '\n';
        }

        System.out.print(answer);
    }
}
