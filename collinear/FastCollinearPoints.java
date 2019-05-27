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

public class FastCollinearPoints {

    private final int numberOfSegments;
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        int numberOfPoints = points.length;
        for (int i = 0; i < numberOfPoints; i++)
            if (points[i] == null) throw new IllegalArgumentException();
        Point[] ps = points.clone();
        Arrays.sort(ps);
        for (int i = 0; i < numberOfPoints - 1; i++) {
            if (ps[i].compareTo(ps[i + 1]) == 0)
                throw new IllegalArgumentException();
        }
        ArrayList<LineSegment> aLS = new ArrayList<LineSegment>();

        for (int i = 0; i < numberOfPoints; i++) {
            Point[] temP = ps.clone();
            Arrays.sort(temP, temP[i].slopeOrder());
            int j = 1;
            while (j < numberOfPoints - 2) {
                int k = j;
                double s12 = temP[0].slopeTo(temP[k++]);
                double s1e;
                do {
                    if (k == numberOfPoints) {
                        k++;
                        break;
                    }
                    s1e = temP[0].slopeTo(temP[k++]);
                } while (Double.compare(s12, s1e) == 0);
                if (k - j < 4) {
                    j++;
                    continue;
                }
                int numOfColinear = k-- - j;
                Point[] colinear = new Point[numOfColinear];
                colinear[0] = temP[0];
                for (int t = 1; t < numOfColinear; t++)
                    colinear[t] = temP[t + j - 1];
                Arrays.sort(colinear);
                if (colinear[0] == temP[0])
                    aLS.add(new LineSegment(colinear[0], colinear[numOfColinear - 1]));
                j = k;
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
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        for (Point p : points) {
            p.draw();
        }

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        StdOut.println("Done.");
    }
}
