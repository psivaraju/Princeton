
import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point to
     * standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument point
     *         (x0 = x1 and y0 = y1); a negative integer if this point is less
     *         than the argument point; and a positive integer if this point is
     *         greater than the argument point
     */
    public int compareTo(Point that) {
        if (that == null) {
            throw new NullPointerException();
        }
        int diffY = this.y - that.y;
        return diffY == 0 ? (this.x - that.x) : diffY;
    }

    /**
     * Returns the slope between this point and the specified point. Formally,
     * if the two points are (x0, y0) and (x1, y1), then the slope is (y1 - y0)
     * / (x1 - x0). For completeness, the slope is defined to be +0.0 if the
     * line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical; and
     * Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that == null) {
            throw new NullPointerException();
        }
        int diffY = (this.y - that.y);
        int diffX = (this.x - that.x);
        final double slope;
        if (diffY == 0 && diffX == 0) {
            slope = Double.NEGATIVE_INFINITY;
        }
        else if (diffY == 0) {
            slope = +0.0;
        }
        else if (diffX == 0) {
            slope = Double.POSITIVE_INFINITY;
        }
        else {
            slope = ((double) diffY / (double) diffX);
        }

        return slope;
    }

    /**
     * Compares two points by the slope they make with this point. The slope is
     * defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            public int compare(Point pointa, Point pointb) {
                if (pointa == null || pointb == null) {
                    throw new NullPointerException();
                }
                double slopea = slopeTo(pointa);
                double slopeb = slopeTo(pointb);
                final int retVal;

                if (Math.abs(slopea - slopeb) < 0.0001) {
                    retVal = 0;
                }
                else if (slopea > slopeb) {
                    retVal = 1;
                }
                else {
                    retVal = -1;
                }
                return retVal;
            }
        };

    }

}
