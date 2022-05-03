import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Clustering {
    public static double clustering(int[] x, int[] y, int k) {
        Pointer[] ary = new Pointer[x.length];
        QUWC union = new QUWC(ary.length);
        HashSet<Edge> set = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < ary.length; i++) {
            ary[i] = new Pointer(x[i], y[i], i);
        }
        for (int i = 0; i < ary.length; i++) {
            for (int j = i; j < ary.length; j++) {
                Edge e = new Edge(ary[i], ary[j]);
                if (!set.contains(e)) {
                    set.add(e);
                    pq.add(e);
                }
            }
        }

        int count = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (!union.isConnected(edge.getStart().getGroup(), edge.getEnd().getGroup())) {
                count++;
                union.union(edge.getStart().getGroup(), edge.getEnd().getGroup());
                if (count > ary.length - k) {
                    // I AM THE GOD OF NESTED IF STATEMENTS
                    // FEAR MY WRATH
                    return edge.getDistance();
                }
            }
        }
        return -1; // pain if here
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
        }
        int k = scanner.nextInt();
        System.out.println(String.format("%.12f", clustering(x, y, k)));
    }
}

class Edge implements Comparable<Edge> {

    private double distance;
    private Pointer start, end;

    public Edge(Pointer start, Pointer end) {
        this.start = start;
        this.end = end;
        distance = start.distance(end);
    }
    public Pointer getStart() {
        return start;
    }
    public Pointer getEnd() {
        return end;
    }
    public double getDistance() {
        return distance;
    }

    public int compareTo(Edge other) {
        double x = getDistance() - other.getDistance();
        if (x>0)return 1;
        if (x<0)return -1;
        return 0;
    }
}

class QUWC {

    private final int[] aryOne;
    private final int[] aryTwo;

    public QUWC(int N) {
        aryOne = new int[N];
        aryTwo = new int[N];
        for (int i = 0; i < N; i++) {
            aryOne[i] = i;
            aryTwo[i] = 1;
        }
    }

    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }

    // Recursively dives for the root
    private int root(int i) {
        if (aryOne[i] != i) {
            return (root(aryOne[i]));
        }
        return i;
    }

    // Unionizes the working class by finding the roots and making them equal
    // How equal is a matter of debate
    public void union(int p, int q) {
        int i = root(p);
        int j = root(q);
        if (i == j)
            return;
        if (aryTwo[i] < aryTwo[j]) {
            aryTwo[j] += aryTwo[i];
            aryOne[i] = j;
        } else {
            aryTwo[i] += aryTwo[j];
            aryOne[j] = i;
        }
    }
}

class Pointer implements Comparable<Pointer> {
    private final int x;
    private final int y;
    private int group;

    public Pointer(int x, int y, int group) {
        this.x = x;
        this.y = y;
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public double distance(Pointer other) {
        return Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2));
    }

    public double distance(int xOther, int yOther) {
        return Math.sqrt(Math.pow(((double) x) - (double) xOther, 2) + Math.pow((double) y - (double) yOther, 2));
    }

    @Override
    public int compareTo(Pointer o) {
        double dist = distance(0, 0) - o.distance(0, 0);
        if (dist > 0) return 1;
        else if (dist < 0) return -1;
        return 0;
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}