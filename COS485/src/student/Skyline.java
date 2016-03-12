package student;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/* 
 * This Skyline class is meant to contain your algorithm.
 * You should implement the static method: findSkyline
 * The input is an ArrayList of Point objects representing the buildings.
 * The output should be a new ArrayList of Point objects with all the buildings
 *  merged into a single Building. (Or more than 1 Building if they are non-overlapping.)
 */

public class Skyline
{
	public static ArrayList<Point> findSkyline(ArrayList<Point> input) 
	{	
		// list to hold the profiles
		ArrayList<Building> building = new ArrayList<Building>();

		// create the Building instances
		// add them to the list
		for(int i=0; i<input.size(); i+=2){
			Building p = new Building();
			p.x1 = input.get(i).x;
			p.y1 = input.get(i).y;
			p.x2 = input.get(i+1).x;
			p.y2 = input.get(i+1).y;	
			building.add(p);
		}

		printList(building);

		ArrayList<Point> left, right;

		// Base Case: One Building
		// return converted buildings list to points list
		if(building.size() == 1){
			return toPoint(building);
		}

		// Recursion Step: 
		//	more than 2 buildings
		// 	split the list into left and right halves 
		// 	merge the sub-arrays
		else{

			int mid = building.size()/2;

			left = findSkyline(toPoint(building.subList(0, mid)));
			right = findSkyline(toPoint(building.subList(mid, building.size())));
			return mergeSkyline(findSkyline(left), findSkyline(right));
		}

	}	


	// merges the two skylines together
	public static ArrayList<Point> mergeSkyline(ArrayList<Point> findSkyline, ArrayList<Point> findSkyline2) {




		return null;
	}

	// helper method to convert building list to points list
	public static ArrayList<Point> toPoint(List<Building> building){
		ArrayList<Point> building1 = new ArrayList<Point>();
		building1.add(new Point(building.get(0).x1, building.get(0).y1));
		building1.add(new Point(building.get(0).x2, building.get(0).y2));
		return building1;		
	}

	// helper method to print content
	public static void printList(ArrayList<Building> building) {

		for(int i=0; i<building.size(); i++)
			System.out.println(building.get(i));
	}

	//	Nested class to create the buildings 
	//		based on the input points
	//	Each Building will have 2 sets of points

	public static class Building{
		int x1, y1, x2, y2;

		public Building(){}

		public Building(int x1, int y1, int x2, int y2){
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		public String toString(){
			return "[("+x1+","+y1+"), ("+x2+","+y2+")]";

		}
	}
}
