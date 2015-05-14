package homework8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by hwentworth23 on 4/14/15.
 */
public class Goldbach {

    static final int BOUND = 1000001;
    static boolean[] isPrime = new boolean[BOUND];

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder report = new StringBuilder();
        int num = Integer.parseInt(in.readLine().trim());
        sieve();

        while (num != 0) {

            //start at 3 because looking for odd primes
            int prime = 3;
            boolean foundOne = false;

            while (prime <= Math.ceil(num / 2.0)) {
                if (isPrime[num - prime]) {
                    report.append(num).append(" = ").append(prime).append(" + ").append(num - prime).append('\n');
                    foundOne = true;
                    break;
                } else {
                    prime = nextPrime(prime);
                }
            }

            if (!foundOne) {
                report.append("Goldbach's conjecture is wrong.\n");
            }

            num = Integer.parseInt(in.readLine().trim());
        }

        System.out.print(report);
    }

    public static int nextPrime(int prime) {
        int next = prime + 1;
        while (!isPrime[next]) {
            next ++;
        }
        return next;
    }


    public static void sieve() {
        isPrime[0] = false;
        isPrime[1] = false;

        Arrays.fill(isPrime, 2, BOUND, true);

        for (int i = 2; i < BOUND; i ++) {
            if (isPrime[i]) {
                for (int j = i + i; j < BOUND; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}
