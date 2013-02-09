package sudoku;

public class SudokuDisplay {
	public static char HLine = '-';
	public static char VLine = '|';
	public static void DisplayHLine(){//for larger possiblities sudoku
		for(int j = 0; j < 41; j++)
			System.out.print(HLine);
		System.out.println();
	}
	public static void DisplayHLineS(){//for solved sudoku
		for(int j = 0; j < 13; j++)
			System.out.print(HLine);
	}
	public static void DisplayRow(boolean[][][] sud, int row){
		for(int i = 0; i < 3; i++){
			DisplayLine(sud,row,i);
			System.out.println();
		}
	}
	public static void DisplayLine(boolean[][][] sud, int row, int line){
		System.out.print(VLine); System.out.print(VLine);
		for(int j = 1; j <= 9; j++){
			for(int k = 1; k <= 3; k++){
				int num = line*3 + k;
				DisplayNum(sud[row][j][num], num);
			}
			System.out.print(VLine);
			if(j%3 == 0)
				System.out.print(VLine);
		}
	}
	public static void DisplayRows(boolean[][][] sud){
		DisplayHLine(); DisplayHLine();
		for(int j = 1; j <= 9; j++){
			DisplayRow(sud,j);
			DisplayHLine();
			if(j%3 == 0)
				DisplayHLine();
		}
	}
	public static void DisplayNRow(int[] row){
		System.out.print(VLine);
		for(int j = 1; j <= 9; j++){
			System.out.print(row[j]);
			if(j%3 == 0)
				System.out.print(VLine);
		}
	}
	public static void DisplayNRows(int[][] rows){
		DisplayHLineS();
		System.out.println();
		for(int j = 1; j <= 9; j++){
			DisplayNRow(rows[j]);
			System.out.println();
			if(j%3 == 0){
				DisplayHLineS();
				System.out.println();
			}
				
		}
	}
	public static void DisplayNum(boolean display, int num){
		if(display)
			System.out.print(num);
		else
			System.out.print(" ");
		return;
	}
}
