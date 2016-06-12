

import java.util.Scanner;
//Name:jingyu xie 
//USC loginid: jingyuxi
//CSCI455 PA2
//Spring 2016

/*
class SolitaireBoard
The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
for CARD_TOTAL that result in a game that terminates.
(See comments below next to named constant declarations for more details on this.)
*/

public class SolitaireBoard {

	public static final int NUM_FINAL_PILES =9;
	// number of piles in a final configuration
	// (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)

	public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
	// bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
	// see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
	// the above formula is the closed form for 1 + 2 + 3 + . . . +
	// NUM_FINAL_PILES

	/**
	 * Representation invariant:
	 * 1. piles.length< CARD_TOTAL
	 * 2.numPiles<CARD_TOTAL
	 * 3.piles[0]!=0
	 * 4.piles[0]+piles[1]+...+piles[n]<CARD_TOTAL
	 * 
	 * <put rep. invar. comment here>
	 * 1.the length of the piles should less than CARD_TOTAL
	 * 2.the max numPiles shoud less than CARD_TOTAL: i.g 1 1 1....1 1 1 at most CARD_TOTAL's numbers
	 * 3.first element no 0
	 * 4.total of the input should less than the CARD_TOTAL
	 */

	// <add instance variables here>

	private Scanner lineScanner;
	private String numbers;
	private int cards = CARD_TOTAL;
	private int i = 0;
	private int sum = 0;
	private int counter = 0;
	private int[] piles;
	private int[] newPiles;
	private int[] temp;

	/**
	 * Creates a solitaire board with the given configuration. PRE:
	 * SolitaireBoard.isValidConfigString(numberString)
	 */
	public SolitaireBoard(String typedString) {
		//initial array with the size of CARD_TOTAL
		piles = new int[CARD_TOTAL];
		//initial line Scanner and use typedString as string
		lineScanner = new Scanner(typedString);
		// if pass , it's going to generate a piles and numbers of Piles
		while (lineScanner.hasNext()) {
			numbers = lineScanner.next();
			piles[i] = Integer.parseInt(numbers);
			i++;
		}
		//save i into counter make it as a number of piles
		counter = i;
		//initialize the final array with the size of the numbers of first piles
		newPiles = new int[counter];
		//Use a for loop to take the significant integers out into the final array(get rid of (CARD_TOTAL-counter) 0s in piles )
		for (int j = 0; j < counter; j++) {
			newPiles[j] = piles[j];
		}
		//Prepare to print out the final pile array as a string
		numbers = "";
		//use for loop to add up every elements of newPiles into the string numbers,except the last digit
		for (int i = 0; i < newPiles.length - 1; i++) {
			numbers = numbers + newPiles[i] + " ";
		}
		//add the last digit to the end of string 
		numbers = numbers + newPiles[newPiles.length - 1];
		//print out the final string numbers
		System.out.println("Initial configuration: " + numbers);
		//assert statement to check the board is valid or not
		assert isValidSolitaireBoard();
	}

	/**
	 * Creates a solitaire board with a random initial configuration.
	 */
	public SolitaireBoard() {
		//initial array with the size of CARD_TOTAL
		piles = new int[CARD_TOTAL];
		//use a while loop to generate random piles
		while (cards > 0) {
			//use Math.random to generate random numbers from 1-cards
			int randomCard = (int) (Math.random() * cards) + 1;
			//reassgin the value to the cards as the number left from the last looping
			cards = cards - randomCard;
			//give the generated number assign to current piles array
			piles[i] = randomCard;
			i++;
		}
		
		//initialize the final array with the size of the numbers of first piles
		newPiles = new int[i];
		//Use a for loop to take the significant integers out into the final array(get rid of (CARD_TOTAL-counter) 0s in piles )
		for (int j = 0; j < i; j++) {
			newPiles[j] = piles[j];
		}
		//Prepare to print out the final pile array as a string
		numbers = "";
		//use for loop to add up every elements of newPiles into the string numbers,except the last digit
		for (int i = 0; i < newPiles.length - 1; i++) {
			//add the last digit to the end of string 
			numbers = numbers + newPiles[i] + " ";
		}
		//add the last digit to the end of string 
		numbers = numbers + newPiles[newPiles.length - 1];
		//print out the final string numbers
		System.out.println("Initial configuration: " + numbers);
		//assert statement to check the board is valid or not
		assert isValidSolitaireBoard();
	}

