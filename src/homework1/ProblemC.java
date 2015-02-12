package homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Created by Helena on 2/6/2015.
 */
//TODO Wrong Answer
public class ProblemC {
    public static void main(String[] args) throws IOException {
        int caseNum = 1;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        StringTokenizer st = new StringTokenizer(line);
        int m, n, i, j, relI;

        //num students
        n = Integer.parseInt(st.nextToken());
        //num relations
        m = Integer.parseInt(st.nextToken());

        while (m + n != 0) {

            ArrayList<Set<Integer>> arr = new ArrayList<Set<Integer>>();

            //arraylist of sets, look for set that contains m or n and insert other number?
            for (int z = 0; z < m; z++) {

                relI = -1;
                line = in.readLine();
                st = new StringTokenizer(line);
                i = Integer.parseInt(st.nextToken());
                j = Integer.parseInt(st.nextToken());

                for (int k = 0; k < arr.size(); k++) {
                    if (arr.get(k).contains(i)) {
                        if (!arr.get(k).contains(j))
                            arr.get(k).add(j);
                        if (relI != -1) {
                            arr.get(relI).addAll(arr.get(k));
                            arr.remove(k);
                        }
                        relI = k;
                    } else if (arr.get(k).contains(j)) {
                        arr.get(k).add(i);
                        if (relI != -1) {
                            arr.get(relI).addAll(arr.get(k));
                            arr.remove(k);
                        }
                        relI = k;
                    }
                }

                if (relI == -1) { //then we didn't find a religion containing either i or j
                    Set<Integer> religion = new HashSet<Integer>();
                    religion.add(i);
                    religion.add(j);
                    arr.add(religion);
                }

            }

            //print arr.size
            System.out.println("Case " + caseNum++ + ": " + arr.size());

            //next case
            line = in.readLine();
            st = new StringTokenizer(line);
            //num students
            n = Integer.parseInt(st.nextToken());
            //num relations
            m = Integer.parseInt(st.nextToken());
        }
    }
}
