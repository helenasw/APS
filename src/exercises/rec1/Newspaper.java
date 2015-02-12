package exercises.rec1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Newspaper {
    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numTests = Integer.parseInt(nextLine(in));
        int numChars;
        int articleLength;
        int articlePrice;
        Map<Character, Integer> charPrices = new HashMap<Character, Integer>();

        //for each test
        for (int i = 0; i < numTests; i++) {

            numChars = Integer.parseInt(nextLine(in));
            charPrices.clear();
            //for each character put in map
            for (int j = 0; j < numChars; j++) {
                String line = nextLine(in);
                StringTokenizer st = new StringTokenizer(line);
                charPrices.put(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
            }

            articlePrice = 0;
            articleLength = Integer.parseInt(nextLine(in));
            //add up the price
            for (int j = 0; j < articleLength; j++) {
                String line = in.readLine();
                for (char c : line.toCharArray()) {
                    if (charPrices.containsKey(c))
                        articlePrice += charPrices.get(c);
                }
            }

            StringBuilder price = new StringBuilder(articlePrice + "$");
            if (price.length() >= 3)
                System.out.println(price.insert(price.length() - 3, "."));
            else if (price.length() == 2)
                System.out.println(price.insert(0, ".0"));
            else
                System.out.println(price.insert(0, ".00"));

        }

    }

    private static String nextLine(BufferedReader in) throws IOException{
        String line = "";
        while (line.isEmpty()) {
            line = in.readLine();
        }
        return line;
    }
}
