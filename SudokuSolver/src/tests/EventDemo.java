package tests;
import java.applet.*;
import java.awt.*;
public class EventDemo extends Applet{
	private String message = "Waiting for events...";
	int width; 
	int height;
	// Default constructor
	public EventDemo()
	{
		// Call parent constructor
		super();
	}

//	Init method, called when applet first initialises
	public void init()
	{
		setBackground( Color.white );
		width = 100; height = 100;
	}

	// Overridden paint method
	public void paint ( Graphics g )
	{
		g.setColor(Color.blue);
		g.drawString(message, 0, 20);
	}

//	Overridden methods for event handling
	public boolean mouseEnter( Event evt, int x, int y)
	{
		// Set message....
		message = "mouseEnter - x:" + x + " y: " + y;

		// ... and repaint applet
		repaint();

		// Signal we have handled the event
		return true;
	}

	public boolean mouseExit( Event evt, int x, int y)
	{
		// Set message....
		message = "mouseExit - x:" + x + " y: " + y;

		// ... and repaint applet
		repaint();

		// Signal we have handled the event
		return true;
	}

	public boolean mouseMove( Event evt, int x, int y)
	{
		// Set message....
		message = "mouseMove - x:" + x + " y: " + y;

		// ... and repaint applet
		repaint();

		// Signal we have handled the event
		return true;
	}

	public boolean mouseDown( Event evt, int x, int y)
	{
		// Set message....
		message = "mouseDown - x:" + x + " y: " + y;

		// ... and repaint applet
		repaint();

		// Signal we have handled the event
		return true;
	}
}
