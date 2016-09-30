package week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {
    private List<LineSegment> allSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("Arg to constructor is null !!");
        }

        Point[] copy = Arrays.copyOfRange(points, 0, points.length);
        Point[] clone;
        List<Point> tempList = null;
        allSegments = new ArrayList<LineSegment>();
        Arrays.sort(copy);

        for (int i = 0; i < copy.length - 1; i++) {
            if (copy[i].compareTo(copy[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        for (int i = 0; i < copy.length - 3; i++) {
            clone = Arrays.copyOfRange(copy, i + 1, copy.length);
            Arrays.sort(clone, copy[i].slopeOrder());
            double slope = -1;

            for (int j = 1; j < clone.length; j++) {
                if (tempList == null) {
                    tempList = new ArrayList<Point>();
                    tempList.add(copy[i]);
                    tempList.add(clone[j - 1]);
                    slope = clone[j - 1].slopeTo(copy[i]);
                }

                if (slope == clone[j].slopeTo(copy[i])) {
                    tempList.add(clone[j]);
                } else {
                    if (tempList.size() > 3) {
                        allSegments.add(new LineSegment(tempList.get(0),
                                tempList.get(tempList.size() - 1)));
                    }
                    tempList = null;
                }

            }
            if (tempList != null && tempList.size() > 3) {
                allSegments.add(new LineSegment(tempList.get(0), tempList
                        .get(tempList.size() - 1)));
            }
            tempList = null;

        }
    }

    public int numberOfSegments() {
        return allSegments.size();
    }

    public LineSegment[] segments() {
        if (allSegments == null) {
            return null;
        } else {
            return allSegments.toArray(new LineSegment[allSegments.size()]);
        }
    }
}