package myTomorrow.view.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;

public class Navigation extends JSplitPane
{
	private JPanel buttons;
	private JPanel legend;
	
	public Navigation(){
		this.setOrientation(VERTICAL_SPLIT);
		this.buttons = new JPanel();
		this.legend = new JPanel();
		this.setDividerLocation(500);
		this.setTopComponent(this.buttons);
		this.setBottomComponent(this.legend);
		this.setDividerSize(0);
		this.setEnabled(false);
		this.buttons.setLayout(new GridLayout(4,1));
		JButton appointment= new JButton("Ajouter RDV");
		this.buttons.add(appointment);
		JButton lesson= new JButton("Ajouter Cours");
		this.buttons.add(lesson);
		JButton person= new JButton("<html><head> <style> p{ text-align : center}</style></head><body><p>Ajouter une personne <br>à un cours</p></body></html>");
		this.buttons.add(person);
		JButton delete= new JButton("Supprimer");
		this.buttons.add(delete);
		
		this.legend.setLayout(new GridLayout(2,1));
		JLabel appointmentLegend = new JLabel(" RDV  ");
		appointmentLegend.setIcon(new ImageIcon("legendeRDV.png"));
		appointmentLegend.setHorizontalAlignment(SwingConstants.CENTER); 
		this.legend.add(appointmentLegend);
		
		JLabel lessonLegend = new JLabel(" Cours");
		lessonLegend.setIcon(new ImageIcon("legendeCours.png"));
		lessonLegend.setHorizontalAlignment(SwingConstants.CENTER); 
		this.legend.add(lessonLegend);
	}
	
	public JPanel getButtons() {
		return this.buttons;
	}
	
	public JPanel getLegend() {
		return this.legend;
	}

}
