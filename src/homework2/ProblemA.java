package homework2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProblemA {

    public static void main(String[] args) throws IOException {

        StringBuilder report = new StringBuilder();
        int numMeters, caseNum = 1, res, index;
        String operation;
        Fenwick resistances;
        int[] meters;

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        numMeters = Integer.parseInt(st.nextToken());

        while (numMeters != 0) {

            report.append("Case ").append(caseNum++).append(":\n");

            //initialize potentiometers with initial resistances
            resistances = new Fenwick(numMeters);
            meters = new int[numMeters + 1];
            for (int i = 1; i < numMeters + 1; i ++) {
                //fenwick tree uses 1-based index
                res = Integer.parseInt(in.readLine());
                resistances.adjust(i, res);
                meters[i] = res;
            }

            //perform operations
            st = new StringTokenizer(in.readLine());
            operation = st.nextToken();
            while (!operation.equals("END")) {

                if (operation.equals("S")) {
                    //“S x r” - set potmeter x to r Ohms
                    index = Integer.parseInt(st.nextToken());
                    res = Integer.parseInt(st.nextToken());
                    resistances.adjust(index, res - meters[index]);
                    meters[index] = res;

                } else if (operation.equals("M")) {
                    //“M x y” - measure resistance between potmeter x and potmeter y
                    report.append(resistances.sumQuery(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");

                }

                //next operation
                st = new StringTokenizer(in.readLine());
                operation = st.nextToken();
            }

            report.append("\n");

            //next case
            st = new StringTokenizer(in.readLine());
            numMeters = Integer.parseInt(st.nextToken());
        }
        report.deleteCharAt(report.length() - 1);

        System.out.print(report);

    }

}

