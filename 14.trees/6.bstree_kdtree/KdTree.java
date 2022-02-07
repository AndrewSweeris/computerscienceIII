import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.ArrayList;


public class KdTree {

    private int size = 0;
    private Node root;
    private ArrayList<Point2D> range;
    private Point2D nearest;

    public static void main(String[] args) {                // unit testing of the methods (optional)
        // tested in PointSET.java
    }

    /**
     * returns if the size is equal to zero
     *
     * @return
     */
    public boolean isEmpty() {                      // is the set empty?
        return size == 0;
    }

    /**
     * returns the current size of the tree
     *
     * @return
     */
    public int size() {                      // number of points in the set
        return size;
    }

    /**
     * inserts a new node if not in the treeSet already the rectangles will encompass the whole area
     * and the point will lie inside
     *
     * @param p
     */
    public void insert(Point2D p) {
        checkIfNull(p);

        if (root != null) {
            insert(p, new RectHV(0, 0, 1, 1), root, true);
        } else {
            root = new Node(p);
            root.rect = new RectHV(0, 0, 1, 1);
            size++;
        }
    }

    /**
     * helper method that inserts a node recursively and updates the Rectangle with it -
     * alternatively a Rectangle could be passed as a parameter to free up space in the node class
     *
     * @param p
     * @param node
     * @param vertical vertical is current root and true being vertical line
     * @return
     */
    private Node insert(Point2D p, RectHV rect, Node node, boolean vertical) {
        if (node == null) {
            node = new Node(p);
            node.rect = rect;
            size++;
            return node;
        } else if (node.p.equals(p)) {
            return node;
        } else {
            if (!vertical) { // false
                if (node.p.y() <= p.y()) {
                    rect = new RectHV(rect.xmin(), node.p.y(), rect.xmax(), rect.ymax());
                    node.right = insert(p, rect, node.right, !vertical);
                } else {
                    rect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.p.y());
                    node.left = insert(p, rect, node.left, !vertical);
                }
                return node;
            } else { // true
                if (node.p.x() <= p.x()) {
                    rect = new RectHV(node.p.x(), rect.ymin(), rect.xmax(), rect.ymax());
                    node.right = insert(p, rect, node.right, !vertical);
                } else {
                    rect = new RectHV(rect.xmin(), rect.ymin(), node.p.x(), rect.ymax());
                    node.left = insert(p, rect, node.left, !vertical);
                }
                return node;
            }
        }
    }

    /**
     * recursively ascertains if a point is in the tree
     *
     * @param p
     * @return
     */
    public boolean contains(Point2D p) {           // does the set contain point p?
        checkIfNull(p);
        if (root == null) return false;
        else {
            return contains(p.x(), p.y(), root, true);
        }
    }

    /**
     * helper method to recursively determine if a point is in the tree by checking left or right
     * based on vertical == true for x points and vertical == false for y points
     *
     * @param x
     * @param y
     * @param node
     * @param vertical
     * @return
     */
    private boolean contains(double x, double y, Node node, boolean vertical) {
        if (node == null) {
            return false;
        } else if (node.p.x() == x && node.p.y() == y) {
            return true;
        } else {
            if ((vertical && node.p.x() > x) || (!vertical && node.p.y() > y)) {
                return contains(x, y, node.left, !vertical);
            } else {
                return contains(x, y, node.right, !vertical);
            }
        }
    }

    /**
     * draws all points to standard draw
     */
    public void draw() {
        StdDraw.setPenRadius(0.01);
        draw(root, true);
    }

    /**
     * method recursively draws nodes and red vertical lines and blue horizontal lines based on
     * vertical
     *
     * @param node
     * @param vertical - true draws vertical red line inside node's rectangle and blue horizontal
     *                 line inside node's rectangle otherwise
     */
    private void draw(Node node, boolean vertical) {
        if (node != null) {
            if (vertical) {
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(Color.RED);
                StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
            } else {
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
            }
            draw(node.left, !vertical);
            draw(node.right, !vertical);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.02);
            StdDraw.point(node.p.x(), node.p.y());
        }
    }

    /**
     * returns a data structure of all points that are inside the rectangle
     *
     * @param rect
     * @return
     */
    public Iterable<Point2D> range(RectHV rect) {
        checkIfNull(rect);
        if (root != null) {
            range = new ArrayList<Point2D>();
            range(rect, root, range);
            return range;
        }
        return null;
    }

    /**
     * helper method to add points that are inside the given rectangle
     *
     * @param rect
     */
    private void range( RectHV rect, Node node, ArrayList<Point2D> q) {
        if (node == null)
            return;
        if (rect.contains(node.p))
            q.add(node.p);
        if (node.left!=null&&rect.intersects(node.left.rect))
            range(rect, node.left, q);
        if (node.right!=null&&rect.intersects(node.right.rect))
            range(rect, node.right, q);
    }

    /**
     * a nearest neighbor in the set to point p; null if the set is empty
     *
     * @param p
     * @return
     */
    public Point2D nearest(Point2D p) {
        checkIfNull(p);
        if (root != null) {
            nearest = root.p;
            nearest(p,root, true);
            return nearest;
        }
        return null;
    }

    /**
     * helper method to recursively calculate closest point in tree to p with pruning
     *
     * @param p
     * @param node
     * @param vertical
     */
    private void nearest(Point2D p, Node node, boolean vertical) {
        if (node != null) {
            if (calcDistance(node.p, p) < calcDistance(nearest, p)) {
                nearest = node.p;
            }
            nearest(p, node.left, !vertical);
            nearest(p, node.right, !vertical);
        }
     }
    private Node nearest(Node n, Node closest, Point2D p, boolean orientation) {
        Node c = closest;

        if (n == null) {
            return c;
        }

        double minDistance = p.distanceSquaredTo(c.p);
        double thisDistance = p.distanceSquaredTo(n.p);

        if (n.rect.distanceSquaredTo(p) >= minDistance) {
            return c;
        }

        if (thisDistance < minDistance) {
            c = n;
        }

        // we choose the point that is on the same side of the splitting line
        // to search first, as it will help prune more parts of the tree
        boolean isCoordLessThan = false;
        if (orientation) {
            isCoordLessThan = p.x() < n.p.x();
        } else {
            isCoordLessThan = p.y() < n.p.y();
        }

        if (isCoordLessThan) {
            c = nearest(n.left, c, p, !orientation);
            c = nearest(n.right, c, p, !orientation);
        } else {
            c = nearest(n.right, c, p, !orientation);
            c = nearest(n.left, c, p, !orientation);
        }

        return c;
    }
    private double calcDistance(Point2D pointOne, Point2D pointTwo) {
        double top = Math.abs(pointOne.y() - pointTwo.y());
        double bottom = Math.abs(pointOne.x() - pointTwo.x());
        double distance = Math.sqrt(Math.pow(top, 2) + Math.pow(bottom, 2));
        return Math.abs(distance);
    }

    /**
     * throws an exception if a null reference is passed
     *
     * @param o
     */
    private void checkIfNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * inner class that provides the functionality of a Node with references left/right, a point and
     * a RectHV object that is optional
     */
    private class Node {

        Node left, right;
        Point2D p;
        RectHV rect;

        public Node(Point2D p) {
            this.p = p;
        }
    }
}