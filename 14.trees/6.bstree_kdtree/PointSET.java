import edu.princeton.cs.algs4.In;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class PointSET {

    private Set<Point2D> set;

    public PointSET() {                     // construct an empty set of points
        set = new TreeSet<>();
    }

    public boolean isEmpty() {              // is the set empty?
        return set.isEmpty();
    }

    public int size() {                      // number of points in the set 
        return set.size();
    }

    public void insert(Point2D p) {             // add the point to the set (if it is not already in the set)
        checkIfNull(p);
        if (!set.contains(p)) {
            set.add(p);
        }
    }

    public boolean contains(Point2D p) {           // does the set contain point p?
        checkIfNull(p);
        return set.contains(p);
    }

    public void draw() {                    // draw all points to standard draw with p.draw()
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        Iterator<Point2D> it = set.iterator();
        Point2D point;
        while (it.hasNext()) {
            drawPoint(it.next());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {           // all points that are inside the rectangle
        checkIfNull(rect);
        Set<Point2D> list = new TreeSet<>();
        Iterator<Point2D> it = set.iterator();
        Point2D point;
        while (it.hasNext()) {
            point = it.next();
            if (rect.contains(point)) list.add(point);
        }
        return list;
    }

    public Point2D nearest(Point2D p) {           // a nearest neighbor in the set to point p; null if the set is empty
        checkIfNull(p);
        if (!set.isEmpty()) {
            Iterator<Point2D> it = set.iterator();
            Point2D point, closestPoint;
            if (it.hasNext()) {
                closestPoint=it.next();
                while (it.hasNext()) {
                    point = it.next();
                    if (closestPoint==null||calcDistance(point,p)<calcDistance(closestPoint,p)) {
                        closestPoint=point;
                    }
                }
                return closestPoint;
            }
        }
        return null;
    }
    private double calcDistance(Point2D pointOne, Point2D pointTwo) {
        double top = pointOne.y()-pointTwo.y();
        double bottom = pointOne.x()-pointTwo.x();
        double distance = Math.sqrt(Math.abs(Math.pow(top,2)+Math.pow(bottom, 2)));
        return Math.abs(distance);
    }

    private void checkIfNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {                // unit testing of the methods (optional) 
        String filename = "circle10.txt";   // args[0];
        In in = new In(filename);

        StdDraw.enableDoubleBuffering();

        // initialize the data structures with N points from standard input
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            kdtree.insert(p);
            brute.insert(p);
        }
        RectHV rectOne = new RectHV(0,0,.3,.3);
        // System.out.println(brute.range(rectOne).toString());
        brute.draw();
        kdtree.draw();
        StdDraw.show();
    }
    private static void drawPoint(Point2D point) {
        StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.filledCircle(point.x(),point.y(),0.01);
    }
}
