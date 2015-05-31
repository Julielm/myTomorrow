package myTomorrow.view.gui;

import java.util.List;

import javax.swing.JLabel;

import org.joda.time.DateTime;

public class DayLabel extends JLabel
{
	public DayLabel(String label, int dayOfWeek){
		this.setText(label);
		this.setBounds(58+((dayOfWeek-1)*133),15,GraphicalEvent.EVENT_WIDTH,30);
		
	}
	
}
