package recitation6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Cahses {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> customersAtTime = new HashMap<String, Integer>();
        int numCustomers = Integer.parseInt(in.readLine().trim());
        int max = 0;
        for (int i = 0; i < numCustomers; i ++) {
            String time = in.readLine().trim();
            int num;
            if (customersAtTime.containsKey(time)) {
                num = customersAtTime.get(time) + 1;
            } else {
                num = 1;
            }
            customersAtTime.put(time, num);
            if (num > max)
                max = num;
        }

        System.out.println(max);
    }
}
