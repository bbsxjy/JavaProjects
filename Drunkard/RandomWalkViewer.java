

import java.util.Scanner;
import javax.swing.JFrame;

/**
 * the RandomWalkViewer contains the main routine, prompts the steps and
 * starting location creates the JFrame containing the RandomWalkComponent
 *
 **/

public class RandomWalkViewer {
	// the main method routine
	public static void main(String[] args) {
		// ini JFrame object
		JFrame frame = new JFrame();

		// set size 400pixel by 400 pixel
		frame.setSize(400, 400);

		// set viewer title
		frame.setTitle("Drunkard Viewer");

		// set exit option
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ini Scanner
		Scanner in = new Scanner(System.in);

		// prompt to enter numbers for starting variable X
		System.out.println("Enter start location for X: ");

		// store the entered number into x;
		int x = in.nextInt();
		// a while loop to judge whether the entered number is valid or not,
		// which means only 0<=x<=80 can make the program keep going
		while (x < 0 || x > 80) {
			// prompt to error massage and make user to enter number again
			System.out.println("Error: your X location number are invalid, please check! X=[0,80] ");
			System.out.println("Enter start location for X again: ");

			// store the value
			x = in.nextInt();
		}

		// prompt to enter numbers for starting variable Y
		System.out.println("Enter start location for Y: ");

		// store the entered number into y;
		int y = in.nextInt();

		// a while loop to judge whether the entered number is valid or not,
		// which means only 0<=y<=80 can make the program keep going
		while (y < 0 || y > 80) {
			// prompt to error massage and make user to enter number again
			System.out.println("Error: your Y location number are invalid, please check! Y=[0,80]");
			System.out.println("Enter start location for Y again: ");

			// store the value
			y = in.nextInt();
		}

		// prompt to enter numbers for starting variable step size
		System.out.println("Enter step size: ");

		// store the entered number into steps;
		int steps = in.nextInt();

		// a while loop to judge whether the entered number is valid or not,
		// which means only steps>=0 can make the program keep going
		while (steps < 0) {
			// prompt to error massage and make user to enter number again
			System.out.println("Error: the steps you entered are invalid, must be greater than 0 ! ");
			System.out.println("Enter step size again: ");

			// store the value
			steps = in.nextInt();
		}

		// initialize RandomWalkComponent object and pass the value x,y,steps to
		// the RandomWalkComponent class
		RandomWalkComponent component = new RandomWalkComponent(x, y, steps);

		// add the object into the frame
		frame.add(component);

		// set the frame visible
		frame.setVisible(true);

	}
}
