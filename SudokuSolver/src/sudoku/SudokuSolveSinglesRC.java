package sudoku;
/*This class creates the instance of the sudoku and finds the single values in the rows and columns
 * It constructs the sudoku from the arrays given to it
 * Then, it checks the rows, then column for single values
 * Once found, the single values are made the row entry. 
 */
public class SudokuSolveSinglesRC {
	private boolean[][][] Possible;//array of possibilities for each square 
	private int[][] theSolved;//the solved sudoku. if a square is unsolved, it has a "0" value
	private int[][] NumPossible;//the number of possibilities for each square
	private boolean[][] NewlySolved;//tells whether each square is newly solved. 
	/*The constructor takes the arrays and copies them into this classes arrays
	 */
	public SudokuSolveSinglesRC(boolean[][][] Possible, int[][] theSolved, int[][] NumPossible, boolean[][] NewlySolved){
		this.Possible = Possible; this.theSolved = theSolved; 
		this.NumPossible = NumPossible; this.NewlySolved = NewlySolved;
	}
	
	//the getter methods for the class
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
	
	//When a square has been solved, the other values are eliminated from being possiblites.
	private void DeleteFromPossible(int num, int row, int column){
		for(int j = 1; j <= 9; j++){//goes through the square
			if(j != num)
				Possible[row][column][j] = false;//makes other numbers not possible
				NumPossible[row][column] = 1;
		}
	}
	public void CheckforSinglesRC(){//checks for the single values
		CheckSinglesRow();//in each row
		CheckSinglesColumn();//in each column
	}
	
	/*The following method finds out the numbers in a certain row that have not been solved yet.
	 * It then goes through them to see which unsolved ones appear in only
	 * 		one square in the row. 
	 * If it appears in only one square, then that's the value of the square and the
	 * 		other possibilities are eliminated. 
	 */
	private void CheckSinglesRow(){
		int i = 0;//initializes i, which is used to goes through the array of numbers to check
		for(int j = 1; j <= 9; j++){//goes through each row
			int[] NumsToCheck = UnSolvedInRow(j);//finds unsolved numbers
			int NumSpots = 0;//initializes NumSpots, which tells how many squares and unsolved value appears in
			while(NumsToCheck[i] != 0){//goes through the array of unsolved numbers
				int place = 0;//initializes place, which is used to keep where the single value is
				int NumToCheck = NumsToCheck[i];//used for the value of the single
				for(int k = 1; k <= 9; k++){//transverses the row
					if(Possible[j][k][NumToCheck]){
						NumSpots++;//increments number of places where value appears
						place = k;//keeps the spot where the single is
						
						/*If the number of spots where the value appears is greater than 1
						 * 		than the value won't be used by the method.
						 * 	The loop is therefore broken out of to save operations.
						 */	
						if(NumSpots >= 2)
							break;
					}
				}
				if(NumSpots == 1){//if the value appears in only one spot
					DeleteFromPossible(NumToCheck,j,place);
					NewlySolved[j][place] = true;//tells that the spot has been newly solved
					theSolved[j][place] = NumToCheck;//puts it into the solved sudoku array
				}
				i++;//increments array index so that the array of unsolved numbers can be transversed 
				NumSpots = 0;//sets NumSpots back to zero so the next number can be tested.
			}
			i = 0;//sets i back to zero so the next row can be tested
		}
		
	}
	/*The following method finds the unsolved numbers in the specified row
	 * It makes an array that tells whether each number has been solved or not
	 * Then, an int array is made that tells which values have not been solved.
	 */
	private int[] UnSolvedInRow(int row){
		
		/*initializes array that tells whether each value was solved or not.
		 * The index of the array is the value and the entry is whether it is solved or not
		 */
		boolean[] Solved = new boolean[10]; 
		
		int counter = 0;//initializes the counter, which is used to add numbers to the array of unsolved numbers
		int[] Unsolved = new int[10];//array that tells the unsolved numbers
		for(int j = 1; j <= 9; j++){//transverses the row
			int numToUse = theSolved[row][j];//finds the entry in the row
			if(numToUse != 0){//if it is solved
				Solved[numToUse] = true;//entry in the array is set to true
			}
		} 
		for(int j = 1; j <= 9; j++){//transverses possible values in the row
			if(!Solved[j]){//if it is not in the array of solved values
				Unsolved[counter] = j;//adds the unsolved value to the array of unsolved values
				counter++;//increments the counter
			}
		}
		return Unsolved;
	}
	
	/*The following method finds out the numbers in a certain row that have not been solved yet.
	 * It then goes through them to see which unsolved ones appear in only
	 * 		one square in the row. 
	 * If it appears in only one square, then that's the value of the square and the
	 * 		other possibilities are eliminated. 
	 */
	private void CheckSinglesColumn(){
		int i = 0; //initializes i, which is used to goes through the array of numbers to check
		for(int j = 1; j <= 9; j++){//goes through each column
			int[] NumsToCheck = UnSolvedInColumn(j);//gets the unsolved numbers in the column
			int NumSpots = 0; //initializes NumSpots, which tells how many squares and unsolved value appears in
			while(NumsToCheck[i] != 0){ //goes through the array of unsolved numbers
				int place = 0; //initializes place, which is used to keep where the single value is
				int NumToCheck = NumsToCheck[i]; //used for the value of the single
				for(int k = 1; k <= 9; k++){ //transverses the column
					if(Possible[k][j][NumToCheck]){
						NumSpots++; //increments number of places where value appears 
						place = k; //keeps the spot where the single is
						
						/*If the number of spots where the value appears is greater than 1
						 * 		than the value won't be used by the method.
						 * 	The loop is therefore broken out of to save operations.
						 */	
						if(NumSpots >= 2)
							break;
					}
				}
				if(NumSpots == 1){ //if the value appears in only one spot
					DeleteFromPossible(NumToCheck,place,j);
					NewlySolved[place][j] = true; //tells that the spot has been newly solved
					theSolved[place][j] = NumToCheck; //puts it into the solved sudoku array
				}
				i++; //increments array index so that the array of unsolved numbers can be transversed
				NumSpots = 0; //sets NumSpots back to zero so the next number can be tested.
			}
			i = 0; //sets i back to zero so the next row can be tested
		}
		
	}
	private int[] UnSolvedInColumn(int column){
		boolean[] Solved = new boolean[10]; int counter = 0;
		int[] Unsolved = new int[10];
		for(int j = 1; j <= 9; j++){ //transverses the column
			int numToUse = theSolved[j][column];
			if(numToUse != 0){
				Solved[numToUse] = true;
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
