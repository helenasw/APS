package recitation2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MagicNumbers {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String n = in.readLine();
        String[] bits144 = n.split("144");

        for (String s : bits144) {
            String[] bits14 = s.split("14");
            for (String s14 : bits14) {
                if (s14.split("1").length != 0) {
                    System.out.println("NO");
                    System.exit(0);
                }
            }
        }

        System.out.println("YES");
    }
}
