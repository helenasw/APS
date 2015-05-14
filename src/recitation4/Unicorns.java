package recitation4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Unicorns {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int goal = 0, atLeast = 0, unknown = 0;

        char[] s1 = in.readLine().trim().toCharArray();
        for (char c : s1) {
            if (c == '+')
                goal ++;
            else
                goal --;
        }

        char[] s2 = in.readLine().trim().toCharArray();
        for (char c : s2) {
            if (c == '+')
                atLeast ++;
            else if (c == '-')
                atLeast --;
            else
                unknown ++;
        }


        if (goal == atLeast && unknown == 0) {
            System.out.println(1.000000000000);
        } else {


            NumberFormat f = new DecimalFormat("#0.000000000000");

        }
    }
}
