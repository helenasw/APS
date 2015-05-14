package recitation7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by hwentworth23 on 5/8/15.
 */
public class FlipOut {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numNums = Integer.parseInt(in.readLine().trim());
        int maxOnes = 0;
        int numOnes = 0;
        int[] numbers = new int[numNums];
        StringTokenizer st = new StringTokenizer(in.readLine());
        for (int i = 0; i < numNums; i ++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            if (numbers[i] == 1)
                numOnes ++;
        }

        for (int i = 0; i < numNums; i ++) {
            int onesInRange = 0;
            for (int j = i; j < numNums; j ++) {
                if (numbers[j] == 1)
                    onesInRange ++;
                int poss = numOnes + j - i - (2 * onesInRange) + 1;
                maxOnes = Math.max(poss, maxOnes);
            }
        }

        System.out.println(maxOnes);
    }
}
