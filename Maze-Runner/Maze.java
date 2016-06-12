

//Name:Jingyu Xie
//USC loginid:jingyuxi
//CS 455 PA3
//Spring 2016

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Maze class
 * 
 * Stores information about a maze and can find a path through the maze (if
 * there is one).
 * 
 * Assumptions about structure of the maze, as given in mazeData, startLoc, and
 * endLoc (parameters to constructor), and the path: -- no outer walls given in
 * mazeData -- search assumes there is a virtual border around the maze (i.e.,
 * the maze path can't go outside of the maze boundaries) -- start location for
 * a path is maze coordinate startLoc -- exit location is maze coordinate
 * exitLoc -- mazeData input is a 2D array of booleans, where true means there
 * is a wall at that location, and false means there isn't (see public FREE /
 * WALL constants below) -- in mazeData the first index indicates the row. e.g.,
 * mazeData[row][col] -- only travel in 4 compass directions (no diagonal paths)
 * -- can't travel through walls
 */

public class Maze {

	public static final boolean FREE = false;
	public static final boolean WALL = true;
	// create nesseary instant variables
	private boolean[][] mazeData;
	private MazeCoord startLoc;
	private MazeCoord endLoc;
	private LinkedList<MazeCoord> path;

	/**
	 * Constructs a maze.
	 * 
	 * @param mazeData
	 *            the maze to search. See general Maze comments for what goes in
	 *            this array.
	 * @param startLoc
	 *            the location in maze to start the search (not necessarily on
	 *            an edge)
	 * @param endLoc
	 *            the "exit" location of the maze (not necessarily on an edge)
	 *            PRE: 0 <= startLoc.getRow() < mazeData.length and 0 <=
	 *            startLoc.getCol() < mazeData[0].length and 0 <=
	 *            endLoc.getRow() < mazeData.length and 0 <= endLoc.getCol() <
	 *            mazeData[0].length
	 * 
	 */
	public Maze(boolean[][] mazeData, MazeCoord startLoc, MazeCoord endLoc) {
		// if the startLoc is satisfied the conditions below then it will admit
		// as part of the maze
		if (startLoc.getRow() >= 0 && startLoc.getRow() < mazeData.length && startLoc.getCol() >= 0
				&& startLoc.getCol() < mazeData[0].length && startLoc.getRow() >= 0
				&& startLoc.getRow() < mazeData.length && startLoc.getCol() >= 0
				&& startLoc.getCol() < mazeData[0].length) {

			// initialize those variables from Maze method
			this.mazeData = mazeData;
			this.startLoc = startLoc;
			this.endLoc = endLoc;
			this.path = new LinkedList<MazeCoord>();
		}
	}

	/**
	 * Returns the number of rows in the maze
	 * 
	 * @return number of rows
	 */
	public int numRows() {
		return mazeData.length;
	}

	/**
	 * Returns the number of columns in the maze
	 * 
	 * @return number of columns
	 */
	public int numCols() {
		return mazeData[0].length;
	}

	/**
	 * Returns true iff there is a wall at this location
	 * 
	 * @param loc
	 *            the location in maze coordinates
	 * @return whether there is a wall here PRE: 0 <= loc.getRow() < numRows()
	 *         and 0 <= loc.getCol() < numCols()
	 */
	public boolean hasWallAt(MazeCoord loc) {
		//if the Row or Col is out of boundary then return WALL
		if (loc.getRow() > numRows() - 1 || loc.getRow() < 0 || loc.getCol() > numCols() - 1 || loc.getCol() < 0) {
			return WALL;
			//if the mazeData of the current location is WALL then return WALL
		} else if (mazeData[loc.getRow()][loc.getCol()] == WALL) {
			return WALL;
		} else {
			return FREE;
		}
	}

	/**
	 * Returns the entry location of this maze.
	 */
	public MazeCoord getEntryLoc() {
		return startLoc;
	}

	/**
	 * Returns the exit location of this maze.
	 */
	public MazeCoord getExitLoc() {
		return endLoc;
	}

	/**
	 * Returns the path through the maze. First element is starting location,
	 * and last element is exit location. If there was not path, or if this is
	 * called before search, returns empty list.
	 * 
	 * @return the maze path
	 */
	public LinkedList<MazeCoord> getPath() {

		return this.path;

	}

	/**
	 * Find a path through the maze if there is one. Client can access the path
	 * found via getPath method.
	 * 
	 * @return whether path was found.
	 */
	public boolean search() {
		// initialize a new mazecoord object as a linkedlist
		LinkedList<MazeCoord> root = new LinkedList<MazeCoord>();

		return searchHelper(startLoc, root);

	}

