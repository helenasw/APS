package recitation2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Draygons {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int kirito, numDraygons;
        StringTokenizer st = new StringTokenizer(in.readLine());

        kirito = Integer.parseInt(st.nextToken());
        numDraygons = Integer.parseInt(st.nextToken());

        PriorityQueue<Draygon> draygons = new PriorityQueue<Draygon>();

        for (int i = 0; i < numDraygons; i ++) {
            st = new StringTokenizer(in.readLine());
            draygons.add(new Draygon(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        boolean can = true;
        for (int i = 0; i < numDraygons; i ++) {
            Draygon d = draygons.poll();
            if (d.strength < kirito) {
                kirito += d.bonus;
            } else {
                can = false;
            }
        }

        if (can) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    static class Draygon implements Comparable<Draygon> {
        int strength;
        int bonus;

        public Draygon(int strength, int bonus) {
            this.strength = strength;
            this.bonus = bonus;
        }

        @Override
        public int compareTo(Draygon o) {
            return Integer.compare(strength, o.strength);
        }
    }
}
