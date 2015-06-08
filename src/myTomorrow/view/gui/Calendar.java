package myTomorrow.view.gui;

import java.awt.Graphics;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Display calendar in a JPanel.
 * @author myTomorrow
 *
 */
public class Calendar extends JPanel
{
	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Image of the calendar.
	 */
	private Image img;
	
	/**
	 * Put the calendar image in JPanel.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		try 
		{
		      this.img = ImageIO.read(new File("Calendar.png"));
		      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}  
	}

	
}
