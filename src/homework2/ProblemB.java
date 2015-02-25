package homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ProblemB {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder report = new StringBuilder();
        PriorityQueue<BigInteger> numbers = new PriorityQueue<BigInteger>();
        int length = Integer.parseInt(in.readLine());
        BigInteger cost, nextCost;

        while (length != 0) {

            String line = in.readLine();
            st = new StringTokenizer(line);
            for (int i = 0; i < length; i ++) {
                numbers.add(new BigInteger(st.nextToken()));
            }

            cost = BigInteger.ZERO;
            while (numbers.size() > 1) {
                nextCost = numbers.poll().add(numbers.poll());
                cost = cost.add(nextCost);
                numbers.add(nextCost);
            }

            //numbers will have the last nextCost in it, get rid of it
            numbers.clear();

            report.append(cost).append("\n");

            //next case
            length = Integer.parseInt(in.readLine());
        }

        System.out.println(report.deleteCharAt(report.length() - 1));
    }
}
