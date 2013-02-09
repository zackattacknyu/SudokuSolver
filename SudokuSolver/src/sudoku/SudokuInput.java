package sudoku;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class SudokuInput {
	public static int[] ComputeRow(String s){
		int[] num = new int[10]; num[0]=0;
		for(int k = 1; k <= 9; k++){
			if(s.charAt(k-1) >= '1' && s.charAt(k-1) <= '9')
				num[k] = (int)(s.charAt(k-1) - '0');
			else
				num[k] = 0;
		}
		return num;
	}
	public static int[][] InputRows(boolean app){
		Scanner sc = new Scanner(System.in);
		int[][] nums = new int[10][10];
		boolean Shown = false; boolean AutoContinue = true; boolean AutoReEnter = false;
		String[] message = {"Specify the Sudoku line by line","Specify number " +
				"in each space.","Use 0 or a non-number character for blanks."};
		if(app){
			JOptionPane.showMessageDialog(null,message);
		}
		else{
			System.out.println(message[0]);
			System.out.println(message[1]);
			System.out.println(message[2]);
		}
		
		for(int j = 1; j <= 9; j++){
			String newS = "";
			while (newS.length() < 9){
				String message1 = "Enter Line " + j + ":";
				
				if(app){
					newS = JOptionPane.showInputDialog(message1);
				}
				else{
					System.out.print(message1);
					newS = sc.nextLine();
				}
				
				if(newS.length() < 9){
					String message2 = "Invalid. Enter line again.";
					if(app){
						JOptionPane.showMessageDialog(null,message2);
					}
					else{
						System.out.println(message2);
						Pause();
					}
				}
				if(newS.length() > 9 && AutoContinue){
					if(!AutoReEnter){
						String[] message3 = {"Inputted more than 9 digits. ",
								"Only first nine will be read. Re-enter Line(Y/N)?"};
						if(app){
							JOptionPane.showMessageDialog(null,message3);
						}
						else{
							System.out.println(message3[0]);
							System.out.println(message3[1]);
						}
					}
					else{
						String message4 = "Inputted more than 9 digits. Re-enter Line.";
						if(app){
							JOptionPane.showMessageDialog(null, message4);
						}
						else{
							System.out.println(); Pause();
						}
					}
					if(!Shown){
						String[] message5 = {"Always Continue (Type A); Always Re-enter(Type R)",
								"What's your decision(Y, N, A, or R)?"};
						
						if(app){
							JOptionPane.showMessageDialog(null, message5);
						}
						else{
							System.out.println(message5[0]);
							System.out.println(message5[1]);
						}
						
						Shown = true;
					}	
					String decision = "";
					if(!AutoReEnter){
						if(app){
							decision = JOptionPane.showInputDialog("Decision");
						}
						else{
							decision = sc.nextLine();
						}
					}
					if(decision.charAt(0) == 'Y' || AutoReEnter)
						newS = "";
					if(decision.charAt(0) == 'A')
						AutoContinue = false;
					if(decision.charAt(0) == 'R'){
						AutoReEnter = true; newS = "";
					}
					
				}
			}
			nums[j] = ComputeRow(newS);
		}
		return nums;
	}
	public static int[][] InputRowsFile(String FileName){
		FileStringReader theFile = new FileStringReader(FileName);
		int[][] nums = new int[10][10];
		for(int j = 1; j <= 9; j++){
			String newS = "";
			while (newS.length() < 9){
				newS = theFile.readLine();
				
			}
			nums[j] = ComputeRow(newS);
		}
		return nums;
	}
	public static void Pause() {
		for(long j = 0; j < (long)(500000000); j++){
		}
	}
	
}
