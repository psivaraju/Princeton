package week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private List<LineSegment> allSegments;

    public BruteCollinearPoints(final Point[] points) {
        if (points == null) {
            throw new NullPointerException("Arg to constructor is null !!");
        }

        Point[] copy = Arrays.copyOfRange(points, 0, points.length);
        Arrays.sort(copy);

        for (int i = 0; i < copy.length - 1; i++) {
            if (copy[i].compareTo(copy[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        allSegments = new ArrayList<LineSegment>();
        for (int i = 0; i < copy.length; i++) {
            for (int j = i + 1; j < copy.length; j++) {
                for (int k = j + 1; k < copy.length; k++) {
                    for (int l = k + 1; l < copy.length; l++) {
                        double ijSlope = copy[i].slopeTo(copy[j]);
                        if ((ijSlope == copy[i].slopeTo(copy[k]))
                                && (ijSlope == copy[i].slopeTo(copy[l]))) {
                            allSegments.add(new LineSegment(copy[i], copy[l]));
                            break;
                        }
                    }
                }
            }

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