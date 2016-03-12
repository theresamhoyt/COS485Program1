package student;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
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
			return mergeSkyline(left, right);
		}

	}	


	// merges the two skylines together
	public static ArrayList<Point> mergeSkyline(ArrayList<Point> skyline1, ArrayList<Point> skyline2) {

		int h1 = 0, h2 = 0;
		ArrayList<Point> output = new ArrayList<Point>();

		//	iterate through the two skylines
		//	compare the first elements of the skyline
		//	grab the one that comes first
		//	calculate the height for that point
		//	add that point to the retuen list
		// move to the next point in the sky lists
		// if you reach the end of one list, add all the points of the other list

		int i=0, j=0;
		while( !skyline1.isEmpty() && !skyline2.isEmpty()){

			if( skyline1.get(i).x < skyline2.get(j).x){
				h1 = Math.max(skyline1.get(i).y, h2);
				skyline1.get(i).y = h1;
				output.add(skyline1.get(i));
				hasHeight(output);
				skyline1.remove(skyline1.get(i));

			}else{

				h2 = Math.min(h1, skyline2.get(j).y);
				skyline2.get(j).y = h2;
				output.add(skyline2.get(j));	
				hasHeight(output);
				skyline2.remove(skyline2.get(j));
			}

		}


		if(skyline1.isEmpty())
			output.addAll(skyline2);

		if(skyline2.isEmpty())
			output.addAll(skyline1);


		return output;
	}

	// helper method to convert building list to points list
	public static ArrayList<Point> toPoint(List<Building> building){
		ArrayList<Point> building1 = new ArrayList<Point>();
		building1.add(new Point(building.get(0).x1, building.get(0).y1));
		building1.add(new Point(building.get(0).x2, building.get(0).y2));
		return building1;		
	}

	// helper method to see if the height already exists
	// removes the previous index becasue thats where 
	//  the occurence would be 
	public static void hasHeight(ArrayList<Point> alp){
		
		int size = alp.size()-1;
		
		if(alp.size() > 1)
			if( alp.get(size).y == alp.get(size-1).y )
				alp.remove(size-1);


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
