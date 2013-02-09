package sudoku;

public class SudokuTestInput {
	private boolean[][][] Possible; 
	private int[][] theSolved; 
	private int[][] NumPossible; 
	private boolean[][] NewlySolved;
	private boolean[][][] NewPossible = new boolean[10][10][10]; 
	private int[][] NewtheSolved = new int[10][10]; 
	private int[][] NewNumPossible = new int[10][10]; 
	private boolean[][] NewNewlySolved = new boolean[10][10];
	private Sudoku theNewSudoku;
	public SudokuTestInput(Sudoku aSudoku){
		this(aSudoku.getPossible(), aSudoku.getSolved(), aSudoku.getNumPossible(), aSudoku.getNewlySolved());
	}
	public SudokuTestInput(boolean[][][] Possible, int[][] theSolved, int[][] NumPossible, boolean[][] NewlySolved){
		this.Possible = Possible ; this.theSolved = theSolved; 
		this.NumPossible = NumPossible; this.NewlySolved = NewlySolved;
		StartNewPossible(); StartNewtheSolved();
		StartNewNumPossible(); StartNewNewlySolved();
		theNewSudoku = new Sudoku(NewPossible, NewtheSolved, NewNumPossible, NewNewlySolved);
	}
	public Sudoku getNewSudoku(){
		return theNewSudoku;
	}
	private void StartNewPossible(){
		for(int j = 1; j <= 9; j++){
			for(int k=1; k <= 9; k++){
				for(int m = 1; m <= 9; m++){
					NewPossible[j][k][m] = Possible[j][k][m];
				}
			}
		}
	}
	private void StartNewtheSolved(){
		for(int j = 1; j <= 9; j++){
			for(int k=1; k <= 9; k++){
				NewtheSolved[j][k] = theSolved[j][k];
			}
		}
	}
	private void StartNewNumPossible(){
		for(int j = 1; j <= 9; j++){
			for(int k=1; k <= 9; k++){
				NewNumPossible[j][k] = NumPossible[j][k];
			}
		}
	}
	private void StartNewNewlySolved(){
		for(int j = 1; j <= 9; j++){
			for(int k=1; k <= 9; k++){
				NewNewlySolved[j][k] = NewlySolved[j][k];
			}
		}
	}
	private boolean isValid(){
		for(int j = 1; j <= 9; j++){
			for(int k = 1; k <= 9; k++){
				if(NewNumPossible[j][k] <= 0){
					return false;
				}
			}
		}
		return true;
	}
	public boolean TestInput(int row, int column, int input){
		DeleteFromPossible(input, row, column);
		RepeatEliminationAndChecking();
		if(isValid()){
			return true;
		}
		else{
			return false;
		}
	}
	private void RepeatEliminationAndChecking(){
		SudokuSolving newSolve = new SudokuSolving(theNewSudoku);
		newSolve.RepeatEliminationAndChecking();
		theNewSudoku = newSolve.getSudoku();
	}
	private void DeleteFromPossible(int num, int row, int column){
		for(int j = 1; j <= 9; j++){
			if(j != num)
				NewPossible[row][column][j] = false;
		}
	}
	
}
