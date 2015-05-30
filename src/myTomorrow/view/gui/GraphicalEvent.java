package myTomorrow.view.gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class GraphicalEvent extends JButton
{
	public static final int EVENT_WIDTH=130;
	
	public GraphicalEvent(int x, int y, int height, String event){
		this.setBounds(x,y,EVENT_WIDTH, height);
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setName(event);
	}
}
