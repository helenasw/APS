package recitation5;

import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hwentworth23 on 4/24/15.
 */
public class HeavyMetal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        String s = sc.next();

        Pattern p = Pattern.compile(".*a*b");
        Matcher m = p.matcher("aaaaaabab");
        int c = 0;
        while (m.find()) {
            c ++;
        }

        System.out.println(c);

    }
}
