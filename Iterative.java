
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


import java.util.EmptyStackException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;


/**
 * This class implements a board game using iteration
 * @author Jaydeep Patel
 * @version 2.0
 * 
 * 
 */
public class Iterative {
	
	/**
	 * This method checks if a board is solvable iteratively
	 * @param board the board to solve
	 * @param d the size of the board
	 * @param before the array to see which positions have been visited previously
	 * @param row the starting row
	 * @param column the starting column
	 * @return boolean expression if the board is solvable
	 
	 */
	public static boolean solve(int[][] board, int d, boolean[][] before, int row, int column) {
		if(board[row][column] == 0) {
			System.out.println("This start position is invalid!");
			return false;
		}
		
		boolean finished = false;
		Stack<Integer> rows = new Stack(); //create empty stack for rows
		Stack<Integer> columns = new Stack(); //create empty stack for columns 
		rows.push(row); //load current row
		columns.push(column); //load current column
		int temp = 0; //for while loop
		int currentRow = row; //for while loop
		int currentCol = column; //for while loop
		
		while(!finished) {
			if(rows.isEmpty()) { //if one of the stacks is empty
				System.out.println("No solution");
				break;
			}
			currentRow = rows.pop();
			currentCol = columns.pop();
			
			//if it is already visited, skip this loop
			if(before[currentRow][currentCol] == true)
				continue;
			before[currentRow][currentCol] = true; //mark this position as true
			
			//if the current position's value is 0, it is true, break out of loop
			if(board[currentRow][currentCol] == 0) {
				finished = true;
				break;
			}
			else {				
				//going east
				if(currentCol + board[currentRow][currentCol] < d) {
					temp = currentCol + board[currentRow][currentCol];
					rows.push(currentRow);
					columns.push(temp);
				}
				
				//going west
				if(currentCol - board[currentRow][currentCol] >= 0) {
					temp = currentCol - board[currentRow][currentCol];
					rows.push(currentRow);
					columns.push(temp);
				}
				
				//going south
				if(currentRow + board[currentRow][currentCol] < d) {
					temp = currentRow + board[currentRow][currentCol];
					rows.push(temp);
					columns.push(currentCol);
				}
				
				//going north
				if(currentRow - board[currentRow][currentCol] >= 0) {
					temp = currentRow - board[currentRow][currentCol];
					rows.push(temp);
					columns.push(currentCol);
				}
			}
		}
		
		if(finished == true) {
			System.out.println("This board is solvable!");
			return true;
		}
		
		return false;
	}
	
	
	public static void displayBoard() {
		
		Scanner input = new Scanner(System.in);
		Random random =new Random();
		
		int d; //size of the board
		int check = 0; //check for 0
		
	
		//user inputs size of the board
		System.out.println("Please enter a valid d for gameboard dimension ");
		d = input.nextInt();
		
		int[][] board = new int[d][d];
		boolean[][] before = new boolean[d][d];
		
		//fill the board
		for(int i = 0; i < d; i++) {
			for(int j = 0; j < d; j++) {
				//generate a random number for this location in the board
				board[i][j]=random.nextInt(d); //max is exclusive, so no need to do -1
				
				//increment if the position is a 0
				if(board[i][j] == 0)
					check++;
				//if there's already a 0, replace the number in that position
				if(check > 1)
					board[i][j]=random.nextInt(d)+1; //max is exclusive, so no need to do -1
			}
		}
		
		
		//display board
		for(int i = 0; i < d; i++) {
			for(int j = 0; j < d; j++) {
				System.out.print(String.format("%3d", board[i][j]));
			}
			System.out.println();
		}

		System.out.println(solve(board, d, before, 0, 0));
		
		
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		
		displayBoard();
		
	}

}

