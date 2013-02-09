
package sudoku;

import java.util.Scanner;
import javax.swing.JOptionPane;
public class SudokuSolver {

	//static int[][] theSudoku = SudokuInput.InputRows();
	static int[][] theSudoku;
	static Sudoku mySudoku;
	public static Sudoku getMySudoku() {
		return mySudoku;
	}
	public static void setMySudoku(Sudoku mySudoku) {
		SudokuSolver.mySudoku = mySudoku;
	}
	public static void main(String[] args) {
		DecideOnSudoku();
		mySudoku = new Sudoku(theSudoku);
		System.out.println("Initial Sudoku Possibilities");
		DisplayTheSudoku();
		InputCommand();
		if(!isSolved()){
			ShowLines("What has been solved is...");
		}
		else{
			ShowLines("The Sudoku has been solved!! Here it is...");
		}
		SudokuDisplay.DisplayNRows(mySudoku.getSolved());
		
	}
	public static void DecideOnSudoku(){
		String[] dd = {"Manual Input (Press OK)","Read First 9 lines of text file (Press Cancel)"};
		int d = JOptionPane.showConfirmDialog(null, dd, "How to specify sudoku?",2);
		switch(d){
		case 0: theSudoku = SudokuInput.InputRows(false); break;
		case 2: UseFile(); break;
		}
	}
	private static void UseFile(){
		System.out.println("What File do you want to use?");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		theSudoku = SudokuInput.InputRowsFile(s);
	}
	private static void InputCommand(){
		Scanner sc = new Scanner(System.in);
		String i = " "; char cmd = ' ';
		boolean valid = false; 
		while(cmd != 'x' && !isSolved()){
			DisplayCommandOptions();
			i = sc.nextLine();
			cmd = i.charAt(0);
			if(i.length() > 1){
				valid = SpecialInput(i);
			}
			else{
				CommandExecute(cmd);
				valid = true;
			}
			
			if(!valid){
				System.out.println("Invalid Input. Re-enter Input");
			}
			else{
				ShowLines("you now have...");
				DisplayTheSudoku();
			}
		}
	}
	public static void CommandExecute(char cmd){
		switch(cmd){
		case 'v': mySudoku.EliminatePossible();	mySudoku.CheckSolved(); break;
		case 'e': mySudoku.EliminatePossible(); break;
		case 'c': mySudoku.CheckSolved(); break;
		case 's': mySudoku.CheckForSinglesRC(); break;
		case 'b': mySudoku.CheckSinglesBox(); break;
		case 'q': CommandExecute('v'); CommandExecute('g'); break;
		case 'g': GoForBroke(); break;
		case 'i': InitialSolving(); break;
		case 'o': CompletelySolve(); break;
		case 'a': CommandExecute('q'); CommandExecute('o'); break;
		case 'p': mySudoku.TestInput(); break; 
		}
	}
	private static void CompletelySolve(){
		while(!isSolved()){
			InitialSolving();
			RepeatEliminationAndChecking();
			CommandExecute('p');
		}
	}
	private static void RepeatCommand(char cmd){
		boolean[][][] StartingSudoku = new boolean[10][10][10];
		while(!DoesEqual(StartingSudoku)){
			StartingSudoku = CopySudoku();
			CommandExecute(cmd);
		}
	}
	private static boolean SpecialInput(String i){
		boolean valid = false;
		if(i.charAt(1) == 'l' && i.length() == 2){
			valid = true;
			RepeatCommand(i.charAt(0));
		}
		else{
			if(isNum(i)){
				valid = true;
				int numA = Integer.parseInt(i.substring(1, i.length()));
				for(int j = 0; j < numA; j++){
					CommandExecute(i.charAt(0));
				}
			}
		}
		return valid;
	}
	private static boolean isNum(String i){
		for(int j = 1; j < i.length(); j++){
			if(i.charAt(j) < '0' || i.charAt(j) > '9'){
				return false;
			}
		}
		return true;
	}
	private static void DisplayCommandOptions(){
		System.out.println("i = do initial solving and elimination");
		System.out.println("v = eliminate values and check for solved ones");
		System.out.println("e = eliminate possibilities");
		System.out.println("c = check for solved values");
		System.out.println("s = check for single values in the rows/columns");
		System.out.println("b = check for single values in the box");
		System.out.println("g = check singles and eliminate as much as possible");
		System.out.println("q = go through initial sequence of solving, checking singles, eliminating");
		System.out.println("p = test an input");
		System.out.println("o = solve as completely as possible.");
		System.out.println("a = do all steps to solve it");
		System.out.println("x = exit and display what has been solved");
		System.out.println("add number at end to specify number of time to execute it");
		System.out.println("add l to make it loop until it no longer is effective");
	}
	private static void InitialSolving(){
		mySudoku.EliminatePossible();
		ShowLines("The New Possibilites");
		DisplayTheSudoku();
		mySudoku.CheckSolved();
		mySudoku.EliminatePossible();
		ShowLines("After the already solved squares are taken into account");
		DisplayTheSudoku();
		ShowLines("After the checking is done repeatedly");
		RepeatElimination();
		DisplayTheSudoku();
	}
	private static void GoForBroke(){
		if(!isSolved()){
			mySudoku.CheckForSinglesRC();
			ShowLines("After the singles in the rows/columns are solved");
			DisplayTheSudoku();
			ShowLines("This sequence is done repeatedly");
			RepeatEliminationSinglesRC();
			DisplayTheSudoku();
		}
		if(!isSolved()){
			ShowLines("After the singles in the boxes are solved");
			mySudoku.CheckSinglesBox();
			DisplayTheSudoku();
			ShowLines("This sequence is done repeatedly");
			RepeatEliminationSinglesBox();
			DisplayTheSudoku();
		}
		if(!isSolved()){
			ShowLines("After all of this is done repeatedly");
			RepeatEliminationAndChecking();
			DisplayTheSudoku();
		}
	}
	private static boolean isSolved(){
		int[][] SolvedNums = mySudoku.getSolved();
		for(int j = 1; j <= 9; j++){
			for(int k =1 ; k <= 9; k++){
				if(SolvedNums[j][k] == 0){
					return false;
				}
			}
		}
		return true;
	}
	private static void RepeatElimination(){
		SudokuSolving newSolve = new SudokuSolving(mySudoku);
		newSolve.RepeatElimination();
		mySudoku = newSolve.getSudoku();
	}
	private static void RepeatEliminationSinglesRC(){
		SudokuSolving newSolve = new SudokuSolving(mySudoku);
		newSolve.RepeatEliminationSinglesRC();
		mySudoku = newSolve.getSudoku();
	}
	private static void RepeatEliminationSinglesBox(){
		SudokuSolving newSolve = new SudokuSolving(mySudoku);
		newSolve.RepeatEliminationSinglesBox();
		mySudoku = newSolve.getSudoku();
	}
	private static void RepeatEliminationAndChecking(){
		SudokuSolving newSolve = new SudokuSolving(mySudoku);
		newSolve.RepeatEliminationAndChecking();
		mySudoku = newSolve.getSudoku();
	}
	private static boolean DoesEqual(boolean[][][] Array){
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
	private static void DisplayTheSudoku(){
		SudokuDisplay.DisplayRows(mySudoku.getPossible());
	}
	private static void ShowLines(String message){
		System.out.println();
		System.out.println(message);
	}
	private static boolean[][][] CopySudoku(){
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
