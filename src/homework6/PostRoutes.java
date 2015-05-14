    package homework6;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.PriorityQueue;

    /**
     * Eulerian cycles/paths things.
     */
    public class PostRoutes {

        public static void main(String[] args) throws IOException {

            StringBuilder report = new StringBuilder();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String line;
            char[] street;
            Intersection[] oddInts = new Intersection[2];
            int numOdd = 0;
            HashMap<Character, Intersection> intersections = new HashMap<Character, Intersection>();
            Intersection i, j;
            long routeLength = 0;

            line = in.readLine();
            while (line != null && !line.isEmpty()) {

                //first read in graph and add up street lengths
                while (!line.equals("deadend")) {

                    street = line.toCharArray();

                    if (!intersections.containsKey(street[0]))
                        intersections.put(street[0], new Intersection(street[0]));
                    if (!intersections.containsKey(street[street.length - 1]))
                        intersections.put(street[street.length - 1], new Intersection(street[street.length - 1]));

                    i = intersections.get(street[0]);
                    j = intersections.get(street[street.length - 1]);
                    i.addStreet(j, street.length);
                    j.addStreet(i, street.length);

                    routeLength += street.length;
                    line = in.readLine();
                }

                //check if any intersections are of odd degree
                //if yes, then there are 2. find shortest path between them, add length to total sum.
                for (Character c : intersections.keySet()) {
                    if (intersections.get(c).hasOddDegree()) {
                        oddInts[numOdd] = intersections.get(c);
                        numOdd ++;
                        if (numOdd == 2)
                            routeLength += findShortestPath(oddInts[0], oddInts[1]);
                    }
                }

                report.append(routeLength).append("\n");

                //clean up for next test case
                routeLength = 0;
                numOdd = 0;
                intersections.clear();
                line = in.readLine();
            }

            System.out.print(report);

        }

        public static long findShortestPath(Intersection from, Intersection to) {

            from.distance = 0;
            PriorityQueue<Intersection> queue = new PriorityQueue<Intersection>();
            queue.add(from);

            while (!queue.isEmpty()) {
                Intersection i = queue.poll();
                if (i.equals(to))
                    break;

                for (Street s : i.streets()) {
                    long newDist = i.distance + s.length;

                    if (newDist < s.to.distance) {
                        s.to.distance = newDist;
                        queue.add(s.to);
                    }
                }
            }

            return to.distance;
        }
    }

    class Intersection implements Comparable<Intersection> {
        char id;
        long distance = Long.MAX_VALUE;
        private ArrayList<Street> streets = new ArrayList<Street>();

        public Intersection(char id) {
            this.id = id;
        }

        public ArrayList<Street> streets() { return streets; }

        public void addStreet(Intersection to, int length) {
            Street street = new Street(length, to);
            streets.add(street);
        }

        public boolean hasOddDegree() { return streets.size() % 2 == 1; }

        @Override
        public int compareTo(Intersection o) {
            return Long.compare(this.distance, o.distance);
        }
    }

    class Street {
        int length;
        Intersection to;

        public Street(int length, Intersection to) {
            this.length = length;
            this.to = to;
        }
    }