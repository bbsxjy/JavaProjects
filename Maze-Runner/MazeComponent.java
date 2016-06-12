

//Name:Jingyu Xie
//USC loginid:jingyuxi
//CS 455 PA3
//Spring 2016
import java.awt.Color;

//Name:
//USC loginid:
//CS 455 PA3
//Spring 2016

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ListIterator;

import javax.swing.JComponent;

/**
 * MazeComponent class
 * 
 * A component that displays the maze and path through it if one has been found.
 */
public class MazeComponent extends JComponent {
	private Maze maze;

	private static final int START_X = 10; // where to start drawing maze in
											// frame
	private static final int START_Y = 10;
	private static final int BOX_WIDTH = 20; // width and height of one maze
												// unit
	private static final int BOX_HEIGHT = 20;
	private static final int INSET = 2;
	// how much smaller on each side to make entry/exit inner box

	/**
	 * Constructs the component.
	 * 
	 * @param maze
	 *            the maze to display
	 */
	public MazeComponent(Maze maze) {
		this.maze = maze;
	}

	/**
	 * Draws the current state of maze including the path through it if one has
	 * been found.
	 * 
	 * @param g
	 *            the graphics context
	 */
	public void paintComponent(Graphics g) {
		// recover Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		// draw maze
		// iter all the maze data
		for (int i = 0; i < maze.numRows(); i++) {
			for (int j = 0; j < maze.numCols(); j++) {
				// if the there is a wall at the current location then mark it
				// as black and set it as a rectangle
				if (maze.hasWallAt(new MazeCoord(i, j)) == true) {
					g2.setColor(Color.BLACK);
					Rectangle wallBlock = new Rectangle(j * 20 + START_X, i * 20 + START_Y, BOX_WIDTH, BOX_HEIGHT);
					g2.fill(wallBlock);
				}
				// otherwise fill it with white rectangle
				else {
					g2.setColor(Color.WHITE);
					Rectangle freeBlock = new Rectangle(j * 20 + START_X, i * 20 + START_Y, BOX_WIDTH, BOX_HEIGHT);
					g2.fill(freeBlock);
				}
			}
		}
		// get the start and end location
		int startLocRow = maze.getEntryLoc().getRow() * 20 + START_X;
		int startLocCol = maze.getEntryLoc().getCol() * 20 + START_Y;
		int endLocRow = maze.getExitLoc().getRow() * 20 + START_X;
		int endLocCol = maze.getExitLoc().getCol() * 20 + START_Y;
		// draw start point and fill it as yellow color
		g2.setColor(Color.YELLOW);
		Rectangle startBlock = new Rectangle(startLocCol, startLocRow, BOX_WIDTH - INSET, BOX_HEIGHT - INSET);
		g2.fill(startBlock);

		// draw end point and fill it as green color
		g2.setColor(Color.GREEN);
		Rectangle endBlock = new Rectangle(endLocCol, endLocRow, BOX_WIDTH - INSET, BOX_HEIGHT - INSET);
		g2.fill(endBlock);

		// draw path
		// set the previous coordinate as null
		MazeCoord prevCoord = null;
		// initialize a linkedlist iteration
		ListIterator<MazeCoord> iter = maze.getPath().listIterator();
		// boolean a variable whether this is a start
		boolean isStart = true;
		while (iter.hasNext()) {
			// tempCoord =next element
			MazeCoord tempCoord = iter.next();
			// if this is a start
			if (isStart == true && iter.hasNext()) {
				// let tempCoord2 be the next element
				MazeCoord tempCoord2 = iter.next();
				Line2D.Double segment = new Line2D.Double(tempCoord.getCol() * 20 + START_X + 10,
						tempCoord.getRow() * 20 + START_Y + 10, tempCoord2.getCol() * 20 + START_X + 10,
						tempCoord2.getRow() * 20 + START_Y + 10);
				// let previous coordinate as tempCoord2
				prevCoord = tempCoord2;
				// draw the line and set color as blue
				g2.setColor(Color.BLUE);
				g2.draw(segment);
				isStart = false;
				// otherwise when the path's size more than 1
			} else if (maze.getPath().size() > 1) {
				Line2D.Double segment = new Line2D.Double(prevCoord.getCol() * 20 + START_X + 10,
						prevCoord.getRow() * 20 + START_Y + 10, tempCoord.getCol() * 20 + START_X + 10,
						tempCoord.getRow() * 20 + START_Y + 10);
				// draw the line
				g2.setColor(Color.BLUE);
				g2.draw(segment);
				prevCoord = tempCoord;
			}

		}

	}

}
