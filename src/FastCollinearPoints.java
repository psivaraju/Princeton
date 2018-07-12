
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private final List<LineSegment> allSegments;

    public FastCollinearPoints(Point[] points) {
        Point[] copy = Arrays.copyOfRange(points, 0, points.length);
        Arrays.sort(copy);
        allSegments = new ArrayList<>();
        if (copy == null || copy.length == 0) {
            throw new IllegalArgumentException();
        }

        Point prevPoint = null;
        for (Point point : copy) {
            if (point == null || (prevPoint != null && point.compareTo(prevPoint) == 0)) {
                throw new IllegalArgumentException();
            }
            prevPoint = point;
        }

        Point[] subArray = null;
        List<Point> tempList = null;
        double tempSlope = 0;

        for (int i = 0; i < copy.length - 3; i++) {
            subArray = Arrays.copyOfRange(copy, i + 1, copy.length);
            Arrays.sort(subArray, copy[i].slopeOrder());
            for (int j = 0; j < subArray.length; j++) {
                if (tempList == null) {
                    tempList = new ArrayList<>();
                    tempList.add(copy[i]);
                    tempList.add(subArray[j]);
                    tempSlope = copy[i].slopeTo(subArray[j]);
                }
                else {
                    if (tempSlope == copy[i].slopeTo(subArray[j])) {
                        tempList.add(subArray[j]);
                    }
                    else {
                        if (tempList.size() > 3) {
                            addSegmentToAllSegments(tempList.get(0), tempList.get(tempList.size() - 1));
                        }
                        tempList = new ArrayList<>();
                        tempList.add(copy[i]);
                        tempList.add(subArray[j]);
                        tempSlope = copy[i].slopeTo(subArray[j]);
                    }

                }

            }
            if (tempList != null && tempList.size() > 3) {
                addSegmentToAllSegments(tempList.get(0), tempList.get(tempList.size() - 1));
            }
            tempList = null;

        }

    }
    
    private void addSegmentToAllSegments(Point a, Point b) {
        Point comparisonPoint = null;
        boolean addToList = true;
        for(LineSegment seg : allSegments) {
            if(a.compareTo(seg.p) == 0 || a.compareTo(seg.q) == 0) {
                comparisonPoint = b;
            } else {
                comparisonPoint = a;
            }
            if(comparisonPoint.slopeTo(seg.p) == comparisonPoint.slopeTo(seg.q)) {
                addToList = false;
                break;
            }
        }
        
        if(addToList) {
            allSegments.add(new LineSegment(a, b));
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
