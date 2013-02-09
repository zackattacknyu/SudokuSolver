package sudoku;

public class SudokuSolving {
	private Sudoku mySudoku;
	public SudokuSolving(Sudoku aSudoku){
		mySudoku = aSudoku;
	}
	public Sudoku getSudoku(){
		return mySudoku;
	}
	public void RepeatUntilSolved(){
		boolean[][][] StartingSudoku = new boolean[10][10][10];
		while(!DoesEqual(StartingSudoku)){
			StartingSudoku = CopySudoku();
			mySudoku.TestInput();
			RepeatEliminationAndChecking();
		}
	}
	public void RepeatElimination(){
		boolean[][][] StartingSudoku = new boolean[10][10][10];
		while(!DoesEqual(StartingSudoku)){
			StartingSudoku = CopySudoku();
			mySudoku.CheckSolved();
			mySudoku.EliminatePossible();
		}
	}
	public void RepeatEliminationSinglesRC(){
		boolean[][][] StartingSudoku = new boolean[10][10][10];
		while(!DoesEqual(StartingSudoku)){
			StartingSudoku = CopySudoku();
			mySudoku.CheckForSinglesRC();
			RepeatElimination();
		}
	}
	public void RepeatEliminationSinglesBox(){
		boolean[][][] StartingSudoku = new boolean[10][10][10];
		while(!DoesEqual(StartingSudoku)){
			StartingSudoku = CopySudoku();
			mySudoku.CheckSinglesBox();
			RepeatElimination();
		}
	}
	public void RepeatEliminationAndChecking(){
		boolean[][][] StartingSudoku = new boolean[10][10][10];
		while(!DoesEqual(StartingSudoku)){
			StartingSudoku = CopySudoku();
			RepeatElimination();
			RepeatEliminationSinglesRC();
			RepeatEliminationSinglesBox();
		}
	}
	private boolean DoesEqual(boolean[][][] Array){
		boolean[][][] dummy = mySudoku.getPossible();
		for(int j = 1; j <= 9; j++){
			for(int k = 1; k <= 9; k++){
				for(int m = 1; m <= 9; m++){
					if(dummy[j][k][m] != Array[j][k][m])
						return false;
				}
			}
		}
		return true;
	}
	private boolean[][][] CopySudoku(){
		boolean[][][] dummy = mySudoku.getPossible();
		boolean[][][] newArray = new boolean[10][10][10];
		for(int j = 1; j <= 9; j++){
			for(int k = 1; k <= 9; k++){
				for(int m = 1; m <= 9; m++)
					newArray[j][k][m] = dummy[j][k][m];
			}
		}
		return newArray;
	}
}
