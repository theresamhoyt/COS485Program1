package student;

/* 
 * This Skyline class is meant to contain your algorithm.
 * You should implement the static method: findSkyline
 * The input is an ArrayList of Point objects representing the buildings.
 * The output should be a new ArrayList of Point objects with all the buildings
 *  merged into a single profile. (Or more than 1 profile if they are non-overlapping.)
 */
import java.awt.Point;
import java.util.ArrayList;

public class Skyline
{
	// Example routine that just looks at the input list of points 
	// and constructs a bounding rectangle.
	// This is interesting, but not the correct solution to this problem.
	public static ArrayList<Point> findSkyline(ArrayList<Point> input) 
	{	
		Point p = input.get(0);
		int minx = p.x;
		int maxx = p.x;
		int miny = p.y;
		int maxy = p.y;
		for (int i = 1; i < input.size(); i++) {
			p = input.get(i);
			minx = Math.min(minx, p.x);
			maxx = Math.max(maxx, p.x);
			miny = Math.min(miny, p.y);
			maxy = Math.max(maxy, p.y);
		}
		
		ArrayList<Point> output = new ArrayList<Point>();
		output.add(new Point(minx, maxy));
		output.add(new Point(maxx, miny));
				
		return output; 
	}	

  
}
