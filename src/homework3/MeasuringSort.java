package homework3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MeasuringSort {

    static ArrayList<Integer> values = new ArrayList<Integer>();

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int length, nextVal, numSwaps = 0;
        StringBuilder report = new StringBuilder();

        length = Integer.parseInt(in.readLine().trim());

        while (length != 0) {

            //add the first one fo freeeee
            values.add(Integer.parseInt(in.readLine().trim()));

            for (int i = 1; i < length; i++) {
                nextVal = Integer.parseInt(in.readLine().trim());
                values.add(nextVal);

                if (nextVal < values.get(i - 1)) {
                    //the next value will not preserve sortedness
                    numSwaps += (i - indexOfNextSmaller(nextVal));
                    Collections.sort(values);
                }
                //otherwise, it's still sorted and we don't need to do any swaps
            }

            report.append(numSwaps).append("\n");

            //reset everything
            values.clear();
            numSwaps = 0;
            length = Integer.parseInt(in.readLine().trim());
        }

        System.out.print(report);
    }

    private static int indexOfNextSmaller(int val) {

        //midVal and mid will never retain these values because I know how I'm calling it
        int mid = 0, midVal, start = 0, end = values.size() - 2;

        while (start < end) {
            mid = (start + end) / 2;
            midVal = values.get(mid);
            if (values.get(start) == val)
                return start;
            else if (midVal == val)
                return mid;
            else if (values.get(end) == val)
                return end;
            else if (midVal > val) {
                if (end == mid)
                    return start < end ? start : end;
                end = mid;
            } else {
                if (start == mid)
                    return start < end ? start : end;
                start = mid;
            }
        }

        return mid;
//        while (start < end && start != mid) {
//            mid = (start + end) / 2;
//            midVal = values.get(mid);
//
//            if (midVal < val)
//                start = mid; //mid might be the next smallest, don't exclude
//            else if (midVal > val)
//                end = mid - 1;
//            else {
//                //then we need to account for duplicates, and get the last index where midVal occurs
//                while (midVal == val && mid < values.size() - 1)
//                    midVal = values.get(++ mid);
//
//                return mid - 1; //index of last repeating val
//            }
//        }
//
//        //midVal should be pretty close to the val by now, so just loop until we get the next smallest
//        if (midVal < val) {
//            while (midVal < val && mid < values.size() - 1)
//                midVal = values.get(++ mid);
//            return mid - 1;
//        } else if (midVal > val) {
//            while (midVal > val && mid > 0)
//                midVal = values.get(-- mid);
//            return mid;
//        } else {
//            while (midVal == val && mid < values.size() - 1)
//                midVal = values.get(++ mid);
//            return mid - 1; //index of last repeating val
//        }

    }
}
