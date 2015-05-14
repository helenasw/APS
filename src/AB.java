/**
 * Created by hwentworth23 on 3/30/15.
 */
public class AB {

    private static int count = 0;

    public static int choosePage(int[] percentages) {
        count ++;
        int countMod, sum = 0;
        countMod = count % 100;
        for (int i = 0; i < percentages.length; i ++) {
            sum += percentages[i];
            if (countMod < sum) {
                return i;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] numPossibilites = { 25, 25, 50 };

        int[] values = new int[numPossibilites.length];
        for (int i = 0; i <= 50; i ++) {
            values[choosePage(numPossibilites)] += 1;
        }

        for (int i = 0; i < numPossibilites.length; i ++) {
            System.out.println(i + ": " + values[i]);
        }
    }
}
