
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final List<LineSegment> allSegments;

    public BruteCollinearPoints(Point[] inputPoints) {
        if (inputPoints == null) {
            throw new IllegalArgumentException();
        }

        for (Point point : inputPoints) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
        Point[] points = Arrays.copyOfRange(inputPoints, 0, inputPoints.length);

        Arrays.sort(points);

        allSegments = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int m = k + 1; m < points.length; m++) {
                        double slopeij = points[i].slopeTo(points[j]);
                        double slopeik = points[i].slopeTo(points[k]);
                        if (slopeij == slopeik && slopeij == points[i].slopeTo(points[m])) {
                            allSegments.add(new LineSegment(points[i], points[m]));
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
        }
        else {
            return allSegments.toArray(new LineSegment[allSegments.size()]);
        }
    }

}
