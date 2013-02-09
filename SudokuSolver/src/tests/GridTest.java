package tests;

public class GridTest {
	public static void main(String[] args) {
		for(int j =1; j <= 9; j++){
			int xstart = ((j-1)/3)*3 + 1;
			int ystart = ((j-1)%3)*3 + 1;
			System.out.println("Box " + j + " row start:" + 
					xstart + " column start:" + ystart); 
		}
		System.out.println();
		boolean[] a = new boolean[3];
		System.out.println(a[2]);
		for(int j =1; j <= 9; j++){
			int xplace = ((j-1)/3) + 1;
			int yplace = ((j-1)%3) + 1;
			System.out.println("Loc " + j + " row place:" + 
					xplace + " column place:" + yplace); 
		}
		
	}
}
