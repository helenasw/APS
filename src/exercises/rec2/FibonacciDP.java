package exercises.rec2;

/**
 * Created by Helena on 2/6/2015.
 */
public class FibonacciDP {

    public static long fib(int n) {
        long[] fib = new long[n + 1];
        fib[1] = 1;
        fib[2] = 1;

        for (int i = 3; i <= n; i ++) {
            fib[i] = fib[i - 1] + fib[i -2];
        }

        return fib[n];
    }

    public static long fibEric(int n) {
        if (n < 3)
            return 1;
        return fibEric(n-2) + fibEric(n-1);
    }

    public static void main(String[] args) {
        System.out.println(fib(20));
        System.out.println(fibEric(20));
    }
}