	/**
	 * This is the real recursive function which is used to find the path
	 * through the maze
	 * 
	 * @param loc
	 *            this is the MazeCoord instance for the location to search
	 * @param parentVisited
	 *            this is the list that store the path already visited
	 * 
	 * @return true if visited,false if not visited
	 */
	private boolean searchHelper(MazeCoord loc, LinkedList<MazeCoord> parentVisited) {

		/*
		 * base case if there is wall at the current location then return false
		 */
		if (hasWallAt(loc) == WALL) {
			System.out.println("no path found!");
			return false;
			// the ending case
			// if the current location equals the final position then return
			// true
		} else if (endLoc.equals(loc)) {
			// initialize a temparary mazecoord to save the parentVisited
			// linkedlist
			LinkedList<MazeCoord> temp = parentVisited;
			// add the current location into the temprary linkedlist
			temp.add(loc);
			// double check whether there is still some duplicated elements in
			// the linkedlist and assign to the overall variable
			path = removeDuplicate(temp);
			System.out.println("DEBUG: PATH FOUND!");
			return true;
		} else {
			// initialize variables
			/*
			 * curentRow- an int variable for current location ROW curentCol- an
			 * int variable for current location COL temp - temprary linkedlist
			 * mazecoord nextLoc_XPosiDir is the adjacent positive x direction
			 * location nextLoc_YPosiDir is the adjacent positive y direction
			 * location nextLoc_XnegiDir is the adjacent negative x direction
			 * location nextLoc_XnegiDir is the adjacent negative y direction
			 * location
			 */
			int currentRow = loc.getRow();
			int currentCol = loc.getCol();
			LinkedList<MazeCoord> temp;
			MazeCoord nextLoc_XPosiDir = new MazeCoord(currentRow + 1, currentCol);
			MazeCoord nextLoc_YPosiDir = new MazeCoord(currentRow, currentCol + 1);
			MazeCoord nextLoc_XNegiDir = new MazeCoord(currentRow - 1, currentCol);
			MazeCoord nextLoc_YNegDir = new MazeCoord(currentRow, currentCol - 1);

			// if the adjacent positive x direction location has no wall and the
			// location is not visited then keep going in that route
			if (hasWallAt(nextLoc_XPosiDir) == FREE && isVisited(nextLoc_XPosiDir, parentVisited) == FREE) {
				// assagin parentVisited to temp
				temp = parentVisited;
				// add the current location to temp
				temp.add(loc);
				// recursively return back the valid location
				return searchHelper(nextLoc_XPosiDir, temp);

			}
			// if the adjacent positive y direction location has no wall and the
			// location is not visited then keep going in that route
			else if (hasWallAt(nextLoc_YPosiDir) == FREE && isVisited(nextLoc_YPosiDir, parentVisited) == FREE) {
				// assagin parentVisited to temp
				temp = parentVisited;
				// add the current location to temp
				temp.add(loc);
				// recursively return back the valid location
				return searchHelper(nextLoc_YPosiDir, temp);

			}
			// if the adjacent negative x direction location has no wall and the
			// location is not visited then keep going in that route
			else if (hasWallAt(nextLoc_XNegiDir) == FREE && isVisited(nextLoc_XNegiDir, parentVisited) == FREE) {
				// assagin parentVisited to temp
				temp = parentVisited;
				// add the current location to temp
				temp.add(loc);
				// recursively return back the valid location
				return searchHelper(nextLoc_XNegiDir, temp);

			}
			// if the adjacent negative y direction location has no wall and the
			// location is not visited then keep going in that route
			else if (hasWallAt(nextLoc_YNegDir) == FREE && isVisited(nextLoc_YNegDir, parentVisited) == FREE) {
				// assagin parentVisited to temp
				temp = parentVisited;
				// add the current location to temp
				temp.add(loc);
				// recursively return back the valid location
				return searchHelper(nextLoc_YNegDir, temp);

			}
			// if the location is not start location and all the four direction
			// not satisfied(visited or blocked)
			// then return back to the previous coordinate and so on
			else if (loc.equals(startLoc) == false) {
				// assagin parentVisited to temp
				temp = parentVisited;
				// if the location is already in the visited list
				if (isVisited(loc, temp) == true) {
					// remove the last element of the list until find the
					// previous elements
					while (isVisited(loc, temp) == true) {
						temp.removeLast();
					}
					// assgin the previous element as the list last element
					MazeCoord previous = temp.getLast();
					// add the current location to the visited list
					parentVisited.add(loc);
					// return recursively back
					return searchHelper(previous, parentVisited);
				}
				// otherwise let the previous be the last element of the list
				else {
					MazeCoord previous = parentVisited.getLast();
					// add the current location to the list
					temp.add(loc);
					// return recursively back with previous
					return searchHelper(previous, temp);
				}
			}
		}
		System.out.println("DEBUG: no path found!");
		// if all the situation not satisfied which means there is no path
		// through the maze
		return false;
	}

	/**
	 * Returns the boolean results whether the location is visited or not
	 * 
	 * @param loc
	 *            this is the MazeCoord instance for the location to start to
	 *            searching
	 * @param list
	 *            this is the list that store the path already visited
	 * 
	 * @return true if visited,false if not visited
	 */
	private boolean isVisited(MazeCoord loc, LinkedList<MazeCoord> list) {
		// initialize the iteration of the linkedlist
		ListIterator<MazeCoord> iter = list.listIterator();
		// begin iteration
		while (iter.hasNext()) {
			// initialize a temprary coordinate as the next element of the list
			MazeCoord tempCoord = iter.next();
			// if the current location equals to the temp coordinate then return
			// true
			if (tempCoord.equals(loc)) {
				return true;
			}
		}
		// otherwise return false
		return false;
	}

	/**
	 * Returns the a linkedlist as the final path through the maze
	 * 
	 * @param originalPath
	 *            this is the list that store the path already visited
	 * 
	 * @return linkedlist of the object MazeCoord
	 */
	private LinkedList<MazeCoord> removeDuplicate(LinkedList<MazeCoord> originalPath) {
		// initialize a newpath linkedlist
		LinkedList<MazeCoord> newPath = new LinkedList<MazeCoord>();
		// initialize the linkedlist iteration
		ListIterator<MazeCoord> iter = originalPath.listIterator();
		// begin iteration
		while (iter.hasNext()) {
			// initialize a temprary coordinate as the next element of the list
			MazeCoord tempCoord = iter.next();
			// if the next element of the originalpath already in the newPath
			// then delete the last element
			// and continue loop
			if (isVisited(tempCoord, newPath) == true) {
				newPath.removeLast();
				continue;
				// otherwise add the next element of the originalPath to the
				// newPath
			} else {
				newPath.add(tempCoord);
			}
		}
		return newPath;

	}
}
