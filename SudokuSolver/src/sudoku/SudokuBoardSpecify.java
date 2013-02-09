package sudoku;

import java.applet.Applet;
import java.awt.*;
import javax.swing.JOptionPane;

public class SudokuBoardSpecify extends Applet{
	private static final long serialVersionUID = 1L;
	int width, height, SquareSpaceX, SquareSpaceY, IndentXY, startX, startY, charSpaceY;
	private int[][] theSudoku = new int[10][10];
	Button d;
	public int[][] getSudoku(){
		return theSudoku;
	}
	public void init(){
		width = 1000;
		height = 2000;
		charSpaceY = 15;
		setForSmall();
		setBackground(Color.white);
		d = new Button("Push when done specifying sudoku");  
		add(d); 
	}
	public void setForSmall(){
		SquareSpaceX = 15;
		SquareSpaceY = 20;
		IndentXY = 5;
		startX = 10;
		startY = 40;
	}
	public boolean action(Event ev, Object args){
		if(ev.target == d){
			stop(); destroy();
		}
		return true;
	}
	private String message = "start";
	private String message2 = "now";
	public void paint(Graphics g){
		g.setColor(Color.black);
		DrawSudokuLines(g);
		g.drawString(message, 175, 50);
		g.drawString(message2, 175, 65);
		DisplaySolved(g);
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
	public boolean mouseMove( Event evt, int x, int y)
	{
		message = "mouseMove";
		int rplace = (y-startY)/SquareSpaceY + 1;
		int cplace = (x-startX)/SquareSpaceX + 1;
		message2 = "row:" + rplace + " column:" + cplace;
		repaint();
		return true;
	}

	public boolean mouseDown( Event evt, int x, int y)
	{
		message = "mouseDown";
		int rplace = (y-startY)/SquareSpaceY + 1;
		int cplace = (x-startX)/SquareSpaceX + 1;
		message2 = "row:" + rplace + " column:" + cplace;
		SpecifyNum(rplace,cplace);
		repaint();
		return true;
	}
	public void SpecifyNum(int row, int column){
		while(true){
			String spec = JOptionPane.showInputDialog(
					"Specify Number in row " + row + " and column " + column);
			if(spec.length() == 1){
				int num = spec.charAt(0) - '0';
				theSudoku[row][column] = num; return;
			}
			else{
				JOptionPane.showMessageDialog(null, "Invalid Entry. Re-try");
			}
		}
	}
	public void DisplaySolved(Graphics g){
		DrawSudokuLines(g);
		for(int j = 1; j <= 9; j++){
			for(int k = 1; k <= 9; k++){
				if(theSudoku[j][k] != 0){
					String nums = Integer.toString(theSudoku[j][k]);
					DrawTextInBox(j,k,g,nums);
				}
			}
		}
	}
	public void DrawTextInBox(int row, int column, Graphics g, String nums){
		int xstart = startX + SquareSpaceX*(column-1) + IndentXY; 
		int ystart = startY + SquareSpaceY*(row-1);
		g.drawString(nums, xstart, ystart+charSpaceY);
	}
}
