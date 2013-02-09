package sudoku;
import java.util.*;

public class SudokuNewInput {
	private boolean[][][] Possible; 
	private int[][] theSolved; 
	private int[][] NumPossible; 
	private boolean[][] NewlySolved;
	private boolean[][] UseableSquare = new boolean[10][10];
	public SudokuNewInput(boolean[][][] Possible, int[][] theSolved, int[][] NumPossible, boolean[][] NewlySolved){
		this.Possible = Possible; this.theSolved = theSolved; 
		this.NumPossible = NumPossible; this.NewlySolved = NewlySolved;
		NewUseableSquare();
	}
	public SudokuNewInput(Sudoku aSudoku){
		this(aSudoku.getPossible(), aSudoku.getSolved(), aSudoku.getNumPossible(), aSudoku.getNewlySolved());
	}
	public Sudoku getSudoku(){
		return new Sudoku(Possible, theSolved, NumPossible, NewlySolved);
	}
	public boolean[][][] getPossible(){
		return Possible;
	}
	public boolean[][] getNewlySolved(){
		return NewlySolved;
	}
	public int[][] getNumPossible(){
		return NumPossible;
	}
	public int[][] getTheSolved(){
		return theSolved;
	}
	private void NewUseableSquare(){
		for(int j = 1; j <= 9; j++){
			for(int k = 1; k <= 9; k++){
				if(theSolved[j][k] != 0){
					UseableSquare[j][k] = false;
				}
				if(NumPossible[j][k] > 1){
					UseableSquare[j][k] = true;
				}
			}
		}
	}
	public boolean ValidInput(int input, int row, int column){
		SudokuTestInput InputTester = new SudokuTestInput(Possible, theSolved, NumPossible, NewlySolved);
		return InputTester.TestInput(row, column, input);
	}
	public boolean TestValidity(int row, int column, int num){
		int[] ii = PossibleInputs(row,column); 
		if(Arrays.binarySearch(ii, num) < 0){
			return false;
		}
		
		if(!TestSquare(row,column,ii)){
			return false;
		}
		else{
			if(InputUsed == num){
				return true;
			}
		}
		return false;
	}
	public void TestAnInput(){
		boolean used = false;
		while(!used){
			int i = MinNumInputs();
			if(i < 10){
				int[] point = PossibleSquare(i);
				int r = point[0]; int c = point[1];
				int[] ii = PossibleInputs(r, c, i);
				used = TestSquare(r,c,ii);
				if(used){
					NewInput(r,c,InputUsed);
				}
			}
			if(NoSquaresLeft()){
				break;
			}
		}		
	}
	private boolean NoSquaresLeft(){
		for(int j = 1; j <= 9; j++){
			for(int k = 1; k <= 9; k++){
				if(UseableSquare[j][k]){
					return false;
				}
			}
		}
		return true;
	}
	private int MinNumInputs(){
		for(int j = 2; j <= 9; j++){
			int[] coordinate = PossibleSquare(j);
			int r = coordinate[0]; int c = coordinate[1];
			if(r != 0 && c != 0){
				return j;
			}
		}
		return 10;
	}
	public void NewInput(int row, int column, int input){
		DeleteFromPossible(input, row, column);
		theSolved[row][column] = input;
		NewlySolved[row][column] = true;
		NumPossible[row][column] = 1;
	}
	static int InputUsed;
	private boolean TestSquare(int row, int column, int[] inputs){
		int numValid = 0; InputUsed = 1;
		boolean squareUsed = false;
		for(int j = 0; j < inputs.length; j++){
			if(!Possible[row][column][inputs[j]]){
				System.out.println("invalid program for testing square");
			}
			if(ValidInput(inputs[j],row,column)){
				numValid++; InputUsed = inputs[j];
			}
		}
		if(numValid == 1){
			squareUsed = true;
		}
		UseableSquare[row][column] = false;
		return squareUsed;
	}
	private void DeleteFromPossible(int num, int row, int column){
		for(int j = 1; j <= 9; j++){
			if(j != num)
				Possible[row][column][j] = false;
		}
	}
	private int[] PossibleSquare(int numInputs){
		int[] coord = new int[2]; coord[0] = 0; coord[1] = 0;
		for(int j = 1; j <= 9; j++){
			for(int k =1 ; k <= 9; k++){
				if(NumPossible[j][k] == numInputs && UseableSquare[j][k]){
					coord[0] = j; 
					coord[1] = k;
					return coord;
				}
			}
		}
		return coord;
	}
	private int[] PossibleInputs(int r, int c){
		int[] inputs = new int[9]; int counter = 0;
		for(int j = 1; j<= 9; j++){
			if(Possible[r][c][j]){
				inputs[counter] = j; counter++;
			}
		}
		int[] val = new int[counter];
		for(int j = 0; j < counter; j++){
			val[j] = inputs[j];
		}
		return val;
	}
	private int[] PossibleInputs(int r, int c, int numInputs){
		int[] inputs = new int[numInputs]; int counter = 0;
		for(int j = 1; j<= 9; j++){
			if(Possible[r][c][j]){
				inputs[counter] = j; counter++;
			}
		}
		return inputs;
	}
}
