package recitation1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 * Created by hwentworth23 on 4/3/15.
 */
public class PashaString {

    public static void main(String[] args) throws IOException {

        BufferedReader in =  new BufferedReader(new InputStreamReader(System.in));
        char[] chars = in.readLine().trim().toCharArray();
        int m = Integer.parseInt(in.readLine().trim());
        int a;

        BitSet flipped = new BitSet();
        StringTokenizer st = new StringTokenizer(in.readLine());

        for (int i = 0; i < m; i ++) {
            a = Integer.parseInt(st.nextToken()) - 1;
            flipped.flip(a, chars.length - a);
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < chars.length; i ++) {
            if (flipped.get(i)) {
                s.append(chars[chars.length - 1 - i]);
            } else {
                s.append(chars[i]);
            }
        }

        System.out.print(s);
    }
}
