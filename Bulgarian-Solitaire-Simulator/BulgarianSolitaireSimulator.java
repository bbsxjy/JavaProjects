

import java.util.Scanner;

//Name: jingyu xie	
//USC loginid: jingyuxi
//CSCI455 PA2
//Spring 2016


/**
 * This simulator is used to test the mode:
 * -u:user config
 * -s:single step
 * non-parameter setting test
 * @param singleStep
 * 		stands for -s mode,true for turn on/false for turn off
 *  @param userConfig
 *  	stands for -u mode,true for turn on/false for turn off
 *  @param input
 *  	save the return string
 *  @param  in
 *  	Scanner instances
 */

public class BulgarianSolitaireSimulator {

	public static void main(String[] args) {

		boolean singleStep = false;
		boolean userConfig = false;
		String input;
		Scanner in = new Scanner(System.in);
		//setting -u and -s conditions
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u")) {
				userConfig = true;
			} else if (args[i].equals("-s")) {
				singleStep = true;
			}
		}
		//if -u mode turn on, use user configration to start play
		if (userConfig == true) {
			//save the valid return string of forPrompt into input
			input = forPrompt();
			//once the valid string returned, use it to create the new object withVar_SB
			SolitaireBoard withVar_SB = new SolitaireBoard(input);
			//use while loop to check the play is done as desired
			int itar = 0;
			while (!withVar_SB.isDone()) {
				itar++;
				withVar_SB.playRound();
				//output the string with orders
				System.out.println("[" + itar + "] " + withVar_SB.configString());
				//-s mode turn on, upon entering return it will continue running 
				if (singleStep == true) {
					System.out.print("<Type return to continue>");
					in.nextLine();
				}
			}

			System.out.println("Done!");
			//if -u not turn on,use the random initial configrations to start
		} else {
			//create the non-variable new object noVar_SB
			SolitaireBoard noVar_SB = new SolitaireBoard();

			int itar = 0;
			while (!noVar_SB.isDone()) {

				itar++;
				noVar_SB.playRound();
				//output the string with orders
				System.out.println("[" + itar + "] " + noVar_SB.configString());
				//-s mode turn on, upon entering return it will continue running 
				if (singleStep == true) {
					System.out.print("<Type return to continue>");
					in.nextLine();
				}
			}
			System.out.println("Done!");
		}


	}

	/**
	 * This method is used to prompt for user to enter data
	 * , if the data is invalid it will continue asking to type in untill satisfy the requiements
	 *  @param  in
	 *  	Scanner instances
	 */
	private static String forPrompt() {
		//first prompt the total number of cards and asking user to type number
		Scanner in;
		System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
		System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
		System.out.println("Please enter a space-separated list of positive integers followed by newline: ");

		in = new Scanner(System.in);
		String input = in.nextLine();
		//if the entered string is not meet the requirments, it will continue asking to re-type
		while (SolitaireBoard.isValidConfigString(input) != true) {
			System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be "+SolitaireBoard.CARD_TOTAL);
			System.out.println("Please enter a space-separated list of positive integers followed by newline: ");
			input = in.nextLine();
		}
		//once pass all the test,return the valid input to update new input
		return input;

	}

}
