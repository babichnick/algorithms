import edu.princeton.cs.algs4.*;
import java.util.*;

public class FastCollinearPoints {

	private ArrayList<LineSegment> segments;

	public FastCollinearPoints(Point[] points) // finds all line segments
												// containing 4 or more points
	{
		
		segments = new ArrayList<LineSegment>();
		// Go each point p.
        for (int p = 0; p < points.length; p++) {
            // Sort the points according to the slopes they makes with p.
            Arrays.sort(points, points[p].slopeOrder());
            // Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p
            ArrayList<Point> collinearPoints = new ArrayList<Point>(points.length);
            for (int q = 0; q < points.length - 1; q++) {
                if (p == q) {
                    continue;
                }
                if (collinearPoints.isEmpty()) {
                    collinearPoints.add(points[q]);
                } else if (points[p].slopeTo(points[q - 1]) == points[p].slopeTo(points[q])) {
                    collinearPoints.add(points[q]);
                } else if (collinearPoints.size() > 2) {
                    // Draw collinear points.
                    collinearPoints.add(points[p]);
                    Collections.sort(collinearPoints);

                    LineSegment seg = new LineSegment(Collections.min(collinearPoints),
							Collections.max(collinearPoints));							
				    segments.add(seg);
                    break;
                } else {
                    collinearPoints.clear();
                    collinearPoints.add(points[q]);
                }
            }
        }
		
		
		
	}

	public int numberOfSegments() // the number of line segments
	{
		return segments.size();
	}

	public LineSegment[] segments() // the line segments
	{
		return segments.toArray(new LineSegment[segments.size()]);
	}

	public static void main(String[] args) {

		// read the N points from a file
		In in = new In(args[0]);
		int N = in.readInt();
		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			int x = in.readInt();
			int y = in.readInt();
			points[i] = new Point(x, y);
		}

		// draw the points
		StdDraw.show(0);
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		StdOut.println("Number of segments is:" + collinear.numberOfSegments());
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
	}

}