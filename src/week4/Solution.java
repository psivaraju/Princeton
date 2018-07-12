package week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Solution {
    public static class Point {
        int x;
        int y;

        Point() {
            x = 0;
            y = 0;
        }

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    private static class PointComparator implements Comparator<Point> {
        public int compare(Point a, Point b) {
            return a.y == b.y ? (a.x - b.x) : (a.y - b.y);
        }

    }

    private static double slopeOf(Point a, Point b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException();
        }

        final double slope;
        if (Math.abs(b.x - a.x) < 0.001 && Math.abs(b.y - a.y) < 0.001) {
            slope = Double.NEGATIVE_INFINITY;
        }
        else if (Math.abs(b.x - a.x) < 0.001) {
            slope = Double.POSITIVE_INFINITY;
        }
        else if (Math.abs(b.y - a.y) < 0.001) {
            slope = 0;
        }
        else {
            slope = ((double) (b.y - a.y) / (double) (b.x - a.x));
        }

        return slope;
    }

    public static int maxPoints(final Point[] points) {
        if (points.length < 3) {
            return points.length;
        }

        int maxSize = 0;
        List<Point> maxListRef = new ArrayList();
        List<Point> tempList = null;
        Map<Double, List<Point>> map = null;
        double slopeToCompare = 0.0;
        Point[] subArray = null;
        Point[] copyPoints = Arrays.copyOfRange(points, 0, points.length);
        Arrays.sort(copyPoints, new PointComparator());

        for (int i = 0; i < copyPoints.length; i++) {
            subArray = Arrays.copyOfRange(copyPoints, i + 1, copyPoints.length);
            map = new HashMap<>();
            for (int j = 0; j < subArray.length; j++) {
                slopeToCompare = slopeOf(copyPoints[i], subArray[j]);
                if ((tempList = map.get(slopeToCompare)) == null) {
                    tempList = new ArrayList<>();
                    tempList.add(copyPoints[i]);
                }

                tempList.add(subArray[j]);
                map.put(slopeToCompare, tempList);
            }

            List<Point> repeatedPoints = null;
            int numOfRepeatitions = 0;
            if ((repeatedPoints = map.get(Double.NEGATIVE_INFINITY)) != null) {
                // reduce by one since we are looking for repetetions only. The
                // original point is not considered a repetition.
                numOfRepeatitions = repeatedPoints.size() - 1;
            }
            for (Entry<Double, List<Point>> entry : map.entrySet()) {
                if (entry.getKey() != Double.NEGATIVE_INFINITY
                        && entry.getValue().size() + numOfRepeatitions > maxSize) {
                    maxSize = entry.getValue().size() + numOfRepeatitions;
                    maxListRef = entry.getValue();
                    for (int k = 0; k < numOfRepeatitions; k++) {
                        maxListRef.add(copyPoints[i]);
                    }
                }
                else if (entry.getKey() == Double.NEGATIVE_INFINITY && entry.getValue().size() > maxSize) {
                    maxSize = entry.getValue().size();
                    maxListRef = entry.getValue();
                }
            }

        }
        return maxListRef.size();
    }
    
    public static void main(String[] args) {
        //[[0,0],[94911151,94911150],[94911152,94911151]]
        Point[] points = new Point[] {new Point(0,0),new Point(94911151,94911150),new Point(94911152,94911151)};
        maxPoints(points);
    }
}
