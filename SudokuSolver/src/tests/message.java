package tests;

import javax.swing.*;
public class message {
	public static void main(String[] args) {
		String[] s = {"Here I am", "on cue"};
		int i = JOptionPane.showConfirmDialog(null, s, "In", 2);
		System.out.println(i);
		JOptionPane.showMessageDialog(null, s);
	}
}
