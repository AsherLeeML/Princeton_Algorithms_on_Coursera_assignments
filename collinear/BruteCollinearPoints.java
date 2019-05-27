/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private final int numberOfSegments;
    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (Point p : points)
            if (p == null) throw new IllegalArgumentException();
        int numOfPoints = points.length;
        Point[] ps = points.clone();
        Arrays.sort(ps);
        for (int i = 0; i < numOfPoints - 1; i++)
            if (ps[i].compareTo(ps[i + 1]) == 0)
                throw new IllegalArgumentException();


        ArrayList<LineSegment> aLS = new ArrayList<LineSegment>();

        for (int i = 0; i < numOfPoints - 3; i++) {
            for (int j = i + 1; j < numOfPoints - 2; j++) {
                for (int k = j + 1; k < numOfPoints - 1; k++) {
                    for (int q = k + 1; q < numOfPoints; q++) {
                        Point[] temP = new Point[4];
                        temP[0] = ps[i];
                        temP[1] = ps[j];
                        temP[2] = ps[k];
                        temP[3] = ps[q];
                        double s12 = temP[0].slopeTo(temP[1]);
                        double s13 = temP[0].slopeTo(temP[2]);
                        if (Double.compare(s12, s13) != 0)
                            continue;
                        double s14 = temP[0].slopeTo(temP[3]);
                        if (Double.compare(s12, s14) == 0) {
                            Arrays.sort(temP);
                            aLS.add(new LineSegment(temP[0], temP[3]));
                        }
                    }
                }
            }
        }
        numberOfSegments = aLS.size();
        segments = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++)
            segments[i] = aLS.get(i);
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenColor(StdDraw.BLACK);

        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }

        StdDraw.show();
        StdDraw.setPenColor(StdDraw.BLUE);

        BruteCollinearPoints bruteCollinearPoints = new BruteCollinearPoints(points);
        for (LineSegment lS : bruteCollinearPoints.segments()) {
            StdOut.println(lS);
            lS.draw();
        }
        StdDraw.show();
        StdOut.println("Done.");
    }
}
