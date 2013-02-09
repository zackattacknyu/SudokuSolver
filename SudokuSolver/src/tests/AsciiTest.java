package tests;

public class AsciiTest {
	public static void main(String[] args) {
		for(int j = 0; j < 256; j++){
			char a = (char)(j);
			System.out.println(j + " " + a);
		}
	}
}
