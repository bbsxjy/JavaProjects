



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

//Name:Jingyu Xie
//USC loginid:jingyuxi
//CS 455 PA3
//Spring 2016

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JFrame;

/**
 * MazeViewer class
 * 
 * Program to read in and display a maze and a path through the maze. At user
 * command displays a path through the maze if there is one.
 * 
 * How to call it from the command line:
 * 
 * java MazeViewer mazeFile
 * 
 * where mazeFile is a text file of the maze. The format is the number of rows
 * and number of columns, followed by one line per row, followed by the start
 * location, and ending with the exit location. Each maze location is either a
 * wall (1) or free (0). Here is an example of contents of a file for a 3x4
 * maze, with start location as the top left, and exit location as the bottom
 * right (we count locations from 0, similar to Java arrays):
 * 
 * 3 4 0111 0000 1110 0 0 2 3
 * 
 */

public class MazeViewer {

	private static final char WALL_CHAR = '1';
	private static final char FREE_CHAR = '0';

	public static void main(String[] args) {

		String fileName = "";

		try {

			if (args.length < 1) {
				System.out.println("ERROR: missing file name command line argument");
			} else {
				fileName = args[0];

				JFrame frame = readMazeFile(fileName);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				frame.setVisible(true);
			}

		} catch (FileNotFoundException exc) {
			System.out.println("File not found: " + fileName);
		} catch (IOException exc) {
			exc.printStackTrace();
		}
	}

	/**
	 * readMazeFile reads in maze from the file whose name is given and returns
	 * a MazeFrame created from it.
	 * 
	 * @param fileName
	 *            the name of a file to read from (file format shown in class
	 *            comments, above)
	 * @returns a MazeFrame containing the data from the file.
	 * 
	 * @throws FileNotFoundException
	 *             if there's no such file (subclass of IOException)
	 * @throws IOException
	 *             (hook given in case you want to do more error-checking. that
	 *             would also involve changing main to catch other exceptions)
	 */
	private static MazeFrame readMazeFile(String fileName) throws IOException {
		// initialize the file method
		File fileNam = new File(fileName);
		// initialize the streamreader method
		InputStreamReader reader = new InputStreamReader(new FileInputStream(fileNam));
		// initialize the bufferedReader
		BufferedReader br = new BufferedReader(reader);
		// initialize scanner
		Scanner lineScanner;
		// initialize an empty string line
		String line = "";
		String numbers;
		// initialize variables
		int lineCounter = 0, iter = 0, iterRow = 0, row = 0, col = 0;// counters
		int[] mazeSize = new int[2];
		int[] startLoc = new int[2];
		int[] endLoc = new int[2];
		boolean[][] mazeData = null;
		// while the string is not null
		while (line != null) {
			// let the line become the next read line
			line = br.readLine();
			// initialize the linesanner use line as parameter
			lineScanner = new Scanner(line);

			// first line
			if (lineCounter == 0) {
				while (lineScanner.hasNext()) {
					// let the numbers be the next string scans
					numbers = lineScanner.next();
					// let the first and second number stored as the mazeSize
					// array
					mazeSize[iter] = Integer.parseInt(numbers);
					// counters increase
					iter++;
				}
				// let the row and col be the first and second element of the
				// mazeSize
				row = mazeSize[0];
				col = mazeSize[1];
				// use the data above to initialize the mazeData size
				mazeData = new boolean[row][col];
				// lines number increase
				lineCounter++;
				// reset counter
				iter = 0;
			}

			// maze lines
			else if (lineCounter <= row) {
				// iter the maze
				for (int i = 0; i < col; i++) {
					// if the char at the current location is 1 then set the
					// data as true
					if (line.charAt(i) == WALL_CHAR) {
						mazeData[iterRow][i] = true;
						// otherwise set it as false
					} else if (line.charAt(i) == FREE_CHAR) {
						mazeData[iterRow][i] = false;
					}
				}
				// iter ROW increase
				iterRow++;
				// lines numbers increase
				lineCounter++;

			}

			// start lines
			else if (lineCounter == (row + 1)) {

				while (lineScanner.hasNext()) {
					numbers = lineScanner.next();
					// let the first and second number stored as the start
					// location array
					startLoc[iter] = Integer.parseInt(numbers);
					iter++;
				}
				iter = 0;
				lineCounter++;

			}
			// end lines
			else if (lineCounter == (row + 2)) {

				while (lineScanner.hasNext()) {
					numbers = lineScanner.next();
					//// let the first and second number stored as the end
					//// location array
					endLoc[iter] = Integer.parseInt(numbers);
					iter++;
				}
				// reset
				iter = 0;
				lineCounter++;
				// stop loops
				break;
			}

		}
		// return back the reading data
		return new MazeFrame(mazeData, new MazeCoord(startLoc[0], startLoc[1]), new MazeCoord(endLoc[0], endLoc[1]));

	}

}