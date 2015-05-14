package recitation5;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Volodya {

    public static void main(String[] args) {
        Scanner s = new Scanner(new InputStreamReader(System.in));
        long n = s.nextLong();
        long k = s.nextLong();
        long answer;

        if (k <= Math.ceil(n / 2.0)) {
            answer = 2 * k - 1;
        } else {
            answer = 2 * (k - (long)Math.ceil(n / 2.0));
        }

        System.out.println(answer);
    }
}

