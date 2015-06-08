package myTomorrow.view.gui;

import javax.swing.JLabel;

public class DayLabel extends JLabel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DayLabel(String label, int dayOfWeek){
		this.setText(label);
		this.setBounds(58+((dayOfWeek-1)*133),15,GraphicalEvent.EVENT_WIDTH,30);
		
	}
	
}
