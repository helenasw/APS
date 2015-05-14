package recitation1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/**
 * Created by hwentworth23 on 4/3/15.
 */
public class Pangrams {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Character> letters = new HashSet<Character>();

        in.readLine();
        String word = in.readLine().trim().toLowerCase();
        char[] chars = word.toCharArray();
        for (char c : chars) {
            letters.add(c);
        }

        if (letters.size() == 26) {
            System.out.print("YES");
        } else {
            System.out.print("NO");

        }
    }
}
