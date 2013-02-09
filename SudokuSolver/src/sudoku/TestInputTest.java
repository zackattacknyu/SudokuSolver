package sudoku;


public class TestInputTest {
	public static void main(String[] args) {
		int[][] SudokuStart = SudokuInput.InputRowsFile("SudokuToRead.txt");
		Sudoku theSudoku = new Sudoku(SudokuStart);
		SudokuDisplay.DisplayRows(theSudoku.getPossible());
		SudokuSolving newSolver = new SudokuSolving(theSudoku);
		newSolver.RepeatEliminationAndChecking();
		theSudoku = newSolver.getSudoku();
		SudokuDisplay.DisplayRows(theSudoku.getPossible());
		SudokuNewInput solver = new SudokuNewInput(theSudoku);
		for(int j = 0 ; j < 2; j++){
			solver.TestAnInput();
			System.out.println("New Sudoku...");
			theSudoku = solver.getSudoku();
			SudokuDisplay.DisplayRows(theSudoku.getPossible());
			System.out.println();
			newSolver.RepeatEliminationAndChecking();
			theSudoku = newSolver.getSudoku();
			SudokuDisplay.DisplayRows(theSudoku.getPossible());
		}
	}
}
