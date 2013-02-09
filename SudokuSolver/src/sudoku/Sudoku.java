package sudoku;

public class Sudoku {
	//whether a number in a square is possible
	private boolean[][][] Possible = new boolean[10][10][10];
	
	//the Solved squares
	private int[][] theSolved = new int[10][10];
	
	//the number of possiblities remaining for each square
	private int[][] NumPossible = new int[10][10];
	
	private boolean[][] NewlySolved = new boolean[10][10];
		
	public Sudoku(int[][] SolvedI){
		CopyIntArray(SolvedI);
		PossibleStart(); NumPossibleStart();
		NewSolved();
	}
	public Sudoku(boolean[][][] Possible, int[][] theSolved, int[][] NumPossible, boolean[][] NewlySolved){
		this.Possible = Possible; this.theSolved = theSolved; 
		this.NumPossible = NumPossible; this.NewlySolved = NewlySolved;
	}
	public boolean[][][] getPossible(){
		return Possible;
	}
	public int[][] getSolved(){
		return theSolved;
	}
	public int[][] getNumPossible(){
		return NumPossible;
	}
	public boolean[][] getNewlySolved(){
		return NewlySolved;
	}
	private void CopyIntArray(int[][] original){
		for(int j = 0; j < 10; j++){
			for(int k = 0; k < 10; k++){
				theSolved[j][k] = original[j][k];
			}
		}
	}
	private void PossibleStart(){
		for(int j = 0; j < 10; j++){
			for(int k = 0; k < 10; k++){
				for(int m = 0; m < 10; m++)
					Possible[j][k][m] = true;
			}
		}
	}
	private void NumPossibleStart(){
		for(int j = 0; j < 10; j++){
			for(int k = 0; k < 10; k++){
				NumPossible[j][k] = 9;
			}
		}
	}
	private void NewSolved(){
		for(int j = 1; j <= 9; j++){
			for(int k = 1; k <= 9; k++){
				int n = theSolved[j][k];
				if(n != 0){
					NumPossible[j][k] = 1;
					DeleteFromPossible(n,j,k);
				}
				NewlySolved[j][k] = true;
			}
		}
	}
	public void TestInput(){
		SudokuNewInput NewSudoku = new SudokuNewInput(Possible, theSolved, NumPossible, NewlySolved);
		NewSudoku.TestAnInput();
		Possible = NewSudoku.getPossible(); theSolved = NewSudoku.getTheSolved();
		NumPossible = NewSudoku.getNumPossible(); NewlySolved = NewSudoku.getNewlySolved();
	}
	private void DeleteFromPossible(int num, int row, int column){
		for(int j = 1; j <= 9; j++){
			if(j != num)
				Possible[row][column][j] = false;
		}
	}
	public void EliminatePossible(){
		SudokuSolveElimination NewSudoku = new SudokuSolveElimination(Possible, theSolved, NumPossible, NewlySolved);
		NewSudoku.EliminatePossible();
		Possible = NewSudoku.getPossible(); theSolved = NewSudoku.getTheSolved();
		NumPossible = NewSudoku.getNumPossible(); NewlySolved = NewSudoku.getNewlySolved();
	}
	public void CheckSolved(){
		SudokuSolveElimination NewSudoku = new SudokuSolveElimination(Possible, theSolved, NumPossible, NewlySolved);
		NewSudoku.CheckSolved();
		Possible = NewSudoku.getPossible(); theSolved = NewSudoku.getTheSolved();
		NumPossible = NewSudoku.getNumPossible(); NewlySolved = NewSudoku.getNewlySolved();
	}
	public void CheckForSinglesRC(){
		SudokuSolveSinglesRC NewSudoku = new SudokuSolveSinglesRC(Possible, theSolved, NumPossible, NewlySolved);
		NewSudoku.CheckforSinglesRC();
		Possible = NewSudoku.getPossible(); theSolved = NewSudoku.getTheSolved();
		NumPossible = NewSudoku.getNumPossible(); NewlySolved = NewSudoku.getNewlySolved();
		
	}
	public void CheckSinglesBox(){
		SudokuSolveSinglesBox NewSudoku = new SudokuSolveSinglesBox(Possible, theSolved, NumPossible, NewlySolved);
		NewSudoku.CheckSinglesBox();
		Possible = NewSudoku.getPossible(); theSolved = NewSudoku.getTheSolved();
		NumPossible = NewSudoku.getNumPossible(); NewlySolved = NewSudoku.getNewlySolved();
	}
	
	
}
	
