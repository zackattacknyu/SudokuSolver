package sudoku;

import java.util.Scanner;

public class TestTestInput {

	public static void main(String[] args) {
		int[][] SudokuStart = SudokuInput.InputRowsFile("SudokuToRead.txt");
		Sudoku theSudoku = new Sudoku(SudokuStart);
		SudokuDisplay.DisplayRows(theSudoku.getPossible());
		SudokuSolving newSolver = new SudokuSolving(theSudoku);
		newSolver.RepeatEliminationAndChecking();
		theSudoku = newSolver.getSudoku();
		SudokuDisplay.DisplayRows(theSudoku.getPossible());
		Scanner sc = new Scanner(System.in);
		System.out.println("test row ");
		int r = sc.nextInt();
		System.out.println("test column ");
		int c = sc.nextInt();
		System.out.println("test input ");
		int i = sc.nextInt();
		SudokuTestInput tester = new SudokuTestInput(theSudoku);
		System.out.println(tester.TestInput(r,c,i));
		System.out.println("New Sudoku...");
		SudokuDisplay.DisplayRows((tester.getNewSudoku()).getPossible());
		System.out.println("Old Sudoku...");
		SudokuDisplay.DisplayRows(theSudoku.getPossible());
	}

}
