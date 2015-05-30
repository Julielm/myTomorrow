package myTomorrow.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BackgroundCalendar extends JPanel
{
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		try 
		{
		      Image img = ImageIO.read(new File("Calendar.png"));
		      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} 
		catch (IOException e) 
		{
		      e.printStackTrace();
		}  
		for (int y=0; y <7; y++) {
			for (int x=0; x<10; x++) {
				JButton event = new GraphicalEvent(57+(y*133),40+(x*60),61, "event");
				this.add(event);
			}
		}
		
	}
}
