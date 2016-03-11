package student;

import java.awt.Point;
import java.util.ArrayList;

/* 
 * This Skyline class is meant to contain your algorithm.
 * You should implement the static method: findSkyline
 * The input is an ArrayList of Point objects representing the buildings.
 * The output should be a new ArrayList of Point objects with all the buildings
 *  merged into a single profile. (Or more than 1 profile if they are non-overlapping.)
 */

public class Skyline
{

	// 
	public static ArrayList<Point> findSkyline(ArrayList<Point> input) 
	{		
		ArrayList<Point> left, right;

		// Base Case: 
		//	One Building. Just return the skyline
		if(input.size() == 1)
			return input;

		// Recursion Step: 
		//	more than 2 buildings
		// 	split the list into left and right halves 
		// 	merge the sub-arrays
		else{

			left = new ArrayList<Point>(input.subList(0, input.size()/2));
			right = new ArrayList<Point>(input.subList(input.size()/2 + 1, input.size()));
			return mergeSkyline(findSkyline(left), findSkyline(right));
		}

	}	


	public static ArrayList<Point> mergeSkyline(ArrayList<Point> left, ArrayList<Point> right){
		return left;
	}


}
