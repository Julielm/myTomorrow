package myTomorrow.view.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import myTomorrow.model.ScheduledEvent;
import myTomorrow.model.TimeSlot;

import org.joda.time.DateTime;

public class Calendar extends JPanel
{
	private Image img;
	
	public Calendar(){
		this.setLayout(null);
		JButton previousWeek = new JButton();
		Icon previous = new ImageIcon("PreviousWeek.png");
		previousWeek.setIcon(previous);
		previousWeek.setBounds(1,8,28,30);
		previousWeek.setBorder(BorderFactory.createEmptyBorder());
		previousWeek.setBackground(Color.WHITE);
		this.add(previousWeek);
		
		JButton nextWeek = new JButton();
		Icon next = new ImageIcon("NextWeek.png");
		nextWeek.setIcon(next);
		nextWeek.setBounds(28,8,28,30);
		nextWeek.setBorder(BorderFactory.createEmptyBorder());
		nextWeek.setBackground(Color.WHITE);
		this.add(nextWeek);
		
		JButton event = new GraphicalEvent(new ScheduledEvent(new TimeSlot(new DateTime(2015,05,15,8,15), new DateTime(2015,05,15,9,15))));
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
