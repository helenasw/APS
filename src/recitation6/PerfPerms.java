package recitation6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by hwentworth23 on 5/1/15.
 */
public class PerfPerms {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(in.readLine());
        StringBuilder s = new StringBuilder();

        if (size % 2 == 0) {
            for (int i = 2; i <= size; i += 2) {
                s.append(i).append(" ").append(i - 1).append(" ");
            }
            s = s.deleteCharAt(s.length() - 1);
            System.out.println(s);
        } else {
            System.out.println(-1);
        }
    }
}
