package homework7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Cownting like seriously are you kidding me.
 */
public class Cowculations {

    static HashMap<Character, Character> numsToCowNums = new HashMap<Character, Character>();
    static HashMap<Character, Character> cowNumsToNums = new HashMap<Character, Character>();

    public static void main(String[] args) throws IOException {

        numsToCowNums.put('0', 'V');
        numsToCowNums.put('1', 'U');
        numsToCowNums.put('2', 'C');
        numsToCowNums.put('3', 'D');

        cowNumsToNums.put('V', '0');
        cowNumsToNums.put('U', '1');
        cowNumsToNums.put('C', '2');
        cowNumsToNums.put('D', '3');

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numTablets = Integer.parseInt(in.readLine().trim());
        StringBuilder report = new StringBuilder("COWCULATIONS OUTPUT\n");

        for (int t = 0; t < numTablets; t ++) {
            String cowNum1 = in.readLine().trim();
            String cowNum2 = in.readLine().trim();
            String num1 = parseCowNum(cowNum1);
            String num2 = parseCowNum(cowNum2);

            for (int o = 0; o < 3; o ++) {
                char op = in.readLine().charAt(0);

                switch (op) {
                    case 'A':
                        num2 = add(num1, num2);
                        break;
                    case 'R':
                        num2 = shiftRight(num2);
                        break;
                    case 'L':
                        num2 = shiftLeft(num2);
                        break;
                    default:
                        break;
                }
            }
            String result = in.readLine().trim();
            result = parseCowNum(result);
            if (Integer.parseInt(result, 4) == Integer.parseInt(num2, 4)) {
                report.append("YES\n");
            } else {
                report.append("NO\n");
            }

        }


        report.append("END OF OUTPUT\n");
        System.out.print(report);
    }

    public static String add(String num1, String num2) {
        int sum = Integer.parseInt(num1, 4) + Integer.parseInt(num2, 4);
        return Integer.toString(sum, 4);
    }

    public static String shiftLeft(String num) {
        String shifted = num + "0";
        return shifted;
    }

    public static String shiftRight(String num) {
        String shifted = '0' + num.substring(0, num.length() - 1);
        return shifted;
    }

    public static String parseCowNum(String cowNum) {
        StringBuilder num = new StringBuilder();
        char[] chars = cowNum.toCharArray();
        for (char c : chars) {
            num.append(cowNumsToNums.get(c));
        }

        return num.toString();
    }
}
