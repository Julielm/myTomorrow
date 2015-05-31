package myTomorrow.view.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JPanel;

import myTomorrow.model.ScheduledEvent;
import myTomorrow.model.TimeSlot;

import org.joda.time.DateTime;

public class Calendar extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;
		
	public Calendar(){
		
		
		JButton event = new GraphicalEvent(new ScheduledEvent(new TimeSlot(new DateTime(2015,05,31,9,15), new DateTime(2015,05,31,9,15))));
		this.add(event);
	}
	
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
