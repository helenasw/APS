package homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;

public class FerryFairy {

    static ArrayList<Integer> cars;
    static int[][] portSide;
    static StringBuilder report;

    public static void main(String[] args) throws IOException {

        final int UPPER_BOUND = 10000;
        int ferryLen, carLen, numCases, totalCarLen = 0, totalFerrySpace, numCars;
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

            cars.add(0); //buffer because we're going to do 1-based indexing
            while (line != null && !line.isEmpty() && !line.equals("0")) {
                if (totalCarLen < totalFerrySpace) { //if it's already too long then no reason to continue adding cars
                    carLen = Integer.parseInt(line.trim());
                    totalCarLen += carLen;
                    cars.add(carLen);
                }
                line = in.readLine();
            }
            cars.add(UPPER_BOUND); //buffer to avoid indexing out

            numCars = loadFerry(ferryLen);
            findPath(numCars, ferryLen);

            //reset before next case
            cars.clear();
            totalCarLen = 0;

        }
        if (report.length() > 0)
            report.deleteCharAt(report.length() - 1);
        System.out.print(report);
    }

    private static int mToCm(int meters) {
        return meters * 100;
    }

    private static int loadFerry(int ferryLen) {

        portSide = new int[cars.size()][ferryLen + 1];
        int[] starSpace = new int[ferryLen + 2];
        int[] prevStarSpace = new int[ferryLen + 2];
        int numCars, newStarSpace;
        boolean hasValid = false;

        //set up the array that stores the space on the other side
        Arrays.fill(prevStarSpace, ferryLen);

        //set up the first row
        Arrays.fill(portSide[0], -1);
        portSide[0][ferryLen] = 0;

        //set up the table
        for (int car = 1; car < cars.size(); car ++) {
            for (int space = ferryLen; space >= 0; space --) {

                newStarSpace = prevStarSpace[space];
                numCars = -1;
                //look above to see if we can take that value and add current car to other side
                if (portSide[car - 1][space] != -1
                        && prevStarSpace[space] >= cars.get(car)) {
                    numCars = portSide[car - 1][space];
                    newStarSpace -= cars.get(car);
                    hasValid = true;
                }


                //look to the right and above to see if we can keep this car and then the other side space stays the same
                if (space + cars.get(car) <= ferryLen
                        && portSide[car - 1][space + cars.get(car)] != -1) {
                    hasValid = true;
                    if (numCars == 1 + portSide[car - 1][space + cars.get(car)]) {
                        //if putting the car on the other side and taking it is equally good, then maximize balance
                        newStarSpace = Math.max(newStarSpace, prevStarSpace[space + cars.get(car)]);
                    } else if (numCars < 1 + portSide[car - 1][space + cars.get(car)]) {
                        //if this is better than putting it on the other side, keep track of that
                        numCars = 1 + portSide[car - 1][space + cars.get(car)];
                        newStarSpace = prevStarSpace[space + cars.get(car)];
                    }
                    //in the case that numCars is greater, then we already put the other car on the other side and that was better
                }

                portSide[car][space] = numCars;
                starSpace[space] = newStarSpace;
            }

            if (hasValid) {
                for (int i = 0; i < starSpace.length; i++)
                    prevStarSpace[i] = starSpace[i];
                hasValid = false;
            } else {
                //if there were no valid moves for this car, then we're done
                return car - 1;
            }
        }

        return 0;
    }

    private static void findPath(int totalCars, int ferryLen) {

        final String PORT = "port\n", STARBOARD = "starboard\n";
        String[] path = new String[totalCars + 1];
        int spaceI = 0, carI = totalCars, current, above, right;
        report.append(totalCars).append('\n');
        //find the first valid entry at car index, then traverse the table to find a valid path

        //travel to the first valid entry
        while (portSide[carI][spaceI] == -1)
            spaceI ++;

        //repeatedly check above and then to the right and above until you find 0 in the first column
        while (portSide[carI][spaceI] != 0 && carI != 0) {
            current = portSide[carI][spaceI];
            above = portSide[carI - 1][spaceI];
            if (spaceI + cars.get(carI) <= ferryLen)
                right = portSide[carI - 1][spaceI + cars.get(carI)];
            else
                right = -1;
            //check above, if the same, then the last move was to send to other side
            if (current - 1 == right) {
                //move to the right and above
                path[carI] = PORT;
                spaceI += cars.get(carI);
                carI --;
            } else {
                path[carI] = STARBOARD;
                carI --;
            }
        }

        while (carI > 0) {
            path[carI] = STARBOARD;
            carI --;
        }

        for (int i = 1; i < totalCars + 1; i ++) {
            report.append(path[i]);
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
