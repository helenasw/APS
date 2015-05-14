package homework9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FewestFlops {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder report = new StringBuilder();

        int numCases = Integer.parseInt(in.readLine().trim());

        for (int c = 0; c < numCases; c ++) {

            StringTokenizer st = new StringTokenizer(in.readLine());
            int segmentSize = Integer.parseInt(st.nextToken());

                String strn = st.nextToken();
                ArrayList<HashSet<Character>> segments = new ArrayList<HashSet<Character>>();
                int numSegments = strn.length() / segmentSize;

                for (int i = 0; i < numSegments; i ++) {
                    char[] seg = strn.substring(i * segmentSize, (i + 1) * segmentSize).toCharArray();
                    HashSet<Character> chars = new HashSet<Character>();
                    for (int j = 0; j < seg.length; j ++) {
                        chars.add(seg[j]);
                    }
                    segments.add(chars);
                }

                //segment number x last character in the current segment
                int[][] numBlocks = new int[numSegments][26];

                //The first column is just the number of chunks in that block.
                int tmpSize = segments.get(0).size();
                for (Character ch : segments.get(0)) {
                    numBlocks[0][ch - 'a'] = tmpSize;
                }

                for (int i = 1; i < numSegments; i ++) {
                    for (Character currLast : segments.get(i)) {
                        int tmp, min = Integer.MAX_VALUE;
                        for (Character prevLast : segments.get(i - 1)) {
                            tmp = numBlocks[i - 1][prevLast - 'a'] + segments.get(i).size();

                            if (segments.get(i).size() == 1) {
                                if (prevLast == currLast) {
                                    tmp --;
                                }
                            } else if (prevLast != currLast && segments.get(i).contains(prevLast)) {
                                tmp --;
                            }

                            if (tmp < min) {
                                min = tmp;
                            }
                        }
                        numBlocks[i][currLast - 'a'] = min;
                    }
                }

                report.append(minNonZero(numBlocks[numSegments - 1])).append('\n');

        }

        System.out.print(report);
    }

    private static int minNonZero(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            if ( i > 0 && i < min)
                min = i;
        }

        return min;
    }
}
