package homework6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Councilling {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int numCases = Integer.parseInt(in.readLine().trim());
        String line;
        StringBuilder report = new StringBuilder();

        HashMap<String, Vertex> parties = new HashMap<String, Vertex>();
        HashMap<String, Vertex> people = new HashMap<String, Vertex>();
        HashMap<String, Vertex> clubs = new HashMap<String, Vertex>();

        for (int c = 0; c < numCases; c ++) {
            line = eatEmptyLines(in);

            while (line != null && !line.trim().isEmpty()) {
                st = new StringTokenizer(line);
                Vertex person = new Vertex(st.nextToken());
                people.put(person.name, person);

                String partyName = st.nextToken();
                Vertex party;
                if (!parties.containsKey(partyName)) {
                    party = new Vertex(partyName);
                    parties.put(partyName, party);
                } else {
                    party = parties.get(partyName);
                }

                party.adjacencies.add(new Edge(1, party, person, true));

                while (st.hasMoreTokens()) {
                    String clubName = st.nextToken();
                    Vertex club;
                    boolean edgeExists = false;
                    if (!clubs.containsKey(clubName)) {
                        club = new Vertex(clubName);
                        clubs.put(clubName, club);
                    } else {
                        club = clubs.get(clubName);
                        for (Edge e : club.adjacencies) {
                            if (e.to.equals(person)) {
                                edgeExists = true;
                                break;
                            }
                        }
                    }

                    if (!edgeExists) {
                        person.adjacencies.add(new Edge(1, person, club, true));
                    }
                }

                line = in.readLine();
            }

            Graph graph = new Graph(parties, people, clubs);
            int flow = graph.edmondsKarp();
            if (flow != clubs.size()) {
                report.append("Impossible.\n\n");
            } else if (clubs.size() != 0) {
                HashMap<String, String> council = graph.getCouncilMembers();
                for (String p : council.keySet()) {
                    report.append(p).append(" ").append(council.get(p)).append('\n');
                }
                report.append('\n');
            }

            //clean up
            parties.clear();
            people.clear();
            clubs.clear();

        }
        report.deleteCharAt(report.length() - 1);
        System.out.print(report);
    }

    public static String eatEmptyLines(BufferedReader in) throws IOException {
        String line = in.readLine();
        while (line == null || line.trim().isEmpty())
            line = in.readLine();
        return line;
    }
}

class Graph {
    Vertex source;
    Vertex sink;
    HashMap<String, Vertex> parties;
    HashMap<String, Vertex> people;
    HashMap<String, Vertex> clubs;

    public Graph(HashMap<String, Vertex> parties, HashMap<String, Vertex> people, HashMap<String, Vertex> clubs) {

        this.parties = parties;
        this.people = people;
        this.clubs = clubs;
        source = new Vertex("source");
        sink = new Vertex("sink");

        int maxPerParty = (clubs.size() % 2 == 0) ? (clubs.size() / 2) - 1 : (clubs.size() / 2);

        for (String party : parties.keySet()) {
            source.adjacencies.add(new Edge(maxPerParty, source, parties.get(party), true));
        }

        for (String club : clubs.keySet()) {
            clubs.get(club).adjacencies.add(new Edge(1, clubs.get(club), sink, true));
        }
    }

    public int edmondsKarp() {
        int flow = 0;
        Queue<Vertex> queue = new LinkedList<Vertex>();
        while (true) {
            queue.offer(source);
            HashMap<Vertex, Edge> parent = new HashMap<Vertex, Edge>();
            while (!queue.isEmpty() && !parent.containsKey(sink)) {
                Vertex current = queue.poll();
                for (Edge e : current.adjacencies) {
                    if (!parent.containsKey(e.to) && e.capacity > e.f) {
                        parent.put(e.to, e);
                        queue.offer(e.to);
                    }
                }
            }
            if (!parent.containsKey(sink))
                break;
            int df = Integer.MAX_VALUE;
            for (Vertex u = sink; !u.equals(source); u = parent.get(u).from)
                df = Math.min(df, parent.get(u).capacity - parent.get(u).f);
            for (Vertex u = sink; !u.equals(source); u = parent.get(u).from) {
                parent.get(u).pushFlow(df);
            }
            flow += df;
            queue.clear();
        }
        return flow;
    }

    public HashMap<String, String> getCouncilMembers() {
        HashMap<String, String> council = new HashMap<String, String>();

        for (String p : people.keySet()) {
            Vertex person = people.get(p);
            for (Edge e : person.adjacencies) {
                if (e.f == 1 && clubs.containsKey(e.to.name)) {
                    council.put(p, e.to.name);
                }
            }
        }
        return council;
    }
}

class Vertex {
    String name;
    ArrayList<Edge> adjacencies = new ArrayList<Edge>();

    public Vertex(String name) {
        this.name = name;
    }
}

class Edge {
    int capacity;
    int f;
    Edge back;
    Vertex to;
    Vertex from;

    public Edge(int capacity, Vertex from, Vertex to, boolean createBack) {

        this.capacity = capacity;
        this.to = to;
        this.from = from;
        if (createBack) {
            back = new Edge(0, to, from, false);
            to.adjacencies.add(back);
            back.back = this;
        }
    }

    public void pushFlow(int flow) {
        f += flow;
        back.f -= flow;
    }
}