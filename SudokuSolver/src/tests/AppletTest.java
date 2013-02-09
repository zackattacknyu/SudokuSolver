package tests;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class AppletTest extends Applet{
	private static final long serialVersionUID = 1L;
	String s = "unknown";
	int width;
	int height;
	Button b;
	Label l = new Label("Here is my program");
	public void init(){
		width = 100;
		height = 500;
		setBackground(Color.white);
		TextField tf1, tf2, tf3, tf4;
		 // a blank text field
		 tf1 = new TextField();
		 // blank field of 20 columns
		 tf2 = new TextField("", 20);
		 // predefined text displayed
		 tf3 = new TextField("Hello!");
		 // predefined text in 30 columns
		 tf4 = new TextField("Hello", 30);
	}
	public boolean action(Event e, Object args){
		if(e.target == b){
			DoAction();
		}
		repaint();
		return true;
	}
	public void DoAction(){
		s = JOptionPane.showInputDialog("What do you want to show");
	}
	public void paint(Graphics g){
		g.setColor(Color.black);	
		g.drawString(s, 20, 50);
	}
}

