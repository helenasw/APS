import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numCases = Integer.parseInt(in.readLine().trim());
        StringBuilder report = new StringBuilder();
        HashMap<String, Product> products = new HashMap<String, Product>();

        for (int i = 0; i < numCases; i ++) {
            int numTransactions = Integer.parseInt(in.readLine().trim());
            for (int j = 0; j < numTransactions; j ++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                Product p = new Product(st.nextToken(), Integer.parseInt(st.nextToken()));
                if (products.containsKey(p.name)) {
                    products.get(p.name).addToAmount(p.amount);
                } else {
                    products.put(p.name, p);
                }
            }

            ArrayList<Product> ps = new ArrayList<Product>();
            ps.addAll(products.values());
            Collections.sort(ps);
            for (Product p: ps) {
                report.append(p.name).append(" ").append(p.amount).append("\n");
            }
            products.clear();
            report.append('\n');

        }

        report = report.deleteCharAt(report.length() - 1);

        System.out.print(report);
    }

    static class Product implements Comparable<Product> {
        int amount;
        String name;

        public Product(String name, int amount) {
            this.amount = amount;
            this.name = name;
        }

        public void addToAmount(int amount) {
            this.amount += amount;
        }

        @Override
        public int compareTo(Product o) {
            int result = (-1) * Integer.compare(Math.abs(amount), Math.abs(o.amount));
            if (result == 0)
                return name.compareTo(o.name);
            return result;
        }
    }
}
