package sudoku;

import java.applet.*;
import java.awt.*;
import java.util.StringTokenizer;

import javax.swing.*;


public class SudokuApplet extends Applet{
	private static final long serialVersionUID = 1L;
	int width, height;
	int SquareSpaceX, SquareSpaceY, IndentXY;
	int startX, startY, charSpaceY;
	int[][] theSudoku;
	Sudoku mySudoku;
	SudokuSolving solve;
	Button e,c,b,s,r,t,g,p,m;
	boolean[][][] Sudoku;
	public void init(){
		width = 1000;
		height = 2000;
		charSpaceY = 15;
		setBackground(Color.white);
		DecideOnSudoku();
		ShowButtons();  add(e); add(c); add(r); add(s); add(b);
		add(g); add(p); add(t); add(m);
		mySudoku = new Sudoku(theSudoku);
		solve = new SudokuSolving(mySudoku);
	}
	public void DecideOnSudoku(){
		String[] dd = {"Manual Input line-by-line (Press Yes)", "Manual Input onto board (Press No)",
				"Read First 9 lines of text file (Press Cancel)"};
		int d = JOptionPane.showConfirmDialog(null, dd, "How to specify sudoku?",1);
		switch(d){
		case 0: theSudoku = SudokuInput.InputRows(true); break;
		case 1: theSudoku = new int[10][10]; break;
		case 2: String s = JOptionPane.showInputDialog("What File do you want to use?");
		theSudoku = SudokuInput.InputRowsFile(s); break;
		}
	}
	public void setForLarge(){
		SquareSpaceX = 35;
		SquareSpaceY = 50;
		IndentXY = 5;
		startX = 200;
		startY = 100; 
	}
	public void setForSmall(){
		SquareSpaceX = 15;
		SquareSpaceY = 20;
		IndentXY = 5;
		startX = 10;
		startY = 100;
	}
	public boolean mouseDown( Event evt, int x, int y)
	{
		int rplace = (y-startY)/SquareSpaceY + 1;
		int cplace = (x-startX)/SquareSpaceX + 1;
		if(rplace >= 1 && rplace <= 9 && cplace >= 1 && cplace <= 9){
			SpecifyNum(rplace,cplace);
		}
		repaint();
		return true;
	}
	public void SpecifyNum(int row, int column){
		while(true){
			String spec = JOptionPane.showInputDialog(
					"Specify Number in row " + row + " and column " + column);
			if(spec.length() == 1){
				int num = spec.charAt(0) - '0';
				theSudoku[row][column] = num; 
				mySudoku = new Sudoku(theSudoku);
				solve = new SudokuSolving(mySudoku);
				return;
			}
			else{
				JOptionPane.showMessageDialog(null, "Invalid Entry. Re-try");
			}
		}
	}
	public void ShowButtons(){
		e = new Button("Eliminate possibilities:(1)");
		c = new Button("Check for solved values:(2)");
		r = new Button("Do (1) and (2) repeatedly:(5)");
		s = new Button("Check for single values in the rows/columns:(3)");
		b = new Button("Check for single values in the boxes:(4)");
		g = new Button("(5), (3), and (4) repeatedly: (7)");
		p = new Button("Find and Use First Valid Input (6)");
		t = new Button("Specify Input Yourself.");
		m = new Button("Do (6) and (7) repeatedly");
	}
	public boolean action(Event ev, Object args){
		if(ev.target == r){
			solve.RepeatElimination(); mySudoku = solve.getSudoku(); repaint();
		}
		if(ev.target == e){
			mySudoku.EliminatePossible(); repaint();
		}
		if(ev.target == c){
			mySudoku.CheckSolved(); repaint();
		}
		if(ev.target == s){
			mySudoku.CheckForSinglesRC(); repaint();
		}
		if(ev.target == b){
			mySudoku.CheckSinglesBox(); repaint();
		}
		if(ev.target == g){
			solve.RepeatEliminationAndChecking(); mySudoku = solve.getSudoku(); repaint();
		}
		if(ev.target == p){
			mySudoku.TestInput(); repaint();
		}
		if(ev.target == t){
			TryInput(); repaint();
		}
		if(ev.target == m){
			solve.RepeatUntilSolved(); mySudoku = solve.getSudoku(); repaint();
		}
		return true;
	}
	public void paint(Graphics g){
		g.setColor(Color.black);
		setForLarge();
		DisplayPossible(g);
		setForSmall();
		DisplaySolved(g);
		if(isSolved()){
			JOptionPane.showMessageDialog(null, "It has been solved!!! All done.", "Congratulations", 1);
			stop();
		}
	}
	public void TryInput(){
		boolean used = false; int row = 0; int column = 0; int num = 0;
		while(!used){
			String i = JOptionPane.showInputDialog("In Row,Column,Number format, specify the input and location");
			StringTokenizer tokens = new StringTokenizer(i,",");
			used = true;
			if(tokens.countTokens() != 3){
				JOptionPane.showMessageDialog(null, "Incorrect Format. Not in row,col,num format."); used = false;
			}
			else{
				try {
					row = Integer.parseInt(tokens.nextToken().trim());
					column = Integer.parseInt(tokens.nextToken().trim());
					num = Integer.parseInt(tokens.nextToken().trim());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,"Did not specify a number at least once"); used = false;
				}
			}
		}
		int[][] solved = mySudoku.getSolved();
		if(solved[row][column] != 0){
			JOptionPane.showMessageDialog(null, "Square has already been solved");
		}
		else{
			SudokuNewInput newI = new SudokuNewInput(mySudoku);
			boolean valid = newI.TestValidity(row, column, num);
			if(valid){
				JOptionPane.showMessageDialog(null, "It was a valid input");
				newI.NewInput(row, column, num);
				mySudoku = newI.getSudoku(); repaint();
			}
			else{
				JOptionPane.showMessageDialog(null, "Invalid Input. Try again.");
			}
		}
	}
	public void DisplayPossible(Graphics g){
		Sudoku = mySudoku.getPossible();
		DrawSudokuLines(g);
		DrawBoxes(g);
	}
	public void DisplaySolved(Graphics g){
		DrawSudokuLines(g);
		int[][] solved = mySudoku.getSolved();
		for(int j = 1; j <= 9; j++){
			for(int k = 1; k <= 9; k++){
				String[] nums = new String[1];
				if(solved[j][k] != 0){
					nums[0] = Integer.toString(solved[j][k]);
					DrawTextInBox(j,k,g,nums);
				}
			}
		}
	}
	public void DrawBoxes(Graphics g){
		for(int j = 1; j <= 9; j++){
			for(int k = 1; k <= 9; k++){
				DrawTextInBox(j,k,g, MakeString(j,k));
			}
		}
	}
	private String[] MakeString(int row, int column){
		String[] display = new String[3];
		for(int j = 0; j < 3; j++){
			StringBuffer disp = new StringBuffer("");
			for(int k = 1; k <= 3; k++){
				int num = 3*j + k;
				if(Sudoku[row][column][num]){
					disp.append(num);
				}
				else{
					disp.append(" ");
				}
				disp.append(" ");
			}
			display[j] = disp.toString();
		}
		return display;
	}
	public void DrawTextInBox(int row, int column, Graphics g, String[] nums){
		int xstart = startX + SquareSpaceX*(column-1) + IndentXY; int ystart = startY + SquareSpaceY*(row-1);
		g.drawString(nums[0], xstart, ystart+charSpaceY);
		if(nums.length > 1){
			g.drawString(nums[1], xstart, ystart+charSpaceY*2);
			g.drawString(nums[2], xstart, ystart+charSpaceY*3);
		}
	}
	public void DrawSudokuLines(Graphics g){
		for(int j = 0; j < 10; j++){//horizontal lines
			g.drawLine(startX, startY + SquareSpaceY*j, startX + SquareSpaceX*9, startY + SquareSpaceY*j);
			if(j%3 == 0){
				g.drawLine(startX, startY + SquareSpaceY*j + 1, startX + SquareSpaceX*9, startY + SquareSpaceY*j + 1);
			}
		}
		for(int j = 0; j < 10; j++){//vertical lines
			g.drawLine(startX + SquareSpaceX*j, startY, startX + SquareSpaceX*j, startY + SquareSpaceY*9);
			if(j%3 == 0){
				g.drawLine(startX + SquareSpaceX*j + 1, startY, startX + SquareSpaceX*j + 1, startY + SquareSpaceY*9);
			}
		}
	}
	private boolean isSolved(){
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
}
