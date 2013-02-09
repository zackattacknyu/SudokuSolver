package tests;

public class SubstringTest {
	public static void main(String[] args) {
		String a = "abcdefghi";
		for(int j = 0; j < a.length(); j++){
			System.out.print(a.charAt(j) + " ");
		}
		String b = a.substring(1, a.length());
		System.out.println();
		for(int j = 0; j < b.length(); j++){
			System.out.print(b.charAt(j) + " ");
		}
	}
}
