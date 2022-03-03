//-------------------------------------------------------
//Assignment 2 
//Written by: Jaydeep Patel
//-------------------------------------------------------

/*
* Jaydeep Patel 40075915
* COMP 352
* Assignment #2
* Due Date: October 25 2020
*/


import java.util.Random;
import java.util.Scanner;

/**
 * This class implements a board game using recursion
 * @author Jaydeep Patel
 * @version 1.0
 * 
 * 
 */


 
public class Recursive {
	/**
	 * 
	 * @param board the board to solve
	 * @param d dimension of the board
	 * @param before the array to see which positions have been visited previously
	 * @param row the starting row
	 * @param column the starting column
	 * @param check Used to place the zero
	 * @return boolean to see if the expression is solvable
	 */
	
	public static boolean solve(int[][] board, int d, boolean[][] before, int row, int column, int check) {
		
		// Start position cannot be the same as final position
		
		if(check == 0 && board[row][column] == 0) {
			System.out.println("This start position is invalid!");
			return false;
		}
		
		// Verifying if there is a solution
		
		if(check > 0 && board[row][column] == 0) {
			System.out.println("There is a solution!");
			return true;
		}
		
		if(before[column][row] == true)
			return false;
		
		before[column][row] = true;
		boolean finished = false;
		int temp = 0;
		
		//going east
		if(column + board[row][column] < d) {
			temp = column + board[row][column];
			finished = solve(board, d, before, row, temp, ++check);
			//found end
			if(finished == true)
				return true;
		}
		
		//going west
		if(column - board[row][column] >= 0) {
			temp = column - board[row][column];
			finished = solve(board, d, before, row, temp, ++check);
			//found end
			if(finished == true)
				return true;
		}
		
		//going south
		if(row + board[row][column] < d) {
			temp = row + board[row][column];
			finished = solve(board, d, before, temp, column, ++check);
			//found end
			if(finished == true)
				return true;
		}
		
		//going north
		if(row - board[row][column] >= 0) {
			temp = row - board[row][column];
			finished = solve(board, d, before, temp, column, ++check);
			//found end
			if(finished == true)
				return true;
		}
		
		return false;
	}

	//method to print the board
	
	public static void displayBoard() {
		
		Scanner input = new Scanner(System.in);
		Random random =new Random();
		
		int d; //Determining size of board
		int check = 0; //One zero
		
		
		//user inputs size of the board
		System.out.println("Please enter a valid d for gameboard dimension ");
		d = input.nextInt();
		
		int[][] board = new int[d][d];
		boolean[][] before = new boolean[d][d];
		
		//Printing the board
		
		for(int i = 0; i < d; i++) {
			
			for(int j = 0; j < d; j++) {
				
				//generate a random number for this location in the board
				board[i][j]=random.nextInt(d);
				
				
				//increment if the position is a 0
				if(board[i][j] == 0) {
					check++;
				//if there's already a 0, replace the number in that position
				if(check > 1) {
					board[i][j]=random.nextInt(d)+1; //Random number to replace the other zeroes 
					
				}
				
				}
				
				System.out.print(String.format("%3d", board[i][j]));
		}
		
		
			System.out.println(" ");

	}

		
		
		System.out.println(solve(board, d, before, 0, 0, 0));	
		
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		
		displayBoard();
				
				
}
	
}

