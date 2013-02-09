package tests;

public class CopyTest {
	public static void main(String[] args) {
		boolean[][][] newB = Generate();
		SudokuDisplay.DisplayRows(newB);
		boolean[][][] previous = CopySudoku(newB);
		newB = Generate();
		SudokuDisplay.DisplayRows(previous);
		System.out.println();
		SudokuDisplay.DisplayRows(newB);
	}
	private static boolean[][][] Generate(){
		boolean[][][] newB = new boolean[10][10][10];
		for(int j = 1; j < 10; j++){
			for(int k = 1; k < 10; k++){
				for(int m = 1; m < 10; m++){
					newB[j][k][m] = getRandom();
				}
			}
		}
		return newB;
	}
	private static boolean getRandom(){
		int i = (int)(2*Math.random());
		if(i == 0)
			return false;
		else
			return true;
	}
	private static boolean[][][] CopySudoku(boolean[][][] dummy){
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
