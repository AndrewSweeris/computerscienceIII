import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class ConnectingPoints {
    private static double minimumDistance(int[] x, int[] y) {
        double result = 0.;
        //write your code here
        PointNode[] ary = new PointNode[x.length];
        for (int i = 0; i < x.length; i++) {
            ary[i]=new PointNode(x[i],y[i]);
        }
        // sorts based upon distance from init point
        Arrays.sort(ary, (one, two) -> comp(one.distance(ary[0]), two.distance(ary[0])));

        HashSet<PointNode> set = new HashSet<>();
        set.add(ary[0]);
        // System.out.println(Arrays.toString(ary)); // prints order of array to verify sort works
        for (int i = 1; i < ary.length; i++) {
            Iterator<PointNode> it = set.iterator();
            double dist = Double.MAX_VALUE;
            while (it.hasNext()) { // smallest distance determiner
                double temp = ary[i].distance(it.next());
                if (temp<dist)dist=temp;
            }
            result+=dist;
            set.add(ary[i]);
        }

        return result;
    }

    private static int comp(double x, double y) {
        if (x-y<0)return -1;
        if (x-y>0)return 1;
        return 0;
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
        System.out.println(minimumDistance(x, y));
    }
}

class PointNode implements Comparable<PointNode>{
    private final int x;
    private final int y;

    public PointNode(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public double distance(PointNode other) {
        return Math.sqrt(Math.pow(x - other.getX(),2) + Math.pow(y-other.getY(),2));
    }
    public double distance(int xOther, int yOther) {
        return Math.sqrt(Math.pow(((double)x) - (double)xOther,2) + Math.pow((double)y-(double)yOther,2));
    }

    @Override
    public int compareTo(PointNode o) {
        double dist = distance(0,0)-o.distance(0,0);
        if (dist>0)return 1;
        else if (dist<0) return -1;
        return 0;
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}