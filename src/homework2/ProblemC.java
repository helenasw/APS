package homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Stars
 */
public class ProblemC {
    public static void main(String[] args) throws IOException {
        final int maxPlaneSize = 32000;
        BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder report = new StringBuilder();
        int x, level;
        StringTokenizer st;

        //adjust for 1-based indexing and inclusive of upper bound
        Fenwick stars = new Fenwick(maxPlaneSize + 2);

        int numStars = Integer.parseInt(in.readLine());

        int[] starsAtLevel = new int[numStars];
        for (int i = 0; i < numStars; i ++) {
            st = new StringTokenizer(in.readLine());
            x = Integer.parseInt(st.nextToken()) + 1; //adjust for 1-based indexing into fenwick

            //query for level of current star and add to running number of levels
            level = stars.sumQuery(x);
            starsAtLevel[level] ++;

            //add 1 to number of stars at latitude x
            stars.adjust(x, 1);
        }

        for (int i = 0; i < numStars; i ++) {
            report.append(starsAtLevel[i]).append("\n");
        }

        System.out.println(report);
    }
}

class Fenwick {
    public int[] table;

    public Fenwick(int maxN) {
        this.table = new int[maxN + 1];
    }

    public int sumQuery(int a, int b) {
        return sumQuery(b) - sumQuery(a - 1);
    }

    public int sumQuery(int k) {
        int ret = 0;
        while (k > 0) {
            ret += table[k];
            k &= k - 1;
        }
        return ret;
    }

    public void adjust(int i, int value) {
        while (i < table.length) {
            table[i] += value;
            i += (i & (-i));
        }
    }
}