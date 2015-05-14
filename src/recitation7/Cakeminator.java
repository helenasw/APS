package recitation7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Cakeminator {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int numRows = Integer.parseInt(st.nextToken());
        int numCols = Integer.parseInt(st.nextToken());
        int[] cols = new int[numCols];
        //-1 if invalid
        int cake = 0;
        int eachCol = numRows;
        boolean hasStrawberry = false;
        for (int i = 0; i < numRows; i ++) {
            char[] spots = in.readLine().trim().toCharArray();
            for (int j = 0; j < numCols; j ++) {
                if (spots[j] == 'S') {
                    cols[j] = -1;
                    hasStrawberry = true;
                }
            }
            if (!hasStrawberry) {
                eachCol --;
                cake += numCols;
            } else {
                hasStrawberry = false;
            }
        }
        for (int i = 0; i < numCols; i ++)
            if (cols[i] != -1) {
                cake += eachCol;
            }

        System.out.println(cake);
    }
}
