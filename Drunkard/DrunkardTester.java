package cs455PA1;

import java.util.Scanner;

/**
 * This is the Drunkard Tester which is used to test Drunkard.java
 *
 **/
public class DrunkardTester {
	// main method to test
	public static void main(String[] args) {
		// add some used variable in the head
		int x;
		int y;
		int steps;
		int i = 0;

		// initialize the Scanner
		Scanner in = new Scanner(System.in);

		// Enter values for (x,y) and step size
		System.out.println("Enter start location for X(X=[0,80]): ");
		x = in.nextInt();
		System.out.println("Enter start location for Y(Y=[0,80]): ");
		y = in.nextInt();
		System.out.println("Enter step size(Only positive integer): ");
		steps = in.nextInt();

		// a while loop to judge whether the entered number is valid or not,
		// which means only steps>=0 can make the program keep going
		while (steps < 0) {
			// prompt to error massage and make user to enter number again
			System.out.println("Error: the steps you entered are invalid, must be greater than 0 ! ");
			System.out.println("Enter step size again: ");

			// store the value
			steps = in.nextInt();
		}

		// ini Impoint and insert the user's begin position
		ImPoint ip = new ImPoint(x, y);

		// ini Drunkard
		Drunkard dk = new Drunkard(ip, steps);

		// prompt the Drunkard current location and step size
		System.out.println("Drunkard starts at" + "(" + ip.getX() + "," + ip.getY() + "); step size is " + steps);
		System.out.println("get starting loc [expected:" + "(" + ip.getX() + "," + ip.getY() + ")]: " + "(" + ip.getX()
				+ "," + ip.getY() + ")");

		// continue asking user to enter step make the drunkard to forward
		while (i < steps) {
			// use takestep method to move
			dk.takeStep();

			// get current location and set the location to the (x,y)
			ip = dk.getCurrentLoc();
			System.out.println("took step to " + "(" + ip.getX() + "," + ip.getY() + ")" + " SUCCEEDED");

			// increase i by every period
			i++;
		}
		// prompt the program is over
		System.out.println("Steps are completed, and he is lying on the road-->too drunk :)");
	}

}
