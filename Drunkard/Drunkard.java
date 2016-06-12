package cs455PA1;

//Name: Jingyu Xie	
//USC loginid:jingyuxi
//CS 455 PA1
//Spring 2016
import java.util.Random;

/**
 * Drunkard class Represents a "drunkard" doing a random walk on a grid.
 */
public class Drunkard {

	// add private instance variables here:
	private int X;
	private int Y;
	private int STEP_SIZE;
	private int randomNums_where;
	private int randomNums_who;
	private int toWhere;
	ImPoint pXY;

	/**
	 * Creates drunkard with given starting location and distance to move in a
	 * single step.
	 * 
	 * @param startLoc
	 *            starting location of drunkard
	 * @param theStepSize
	 *            size of one step in the random walk
	 */
	public Drunkard(ImPoint startLoc, int theStepSize) {
		// pass the values to the location variable x, y, and step size
		// store them to the local variable X and Y and Step_size
		this.X = startLoc.getX();
		this.Y = startLoc.getY();
		this.STEP_SIZE = theStepSize;
	}

	/**
	 * Takes a step of length step-size (see constructor) in one of the four
	 * compass directions. Changes the current location of the drunkard. Use
	 * random method to generate STEP_SIZE and directions
	 */
	public void takeStep() {
		// initialize the random object
		Random random = new Random();

		// initialize the ImPoint object
		ImPoint startLoc = new ImPoint(X, Y);

		// use the random method nextInt to generate integers from 0 to 1
		randomNums_where = random.nextInt(2);

		// use the random method nextInt to generate integers from 0 to 1
		randomNums_who = random.nextInt(2);

		// Generate directions
		// if the "where" variable is 0, it means it will make its next steps to
		// negative directions
		if (randomNums_where == 0) {
			toWhere = -1;
		} else {
			// if the "where" variable is 1, it means it will make its next
			// steps to positive directions
			toWhere = 1;
		}

		// Generate the next location
		// if the "who" variable is 0, it means it will make its next steps to x
		// direction
		if (randomNums_who == 0) {
			pXY = startLoc.translate(toWhere, 0);
		} else {
			// if the "who" variable is 1, it means it will make its next steps
			// to y direction
			pXY = startLoc.translate(0, toWhere);
		}

	}

	/**
	 * gets the current location of the drunkard.
	 * 
	 * @return an ImPoint object representing drunkard's current location
	 */
	public ImPoint getCurrentLoc() {
		// get the current x position
		X = pXY.getX();

		// get the current y position
		Y = pXY.getY();
		return new ImPoint(X, Y);
	}

}
