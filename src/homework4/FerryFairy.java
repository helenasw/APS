package homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;

public class FerryFairy {

    static ArrayList<Integer> cars;
    static int[][] possibleCarsPort;
    static StringBuilder report;
    static BitSet path = new BitSet();

    public static void main(String[] args) throws IOException {

        final int UPPER_BOUND = 10000;
        int ferryLen, carLen, numCases, totalCarLen = 0, totalFerrySpace;
        String line;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        report = new StringBuilder();
        cars = new ArrayList<Integer>();

        numCases = Integer.parseInt(eatBlankLines(in));
        line = eatBlankLines(in);

        for (int i = 0; i < numCases; i ++) {
            if (line.isEmpty() || line == null)
                line = eatBlankLines(in);
            ferryLen = mToCm(Integer.parseInt(line.trim()));
            line = eatBlankLines(in);
            totalFerrySpace = 2 * ferryLen;

            while (line != null && !line.isEmpty() && !line.equals("0")) {
                if (totalCarLen < totalFerrySpace) { //if it's already too long then no reason to continue adding cars
                    carLen = Integer.parseInt(line.trim());
                    totalCarLen += carLen;
                    cars.add(carLen);
                }
                line = in.readLine();
            }
            cars.add(UPPER_BOUND); //buffer to avoid indexing out
            possibleCarsPort = new int[cars.size()][ferryLen + 1];

            //let's just put the first car portside, as our convention
            possibleCarsPort[0][ferryLen] = 1 + loadFerry(1, ferryLen - cars.get(0), ferryLen);
            path.set(0);

            //prepare report
            findPath(possibleCarsPort[0][ferryLen]);

            //reset before next case
            cars.clear();
            path.clear();
            totalCarLen = 0;

            if (line.equals("0"))
                break;
        }
        if (report.length() > 0)
            report.deleteCharAt(report.length() - 1);
        System.out.print(report);
    }

    private static int mToCm(int meters) {
        return meters * 100;
    }

    private static int loadFerry(int index, int portSpace, int starSpace) {

        int choosePort = 0, chooseStar = 0, carLen = cars.get(index);
        if (portSpace < carLen && starSpace < carLen)
            return 0;

        if (portSpace >= carLen) {
            choosePort = 1 + possibleCarsPort[index + 1][portSpace - carLen];
            if (choosePort == 1) //then it hasn't been set yet
                choosePort = 1 + loadFerry(index + 1, portSpace - carLen, starSpace);
        }

        if (starSpace >= carLen)
            chooseStar = 1 + loadFerry(index + 1, portSpace, starSpace - carLen);

        if (choosePort > chooseStar) {
            path.set(index);
            possibleCarsPort[index][portSpace] = choosePort;
            return choosePort;
        }

        return chooseStar;
    }

    private static void findPath(int totalCars) {

        final String PORT = "port\n", STARBOARD = "starboard\n";
        report.append(totalCars).append('\n');
        for (int i = 0; i < totalCars; i ++) {
            if (path.get(i))
                report.append(PORT);
            else
                report.append(STARBOARD);
        }
        report.append('\n');
    }

    private static String eatBlankLines(BufferedReader in) throws IOException {
        String line = in.readLine();
        while (line == null || line.isEmpty())
            line = in.readLine();
        return line;
    }

}