	/**
	 * Plays one round of Bulgarian solitaire. Updates the configuration
	 * according to the rules of Bulgarian solitaire: Takes one card from each
	 * pile, and puts them all together in a new pile. The old piles that are
	 * left will be in the same relative order as before, and the new pile will
	 * be at the end.
	 */
	public void playRound() {
		// every piles-1 and restore into newPiles
		for (i = 0; i < newPiles.length; i++) {
			newPiles[i] = newPiles[i] - 1;
			sum = sum + newPiles[i];
		}
		// increase the length of newPiles
		temp = new int[newPiles.length + 1];
		counter = 0;
		for (int i = 0; i < newPiles.length; i++) {
			temp[i] = newPiles[i];
			if (newPiles[i] != 0) {
				counter++;
			}
		}
		newPiles = temp;
		// let the end of 0 be the sum of the last intial numbers of piles
		newPiles[newPiles.length - 1] = CARD_TOTAL - sum;
		// removing 0s
		//use temp array to save the new size of significant number
		temp = new int[counter + 1];
		for (int i = 0, j = 0; i < newPiles.length; i++) {
			if (newPiles[i] != 0) {
				temp[j] = newPiles[i];
				j++;
			}
		}
		newPiles = temp;
		//reset the instant varible
		counter = 0;
		sum = 0;

	}

	/**
	 * Returns true iff the current board is at the end of the game. That is,
	 * there are NUM_FINAL_PILES piles that are of sizes 1, 2, 3, . . . ,
	 * NUM_FINAL_PILES, in any order.
	 */

	public boolean isDone() {
		//if the length of newPiles =NUM_FINAL_PILES,then enter the loop to judge
		//if not equal,then return false
		if (newPiles.length == NUM_FINAL_PILES) {
			// find least number in the array
			int least = Integer.MAX_VALUE;
			for (int i : newPiles) {
				if (i < least) {
					least = i;
				}
			}

			// find largest number in the array
			int largest = Integer.MIN_VALUE;
			for (int i : newPiles) {
				if (i > largest) {
					largest = i;
				}
			}
			//create the size of (largest - least + 1) array to check
			temp = new int[largest - least + 1];
			//traverse the newPiles and check if the numbers are distinct
			for (int i : newPiles) {
				if (temp[i - least] != 0)
					return false;
				else
					temp[i - least] = 1;
			}
			return true;
		}
	
		return false;
		
	}

	/**
	 * Returns current board configuration as a string with the format of a
	 * space-separated list of numbers with no leading or trailing spaces. The
	 * numbers represent the number of cards in each non-empty pile.
	 */
	public String configString() {
		numbers = "";
		for (int i = 0; i < newPiles.length - 1; i++) {
			numbers = numbers + newPiles[i] + " ";
		}
		numbers = numbers + newPiles[newPiles.length - 1];
		//assert statement to check the board is valid or not

		return "Current configuration: " + numbers;
		
	}

	/**
	 * Returns true iff configString is a space-separated list of numbers that
	 * is a valid Bulgarian solitaire board with card total
	 * SolitaireBoard.CARD_TOTAL
	 */
	public static boolean isValidConfigString(String configString) {
		//create variables
		int i, counter = 0;
		String numbers;
		Scanner lineScanner;
		//check the String variable configString is valid:
		//contains only positive digits and white space
		for (i = 0; i < configString.length(); i++) {
			if (Character.isDigit(configString.charAt(i)) || Character.isWhitespace(configString.charAt(i))  ) {
				continue;
			}
			else{
				return false;
			}
		}
		//reset i
		i = 0;
		//create new size of piles to save passed string
		int[] piles = new int[CARD_TOTAL];
		lineScanner = new Scanner(configString);
		// if pass , it's going to generate a piles and numPiles
		while (lineScanner.hasNext()) {
			//check if typed string contains valid total number of piles
			if (i > CARD_TOTAL - 1) {
				return false;
			}
			numbers = lineScanner.next();
			piles[i] = Integer.parseInt(numbers);
			i++;
		}
		counter = i;
		//check the passed string is valid for Configuration
		return isValidConfiguration(piles, counter);
	}

	/**
	 * Returns true iff the solitaire board data is in a valid state (See
	 * representation invariant comment for more details.)
	 */
	private boolean isValidSolitaireBoard() {
		//assert statement
		//return true or false
		return isValidConfiguration(newPiles, i);

	}

	// <add any additional private methods here>
	/**
	 * Returns true iff array piles and piles number is a valid number that consists with
	 * the valid configuration:
	 * number of piles<CARD_TOTAL 
	 * total sum of piles elements =45
	 * first element of piles !=0
	 */
	private static boolean isValidConfiguration(int[] piles, int numPiles) {
		int total = 0;
		//check if the numPiles is >CARD_TOTAL 
		if (numPiles > CARD_TOTAL ) {
			return false;
		}
		//get the sum of numPiles
		for (int i = 0; i < numPiles; i++) {
			total = total + piles[i];
		}
		//if sum!=45 or first element of piles=0
		//return false
		if (total != CARD_TOTAL || piles[0] == 0) {
			return false;

		}

		return true;
	}

}