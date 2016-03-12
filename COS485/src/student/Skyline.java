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

			Point s1 = skyline1.get(i);
			Point s2 = skyline2.get(j);


			if( s1.x == s2.x){
				Point p = new Point(s1.x, Math.max(s1.y, s2.y));
				output.add(p);
				h1=h2=s2.y;
				skyline1.remove(s1);
				skyline2.remove(s2);
				removeRedundant(output);

			}
			else if(s1.x < s2.x){
				Point a = new Point(s1.x, Math.max(h2, s1.y) );
				output.add(a);
				h1 = s1.y;
				skyline1.remove(s1);
				removeRedundant(output);

			}else{

				Point b = new Point(s2.x, Math.max(h1, s2.y) );
				output.add(b);
				h2 = s2.y;
				skyline2.remove(s2);
				removeRedundant(output);


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

		for(int i=0; i<building.size(); i++){

			building1.add(new Point(building.get(i).x1, building.get(i).y1));
			building1.add(new Point(building.get(i).x2, building.get(i).y2));
		}

		return building1;		
	}

	// helper method to see if the height already exists
	// removes the previous index becasue thats where 
	//  the occurence would be 
	public static void removeRedundant(ArrayList<Point> alp){

		int size = alp.size()-1;

		if(alp.size() > 1)
	
			if( alp.get(size).y == alp.get(size-1).y ){
				alp.remove(size);
			}
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
