package myTomorrow.view.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.joda.time.DateTime;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class DateOfEventDialog extends JDialog implements ActionListener
{
	private JDatePickerImpl datePicker;
	private JLabel labelHour;
	private JTextField hours;
	private JTextField minutes;
	private JButton okButton;
	private JButton cancelButton;

	public DateOfEventDialog() {
		this.setModal(true);
		this.setTitle("Saisie");
		this.setSize(350,160);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		
		
		JPanel pan = new JPanel();
		pan.setBorder(BorderFactory.createTitledBorder("Date de l'évènement à supprimer"));
		pan.setLayout(new GridLayout(2,2));
		JLabel label = new JLabel("Saisir le jour :");
		pan.add(label);
		
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		this.datePicker = new JDatePickerImpl(datePanel);
		
		pan.add(this.datePicker);
		
		JLabel label2 = new JLabel("Saisir l'heure :");
		pan.add(label2);
		
		JPanel panHours = new JPanel();
		panHours.setLayout(new GridLayout(1, 3));
		this.hours = new JTextField();
		this.labelHour = new JLabel("h");
		this.labelHour.setHorizontalAlignment(SwingConstants.CENTER); 
		this.minutes = new JTextField();
		panHours.add(this.hours);
		panHours.add(this.labelHour);
		panHours.add(this.minutes);
		pan.add(panHours);
		
		
		JPanel control = new JPanel();
		this.okButton = new JButton("Valider");
		this.okButton.setPreferredSize(new Dimension(90, 30));
		control.add(this.okButton);
		this.okButton.addActionListener(this);
		
		this.cancelButton = new JButton("Annuler");
		this.cancelButton.setPreferredSize(new Dimension(90, 30));
		control.add(this.cancelButton);
		this.cancelButton.addActionListener(this);
		this.add(control);
		
		JSplitPane split = new JSplitPane();
		split.setOrientation(JSplitPane.VERTICAL_SPLIT);
		split.setTopComponent(pan);
		split.setDividerLocation(90);
		split.setBottomComponent(control);
		split.setDividerSize(0);
		split.setEnabled(false);
		this.add(split);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	public DateTime getDate()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
