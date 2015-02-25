package homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;

/**
 * Created by Helena on 2/24/2015.
 */
public class Scarecrows {

    public static void main(String[] args) throws IOException {
        int numCases, numSpaces, numScarecrows;
        final char fertile = '.';
        char[] field;
        BitSet isProtected = new BitSet();
        StringBuilder report = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        numCases = Integer.parseInt(in.readLine());

        for (int caseNum = 1; caseNum <= numCases; caseNum ++) {

            numSpaces = Integer.parseInt(in.readLine());
            field = in.readLine().toCharArray();
            numScarecrows = 0;
            isProtected.clear();

            for (int i  = 0; i < numSpaces; i ++) {
                if (field[i] == fertile) {
                    //adding a scarecrow, which would go directly to the right
                    isProtected.set(i, i + 3);
                    numScarecrows ++;
                    //next skip ahead by 2 spaces because the next 2 spaces are already protected
                    i += 2;
                }
            }

            //account for possible last space being unprotected
            if (field[numSpaces - 1] == fertile && !isProtected.get(numSpaces - 1))
                numScarecrows ++;

            report.append("Case ").append(caseNum).append(": ").append(numScarecrows).append("\n");
        }

        System.out.print(report);

    }
}
