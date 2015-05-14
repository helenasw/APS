package recitation6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Worms {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numWorms = Integer.parseInt(in.readLine().trim());
        String line = in.readLine();
        StringTokenizer st = new StringTokenizer(line);
        HashMap<Integer, HashSet<Integer>> wormsInd = new HashMap<Integer, HashSet<Integer>>();
        int[] worms = new int[numWorms + 2];
        for (int i = 1; i <= numWorms; i ++) {
            worms[i] = Integer.parseInt(st.nextToken());
            if (wormsInd.containsKey(worms[i])) {
                wormsInd.get(worms[i]).add(i);
            } else {
                HashSet<Integer> ind = new HashSet<Integer>();
                ind.add(i);
                wormsInd.put(worms[i], ind);
            }
        }
        int sum;
        for (int k = 1; k <= numWorms; k ++) {
            for (int j = k + 1; j <= numWorms; j ++) {
                sum = worms[k] + worms[j];

                if (wormsInd.containsKey(sum)) {
                    for (Integer i : wormsInd.get(sum)) {
                        if (i != j && i != k) {
                            System.out.println(i + " " + j + " " + k);
                            System.exit(0);
                        }
                    }

                }
            }
        }

        System.out.println(-1);
    }
}
