import edu.princeton.cs.algs4.*;
import java.util.*;

public class BruteCollinearPoints {

	private ArrayList<LineSegment> segments;

	public BruteCollinearPoints(Point[] points) // finds all line segments
												// containing 4 points
	{

		// segments = new LineSegment[points.length/2];
		// Point p, q, r, s;
		segments = new ArrayList<LineSegment>();
		// Go each 4 points and check whether they all lie on the same line
		// segment.

		for (int p = 0; p < points.length; p++) {
			for (int q = p + 1; q < points.length; q++) {
				double slopeToQ = points[p].slopeTo(points[q]);

				for (int r = q + 1; r < points.length; r++) {
					double slopeToR = points[p].slopeTo(points[r]);
					if (slopeToQ == slopeToR) {

						for (int s = r + 1; s < points.length; s++) {
							double slopeToS = points[p].slopeTo(points[s]);
							if (slopeToQ == slopeToS) {
								// Create the list of collinear points and sort
								// them.

								List<Point> collinearPoints = new ArrayList<Point>(4);
								collinearPoints.add(points[p]);
								collinearPoints.add(points[q]);
								collinearPoints.add(points[r]);
								collinearPoints.add(points[s]);
								Collections.sort(collinearPoints);

								LineSegment seg = new LineSegment(Collections.min(collinearPoints),
											Collections.max(collinearPoints));							
								segments.add(seg);
							}
						}
					}
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
		BruteCollinearPoints collinear = new BruteCollinearPoints(points);
		StdOut.println("Number of segments is:" + collinear.numberOfSegments());
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
	}
}