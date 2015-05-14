package recitation4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Trainer {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int biceps = 0, chest = 0, back = 0;
        int exercises = Integer.parseInt(in.readLine().trim());

        StringTokenizer st = new StringTokenizer(in.readLine());

        for (int e = 0; e < exercises; e ++) {
            if (e % 3 == 0) {
                chest += Integer.parseInt(st.nextToken());
            } else if (e % 3 == 1) {
                biceps += Integer.parseInt(st.nextToken());
            } else {
                back += Integer.parseInt(st.nextToken());
            }
        }

        int max = Math.max(Math.max(biceps, chest), back);

        if (max == chest) {
            System.out.println("chest");
        } else if (max == biceps) {
            System.out.println("biceps");
        } else {
            System.out.println("back");
        }
    }
}
