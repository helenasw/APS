package homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 * Created by Helena on 2/6/2015.
 */
public class ProblemB {

    public static void main(String[] args) throws IOException {
        final int limit = 1000000;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        BitSet schedule = new BitSet(limit);

        int oneTasks, repTasks, start, end, repInt, c;
        boolean hasConflict = false;

        oneTasks = Integer.parseInt(st.nextToken());
        repTasks = Integer.parseInt(st.nextToken());

        while (oneTasks + repTasks > 0) {

            //process one limit tasks
            for (int i = 0; i < oneTasks; i ++) {
                st = new StringTokenizer(in.readLine());
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                schedule.flip(start, end);
                c = schedule.get(start, end).cardinality();
                if (c != end - start) { //everything should be ones
                    hasConflict = true;
                    break;
                }

            }
            if (!hasConflict) { //no point in starting if there's already a conflict
                //process multiple tasks
                for (int i = 0; i < repTasks; i++) {
                    st = new StringTokenizer(in.readLine());
                    start = Integer.parseInt(st.nextToken());
                    end = Integer.parseInt(st.nextToken());
                    repInt = Integer.parseInt(st.nextToken());

                    while (end < limit) {
                        schedule.flip(start, end);
                        c = schedule.get(start, end).cardinality();
                        if (c != end - start) { //everything should be ones
                            hasConflict = true;
                            break;
                        }
                        start += repInt;
                        end += repInt;
                    }
                }
            }

            if (hasConflict) {
                System.out.println("CONFLICT");
            } else {
                System.out.println("NO CONFLICT");
            }

            //get ready for next case
            st = new StringTokenizer(in.readLine());
            oneTasks = Integer.parseInt(st.nextToken());
            repTasks = Integer.parseInt(st.nextToken());
            schedule.clear();
            hasConflict = false;
        }
    }
}
