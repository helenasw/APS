package individualcomp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(in.readLine().trim());
        StringBuilder report = new StringBuilder();
        while (num != 0) {

            int rem, count = 1, result = num;
            int[] numSkipped = new int[10];

            while (result != 0) {
                rem = result % 10;

                if (rem > 3) {
                    numSkipped[count] ++;
                    rem --;
                    for (int i = count; i > 0; i --) {
                        numSkipped[i] += rem;
                    }
                } else {
                    for (int i = count - 1; i > 0; i --) {
                        numSkipped[i] += rem;
                    }
                }
                count ++;
                result /= 10;
            }

            result = num;
            int digit = 1;
            for (int i = 1; i < 10; i ++) {
                result -= (digit * numSkipped[i]);
                digit *= 10;
            }

            report.append(num).append(": ").append(result).append('\n');


            num = Integer.parseInt(in.readLine().trim());
        }
        System.out.print(report);

    }
}

