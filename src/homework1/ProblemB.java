package homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 * Random notes for future me:
 * You have to process everything even if there is a conflict just so that you get to the right start of the
 * next test case (ie don't break when you find a conflict and don't skip processing repeating even if you already
 * found an error). Also, make sure that you're still checking for termination lines.
 */
public class ProblemB {

    private static BitSet schedule;

    public static void main(String[] args) throws IOException {
        final int limit = 1000000;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        schedule = new BitSet(limit);

        int oneTasks, repTasks, start, end, interval;
        boolean hasConflict = false;

        oneTasks = Integer.parseInt(st.nextToken());
        repTasks = Integer.parseInt(st.nextToken());

        //terminating line will contain 0 0
        while (oneTasks != 0 || repTasks != 0) {

            //process one time tasks
            for (int i = 0; i < oneTasks; i ++) {

                st = new StringTokenizer(in.readLine());
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());

                //make sure we're not supposed to terminate
                if (start == 0 && end == 0) {
                    printResults(hasConflict);
                    return;
                }

                if (!hasConflict) //see notes for justification
                    if (hasTime(start, end))
                        schedule.set(start, end);
                    else
                        hasConflict = true;

            }


            //process multiple tasks
            for (int i = 0; i < repTasks; i++) {
                st = new StringTokenizer(in.readLine());
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                interval = Integer.parseInt(st.nextToken());

                //make sure we're not supposed to terminate
                if ((start == 0 && end == 0) || (end == 0 && interval == 0)) {
                    printResults(hasConflict);
                    return;
                }

                //no point in checking each occurrence if there's already a conflict
                if (hasConflict)
                    continue;
                while (end < limit) {
                    if (hasTime(start, end))
                        schedule.set(start, end);
                    else
                        hasConflict = true;
                    start += interval;
                    end += interval;
                }

                if (start < limit) {
                    if (hasTime(start, limit))
                        schedule.set(start, limit);
                    else
                        hasConflict = true;
                }
            }

            printResults(hasConflict);

            //get ready for next case
            st = new StringTokenizer(in.readLine());
            oneTasks = Integer.parseInt(st.nextToken());
            repTasks = Integer.parseInt(st.nextToken());

            schedule.clear();
            hasConflict = false;
        }
    }

    private static boolean hasTime(int start, int end) {
        return schedule.get(start, end).isEmpty();
    }

    private static void printResults(boolean hasConflict) {
        if (hasConflict)
            System.out.println("CONFLICT");
        else
            System.out.println("NO CONFLICT");
    }
}
