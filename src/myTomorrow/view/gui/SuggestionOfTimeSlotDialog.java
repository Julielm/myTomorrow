package myTomorrow.view.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import org.joda.time.DateTime;

import myTomorrow.model.Day;
import myTomorrow.model.Person;
import myTomorrow.model.TimeSlot;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class SuggestionOfTimeSlotDialog extends JDialog implements ActionListener
{
	private JButton yesButton;
	private JButton noButton;
	private JButton cancelButton;
	private boolean answer;

	
	public SuggestionOfTimeSlotDialog(TimeSlot timeSlot)
	{
		this.setModal(true);
		this.setSize(400,180);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Validation"));
		pan.setLayout(new GridLayout(3,2));
		pan.add(new JLabel("Ce créneau vous convient-il ? "));
		pan.add(new JLabel());
		pan.add(new JLabel("Heure de début :"));
		pan.add(new JLabel(timeSlot.toString(timeSlot.getStartTime())));
		pan.add(new JLabel("Heure de fin : "));
		pan.add(new JLabel(timeSlot.toString(timeSlot.getEndTime())));
		
		JPanel control = new JPanel();
		this.yesButton = new JButton("Oui");
		this.yesButton.setPreferredSize(new Dimension(90, 30));
		control.add(this.yesButton);
		this.yesButton.addActionListener(this);
		
		this.noButton = new JButton("Non");
		this.noButton.setPreferredSize(new Dimension(90, 30));
		control.add(this.noButton);
		this.noButton.addActionListener(this);
		this.add(control);
		
		this.cancelButton = new JButton("Annuler");
		this.cancelButton.setPreferredSize(new Dimension(90, 30));
		control.add(this.cancelButton);
		this.cancelButton.addActionListener(this);
		this.add(control);

		
		JSplitPane split = new JSplitPane();
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split.setTopComponent(pan);
		split.setDividerLocation(100);
		split.setBottomComponent(control);
		split.setDividerSize(0);
		split.setEnabled(false);
		this.add(split);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource()==this.yesButton) {
			this.answer=true;
			this.dispose();
		}
		if (e.getSource()==this.noButton) {
			this.answer=false;
			this.dispose();
		}
		if (e.getSource()==this.cancelButton) {
			this.dispose();
		}
		
	}
	
	public boolean getSuggestionInput(){
		return this.answer;
	}
	
}
