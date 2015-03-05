package homework4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by Helena on 3/1/2015.
 */
public class KAnon {

    public static void main(String[] args) throws IOException {

        //administration stuff
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder report = new StringBuilder();
        StringTokenizer st;

        //keeping my values in line
        HashMap<Integer, Integer> nodes = new HashMap<Integer, Integer>();
        //intervals, ordered by largest to smallest
        PriorityQueue<Interval> intervalQueue = new PriorityQueue<Interval>();
        Interval[] intervals;

        int numCases, numNodes, kDegree, nextNode, prevNodeVal, numDistinctVals;
        Interval interval;
        numCases = Integer.parseInt(in.readLine().trim());

        for (int i = 0; i < numCases; i ++) {
            st = new StringTokenizer(in.readLine());
            numNodes = Integer.parseInt(st.nextToken());
            kDegree = Integer.parseInt(st.nextToken());
            intervals = new Interval[numNodes - 1];

            st = new StringTokenizer(in.readLine());

            nextNode = Integer.parseInt(st.nextToken());
            nodes.put(nextNode, 1);
            prevNodeVal = nextNode;
            numDistinctVals = 1;

            for (int j = 0; j < numNodes; j ++) {
                nextNode = Integer.parseInt(st.nextToken());
                if (nodes.containsKey(nextNode))
                    nodes.put(nextNode, nodes.get(nextNode) + 1);
                else {
                    nodes.put(nextNode, 1);
                    interval = new Interval(numDistinctVals, numDistinctVals + 1, nextNode - prevNodeVal);
                    intervals[numDistinctVals] = interval;
                    intervalQueue.add(interval);

                    prevNodeVal = nextNode;
                    numDistinctVals ++;
                }
            }

            while (!intervalQueue.isEmpty()) {
                interval = intervalQueue.poll();
                if (nodes.get(interval.from) < kDegree && nodes.get(interval.to) < kDegree) {
                    //check behind from and in front of to, looking for the smaller interval weight
                    //change in the direction of the smaller interval weight
                    //if we're still less than k, then update the nodes, intervals, and intervalQueue
                } else if (nodes.get(interval.from) < kDegree) {

                }
            }

        }

    }
}

class Interval implements Comparable<Interval> {
    public int from;
    public int to;
    public int weight;

    public Interval(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Interval o) {
        if (weight < o.weight)
            return 1;
        else if (weight > o.weight)
            return -1;
        return 0;
    }
}