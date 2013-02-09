package sudoku;

public class SudokuSolveSinglesBox {
	private boolean[][][] Possible; 
	private int[][] theSolved; 
	private int[][] NumPossible; 
	private boolean[][] NewlySolved;
	public SudokuSolveSinglesBox(boolean[][][] Possible, int[][] theSolved, int[][] NumPossible, boolean[][] NewlySolved){
		this.Possible = Possible; this.theSolved = theSolved; 
		this.NumPossible = NumPossible; this.NewlySolved = NewlySolved;
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
	public void CheckSinglesBox(){
		for(int j = 1; j <= 9; j++){
			int[] NumsToCheck = UnSolvedInBox(j);
			int i = 0; int NumSpots = 0;
			while(NumsToCheck[i] != 0){
				int bplace = 0;
				int NumToCheck = NumsToCheck[i];
				for(int k = 1; k <= 9; k++){ 
					int rplace = getRPoint(j,k); 
					int cplace = getCPoint(j,k);
					if(Possible[rplace][cplace][NumToCheck]){
						NumSpots++; bplace = k;
						if(NumSpots >= 2)
							break;
					}
				}
				if(NumSpots == 1){
					int rplaceN = getRPoint(j,bplace); 
					int cplaceN = getCPoint(j,bplace);
					DeleteFromPossible(NumToCheck,rplaceN,cplaceN);
					NewlySolved[rplaceN][cplaceN] = true;
					theSolved[rplaceN][cplaceN] = NumToCheck;
				}
				i++; NumSpots = 0;
			}
			i = 0;
		}
	}
	private void DeleteFromPossible(int num, int row, int column){
		for(int j = 1; j <= 9; j++){
			if(j != num)
				Possible[row][column][j] = false;
				NumPossible[row][column] = 1;
		}
	}
	private int getRPoint(int box, int boxL){
		int rstart = ((box-1)/3)*3 + 1;
		int rstep = ((boxL-1)/3);
		return (rstart + rstep);
	}
	private int getCPoint(int box, int boxL){
		int cstart = ((box-1)%3)*3 + 1;
		int cstep = ((boxL-1)%3);
		return (cstart + cstep);
	}
	private int[] UnSolvedInBox(int box){
		boolean[] Solved = new boolean[10]; int counter = 0;
		int[] Unsolved = new int[10];
		int rstart = ((box-1)/3)*3 + 1;
		int cstart = ((box-1)%3)*3 + 1;
		for(int j = rstart; j <= rstart + 2; j++){
			for(int k = cstart; k <= cstart + 2; k++){
				int numToUse = theSolved[j][k];
				if(numToUse != 0){
					Solved[numToUse] = true;
				}
			}
		} 
		for(int j = 1; j <= 9; j++){
			if(!Solved[j]){
				Unsolved[counter] = j;
				counter++;
			}
		}
		return Unsolved;
	}
}
