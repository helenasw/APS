import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class E {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numCases = Integer.parseInt(in.readLine().trim());
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < numCases; i ++) {
            int number = Integer.parseInt(in.readLine().trim());

            Node[][] p = new Node[number + 1][number + 1];
            for (int j = 1; j <= number; j ++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                for (int k = start; k <= end; k ++) {
                    p[j][k] = new Node(j, k);
                    for (int h = 1; h <= number; h ++) {
                        if (p[j - 1][h] != null) {
                            p[j - 1][h].addNeighbor(p[j][k]);
                        }
                    }
                }
            }

            Node begin = new Node(0,0);
            Node end = new Node(-1, -1);
            for (int j = 1; j <= number; j ++) {
                if (p[1][j] != null) {
                    begin.addNeighbor(p[1][j]);
                }
                if (p[number][j] != null) {
                    p[number][j].addNeighbor(end);
                }
            }

            Stack<Node> stack = new Stack<Node>();
            stack.push(begin);
            HashSet<Node> visited = new HashSet<Node>();

            boolean found = false;
            int[] path = new int[number + 1];
            while (!stack.isEmpty()) {
                Node u = stack.pop();
                path[u.p] = u.n;

                if (!visited.contains(u)) {
                    visited.add(u);
                    for (Node n : u.neighbors) {
                        if (n.equals(end)) {
                            found = true;
                            break;
                        }
                        stack.push(n);
                    }
                }
            }

            if (found) {
                for (int j = 1; j <= number; j++) {
                    report.append(path[j]).append(" ");
                }
                report.deleteCharAt(report.length() - 1).append("\n");
            } else {
                report.append("no solution\n");
            }

        }

        System.out.print(report);
    }

    static class Node {
        int n;
        int p;
        ArrayList<Node> neighbors = new ArrayList<Node>();

        public Node(int p, int n) {
            this.n = n;
        }

        public void addNeighbor(Node node) {
            neighbors.add(node);
        }

    }

}
