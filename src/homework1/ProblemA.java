package homework1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Helena on 2/6/2015.
 * Got it
 */
public class ProblemA {

    public static void main(String[] args) throws IOException {

       HashMap<String, HashSet<String>> dict = new HashMap<String, HashSet<String>>();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String line = in.readLine();
        StringTokenizer st;

        while (!line.equals("#")) {
            st = new StringTokenizer(line);
            while (st.hasMoreTokens()) {
                String word = st.nextToken();
                String key = getKey(word);
                HashSet<String> words = dict.get(key);
                if (words == null) {
                    words = new HashSet<String>();
                    words.add(word);
                    dict.put(key, words);
                } else {
                    words.add(word);
                }
            }

            line = in.readLine();
        }

        ArrayList<String> ananagrams = new ArrayList<String>();
        for (String key : dict.keySet()) {
            if (dict.get(key).size() == 1)
                ananagrams.add(dict.get(key).iterator().next());
        }

        Collections.sort(ananagrams);

        for (String ananagram : ananagrams) {
            System.out.println(ananagram);
        }
    }

    private static String getKey(String word) {
        char[] chars = word.toLowerCase().toCharArray();
        Arrays.sort(chars);
        StringBuilder key = new StringBuilder();
        for (char c : chars)
            key.append(c);
        return key.toString();
    }
}
