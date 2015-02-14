package homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Random Notes for Future Me:
 * Use Union Find Data Structures
 */
public class ProblemC {

    private static int[] representatives;

    public static void main(String[] args) throws IOException {

        int possibleReligions = 0, caseNum = 1;

        //given
        int numStudents, numRelations, studentI, studentJ;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = in.readLine();
        StringTokenizer st = new StringTokenizer(line);

        numStudents = Integer.parseInt(st.nextToken());
        numRelations = Integer.parseInt(st.nextToken());

        while (numStudents != 0 || numRelations != 0) {

            representatives = new int[numStudents + 1];

            for (int k = 1; k < numStudents + 1; k ++)
                representatives[k] = k;

            //process the relations
            for (int k = 0; k < numRelations; k ++) {
                line = in.readLine();
                st = new StringTokenizer(line);

                studentI = Integer.parseInt(st.nextToken());
                studentJ = Integer.parseInt(st.nextToken());

                if (studentI == studentJ)
                    continue; //nothing to be done

                union(studentI, studentJ);
            }

            possibleReligions = 0;
            for (int k = 1; k < numStudents + 1; k ++)
                if (representatives[k] == k)
                    possibleReligions ++;

            System.out.println("Case " + caseNum++ + ": " + possibleReligions);

            //next case
            line = in.readLine();
            st = new StringTokenizer(line);
            numStudents = Integer.parseInt(st.nextToken());
            numRelations = Integer.parseInt(st.nextToken());
        }
    }

    private static void union(int studentI, int studentJ) {
        int iParent = find(studentI);
        int jParent = find(studentJ);
        representatives[iParent] = jParent;
    }

    private static int find(int student) {
        if (representatives[student] != student)
            representatives[student] = find(representatives[student]);
        return representatives[student];
    }
}
