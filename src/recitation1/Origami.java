package recitation1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by hwentworth23 on 4/3/15.
 */
public class Origami {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long tmp;

        long squares = 0;

        while (a != 0 && b != 0) {

            if (a < b) {
                tmp = a;
                a = b;
                b = tmp;
            }

            squares += a / b;
            a = a % b;
        }

        System.out.print(squares);
    }
}
