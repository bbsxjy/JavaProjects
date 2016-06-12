

//Name:Jingyu Xie
//USC loginid:jingyuxi
//CS 455 PA3
//Spring 2016

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class MazeTester {
	public static final boolean FREE = false;
	public static final boolean WALL = true;
	private static final char WALL_CHAR = '1';
	private static final char FREE_CHAR = '0';
	
	
	private static int[] mazeSize=new int[2];
	private static int[] startLoc1=new int[2];
	private static int[] endLoc1=new int[2];
	private static MazeCoord startLoc;
	private static MazeCoord endLoc;
	private static boolean[][] mazeData = null;
	private static LinkedList<MazeCoord> path;
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
		path=new LinkedList<MazeCoord>();
		 String fileName = "C:\\Users\\Whaley\\Desktop\\新建文本文档.txt";

		File fileNam=new File(fileName);
		InputStreamReader reader=new InputStreamReader(new FileInputStream(fileNam));
		BufferedReader br=new BufferedReader(reader);
		//initialize scanner
		Scanner lineScanner;
		//initialize an empty string line
		String line="";
		String numbers;
		int lineCounter=0,iter=0,iterRow=0,row=0,col=0;

		
		while (line!=null) {
			line=br.readLine();
			lineScanner = new Scanner(line);
			
			//first line
			if(lineCounter==0){
				while(lineScanner.hasNext()){
					numbers = lineScanner.next();
					mazeSize[iter]=Integer.parseInt(numbers);
					iter++;
				}
				row=mazeSize[0];
				col=mazeSize[1];
				mazeData=new boolean[row][col];
				lineCounter++;
				iter=0;
			}
			
			
			
			//maze lines
			else if(lineCounter<=row){
				
				for(int i=0;i<col;i++){
					if(line.charAt(i)==WALL_CHAR){
						mazeData[iterRow][i]=true;
					}else if(	line.charAt(i)==FREE_CHAR){
						mazeData[iterRow][i]=false;
					}
				}
				iterRow++;
				lineCounter++;
		
				}
				
				
				
				
				
		
			//start lines
			else if(lineCounter==(row+1)){
				
				while(lineScanner.hasNext()){
					numbers = lineScanner.next();
					startLoc1[iter]=Integer.parseInt(numbers);
					iter++;
				}
				iter=0;
				lineCounter++;
				
			}
			//end lines
			else if(lineCounter==(row+2)){
				
				while(lineScanner.hasNext()){
					numbers = lineScanner.next();
					endLoc1[iter]=Integer.parseInt(numbers);
					iter++;
				}
				iter=0;
				lineCounter++;
				break;
			}
			


		}

		startLoc=new MazeCoord(startLoc1[0], startLoc1[1]);
		endLoc=new MazeCoord(endLoc1[0], endLoc1[1]);
	
		search();
		search();
		System.out.println(getPath());
		
	}
	
	
	/**
	Returns the number of rows in the maze
	@return number of rows
	*/
	public static int numRows() {
	   return mazeData.length;   
	}


	/**
	Returns the number of columns in the maze
	@return number of columns
	*/   
	public static int numCols() {
	   return mazeData[0].length;   
	} 


	/**
	   Returns true iff there is a wall at this location
	   @param loc the location in maze coordinates
	   @return whether there is a wall here
	   PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
	*/
	public static boolean hasWallAt(MazeCoord loc) {
		if(loc.getRow()>numRows()-1||loc.getRow()<0
				|| loc.getCol()>numCols()-1||loc.getCol()<0){
			return WALL;
			
		}else if(mazeData[loc.getRow()][loc.getCol()]==true){
			return WALL;
		}else{
			return FREE;
		}
		
	}

	public static LinkedList<MazeCoord> getPath() {
		
		  
			
		return path; 
	}

	
	
	
	public static boolean search()  {  
		LinkedList <MazeCoord> root = new LinkedList <MazeCoord>();
		   return searchHelper(startLoc,root);  

		}

		private static boolean  searchHelper(MazeCoord loc,LinkedList <MazeCoord> parentVisited){
			

			//base case
			if(hasWallAt(loc)==WALL){
				System.out.println("no path found!");
				return false;
				//visited case
			}else if(endLoc.equals(loc)){
				LinkedList <MazeCoord> temp = parentVisited;
				temp.add(loc);
				
				path = removeDuplicate(temp);
				System.out.println("PATH FOUND!");
				return true;
			}
			else{
				int currentRow=loc.getRow();
				int currentCol=loc.getCol();
				LinkedList <MazeCoord> temp;      
				MazeCoord nextLoc_XPosiDir=new MazeCoord (currentRow+1,currentCol);
				MazeCoord nextLoc_YPosiDir=new MazeCoord (currentRow,currentCol+1);
				MazeCoord nextLoc_XNegiDir=new MazeCoord (currentRow-1,currentCol);
				MazeCoord nextLoc_YNegDir=new MazeCoord (currentRow,currentCol-1);
				

					if(hasWallAt(nextLoc_XPosiDir)==FREE&&isVisited(nextLoc_XPosiDir,parentVisited)==FREE){

						temp = parentVisited;

						temp.add(loc);
						return searchHelper(nextLoc_XPosiDir,temp);

					}
					else if(hasWallAt(nextLoc_YPosiDir)==FREE&&isVisited(nextLoc_YPosiDir,parentVisited)==FREE){

						temp = parentVisited;

						temp.add(loc);
						return searchHelper(nextLoc_YPosiDir,temp);

					}
					else if(hasWallAt(nextLoc_XNegiDir)==FREE&&isVisited(nextLoc_XNegiDir,parentVisited)==FREE){

						temp = parentVisited;

						temp.add(loc);
						return searchHelper(nextLoc_XNegiDir,temp);

					}
					else if(hasWallAt(nextLoc_YNegDir)==FREE&&isVisited(nextLoc_YNegDir,parentVisited)==FREE){

						 temp = parentVisited;

						temp.add(loc);
						return searchHelper(nextLoc_YNegDir,temp);

					}
					else if(loc.equals(startLoc)==false){
						
						temp = parentVisited;
					
						if(isVisited(loc,temp)==true){
						while(isVisited(loc,temp)==true){
							temp.removeLast();	
						}
						
						MazeCoord previous=temp.getLast();
						parentVisited.add(loc);
						

						return searchHelper(previous,parentVisited);
						
						}else{
						MazeCoord previous=parentVisited.getLast();

						temp.add(loc);
						return searchHelper(previous,temp);
						}
					}
				
			
				
				
			}
			
			System.out.println("no path found!");
			return false;
			
		}

public static boolean isVisited(MazeCoord loc,LinkedList <MazeCoord> list){

	ListIterator<MazeCoord> iter = list.listIterator();
	while(iter.hasNext()){
		MazeCoord tempCoord=iter.next();
		if(tempCoord.equals(loc)){
			return true;
		}

	}
	return false;
}

public static LinkedList<MazeCoord> removeDuplicate(LinkedList <MazeCoord> originalPath){
	LinkedList <MazeCoord> newPath = new LinkedList <MazeCoord>();

	ListIterator<MazeCoord> iter = originalPath.listIterator();
	while(iter.hasNext()){
		MazeCoord tempCoord=iter.next();
		if(isVisited(tempCoord,newPath)==true){
			newPath.removeLast();	
			continue;
		}else{
		newPath.add(tempCoord);
		}
	}
	return newPath;
	
}
}