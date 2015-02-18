package homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ProblemB {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder report = new StringBuilder();
        int[] numbers;
        int cost, sum, length = Integer.parseInt(in.readLine());

        while (length != 0) {

            numbers = new int[length];
            String line = in.readLine();
            st = new StringTokenizer(line);
            for (int i = 0; i < length; i ++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(numbers);

            cost = 0;
            sum = numbers[0];
            for (int i = 1; i < length; i ++) {
                cost += sum + numbers[i];
                sum += numbers[i];
            }

            report.append(cost).append("\n");

            //next case
            length = Integer.parseInt(in.readLine());
        }

        System.out.println(report);
    }
}
