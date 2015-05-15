import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numCases = Integer.parseInt(in.readLine().trim());
        for (int i = 0; i < numCases; i ++) {
            int numValues = Integer.parseInt(in.readLine().trim());
            StringTokenizer st = new StringTokenizer(in.readLine());
            int prev = Integer.parseInt(st.nextToken());
            int current = Integer.parseInt(st.nextToken());
            int greatestDiff = Math.abs(current - prev);
            int index = 2;
            prev = current;
            for (int j = 3; j <= numValues; j ++) {
                current = Integer.parseInt(st.nextToken());
                int diff = Math.abs(current - prev);
                if (diff > greatestDiff) {
                    greatestDiff = diff;
                    index = j;
                }
                prev = current;
            }

            System.out.println(index);
        }
    }
}
