

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Scanner;
import javax.swing.JComponent;

/**
 * RandomWalkComponent Class Represents the actual how to create the point and
 * draw them on the graphic system and it inheirit the JComponent class
 **/
public class RandomWalkComponent extends JComponent {
	// Define variable X,Y,STEPS
	private int X;
	private int Y;
	private int STEP_SIZE;

	/**
	 * Creates the RandomWalkComponent Objects that passing the values from the
	 * RandomWalkViewer
	 * 
	 * @param x
	 *            starting x location of user's entering
	 * @param y
	 *            starting y location of user's entering
	 * @param STEP_SIZE
	 *            step sizes of user's entering
	 */
	public RandomWalkComponent(int x, int y, int STEP_SIZE) {
		this.X = x;
		this.Y = y;
		this.STEP_SIZE = STEP_SIZE;
	}

	/**
	 * The paintComponent is the method to use graphical drawing
	 * 
	 * @param g
	 *            is used to the java.awt package
	 **/
	public void paintComponent(Graphics g) {

		// recover Graphics2D
		Graphics2D g2 = (Graphics2D) g;

		// ini Impoint and insert the user's begin position
		ImPoint ip = new ImPoint(X, Y);

		// ini Drunkard
		Drunkard dk = new Drunkard(ip, STEP_SIZE);

		// prompt the Drunkard current location and step size
		System.out.println("Drunkard starts at" + "(" + ip.getX() + "," + ip.getY() + "); step size is " + STEP_SIZE);
		System.out.println("get starting loc [expected:" + "(" + ip.getX() + "," + ip.getY() + ")]: " + "(" + ip.getX()
				+ "," + ip.getY() + ")");

		// continue asking user to enter step make the drunkard to forward
		for (int i = 0; i < STEP_SIZE;) {
			// get and store first location to the graphical variable "from"
			Point2D.Double from = new Point2D.Double((ip.getX()) * 5, (ip.getY()) * 5);

			// use takestep method to move
			dk.takeStep();

			// get current location and set the location to the (x,y),and prompt
			// the successful massage
			ip = dk.getCurrentLoc();
			System.out.println("took step to " + "(" + ip.getX() + "," + ip.getY() + ")" + " SUCCEEDED");

			// get and store current location to the graphical variable "to"
			Point2D.Double to = new Point2D.Double((ip.getX()) * 5, (ip.getY()) * 5);

			// initialize and store the location to the variable "segment"
			Line2D.Double segment = new Line2D.Double(from, to);

			// draw the line between from and to
			g2.draw(segment);

			// the counter +1
			i++;

		}
		// prompt the program is over
		System.out.println("Steps are completed, and he is lying on the road-->too drunk :)");
	}

}
