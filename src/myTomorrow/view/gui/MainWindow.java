package myTomorrow.view.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.joda.time.DateTime;

import myTomorrow.model.Day;
import myTomorrow.model.Person;
import myTomorrow.model.ScheduleManager;
import myTomorrow.model.ScheduledEvent;
import myTomorrow.model.TimeSlot;
import myTomorrow.view.UserIHM;

public class MainWindow extends JFrame implements Runnable, UserIHM, ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel calendar;
	private JButton previousWeek; 
	private JButton nextWeek;
	private int weekNb;
	private List<ScheduledEvent> events;
	private List<String> days;
	private ScheduleManager application;
	
	public MainWindow() {
		
		this.setTitle("My Tomorrow");
		this.setSize(1200, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane split = new JSplitPane();
		split.setDividerLocation(200);
		JPanel buttons = new JPanel();
		split.setTopComponent(buttons);
		split.setDividerSize(0);
		split.setEnabled(false);
		this.calendar = new Calendar();
		split.setBottomComponent(calendar);
		
		this.weekNb=0;
		
		this.getContentPane().add(split);
		
	}
	
	@Override
	public void run()
	{
		this.setVisible(true);
		
	}

	@Override
	public Person askPersonInformations()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Day askAvailableDay()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int askDurationOfEvent()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean suggestTimeSlot(TimeSlot timeSlot)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void freeTimeSlotIsEmpty()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void userDontWantTheseFreeTimeSlots()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String askTitleOfTheLesson()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TimeSlot askAvailablePeriod()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void lessonsInThePeriodIsEmpty()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public DateTime inputDateOfEvent()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void thePersonInputIsNTInLesson()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void initCalendar(List<ScheduledEvent> events, List<String> days, ScheduleManager application, int week)
	{
		this.events=events;
		this.days = days;
		this.application = application;
		DateTime today = DateTime.now().plusWeeks(this.weekNb);
		int dayOfWeek = today.getDayOfWeek();
		DateTime startWeek = new DateTime();
		DateTime endWeek = new DateTime();
		for (int day=0; day<7; day++){
			DayLabel label = new DayLabel(this.days.get(dayOfWeek-1)+" "+today.getDayOfMonth()+"/"+today.getMonthOfYear(), dayOfWeek); 
			
			if ((dayOfWeek+1)>7) {
				endWeek=today;
				today=today.minusDays(6);
				dayOfWeek=1;
			}
			else {
				if (dayOfWeek==1) {
					startWeek=today;
				}
				today=today.plusDays(1);
				dayOfWeek+=1;
			}
			this.calendar.add(label);
		}
	
		for (ScheduledEvent event : this.events) {
			if (!(event.getTimeSlot().getStartTime().isBefore(startWeek))&& !(event.getTimeSlot().getStartTime().isAfter(endWeek))) {
				JButton buttonOfEvent = new GraphicalEvent(event);
				this.calendar.add(buttonOfEvent);
			}
		}
		this.calendar.setLayout(null);
		this.previousWeek = new JButton();
		Icon previous = new ImageIcon("PreviousWeek.png");
		this.previousWeek.setIcon(previous);
		this.previousWeek.setBounds(1,8,28,30);
		this.previousWeek.setBorder(BorderFactory.createEmptyBorder());
		this.previousWeek.setBackground(Color.WHITE);
		this.calendar.add(this.previousWeek);
		this.previousWeek.addActionListener(this);
		
		this.nextWeek = new JButton();
		Icon next = new ImageIcon("NextWeek.png");
		this.nextWeek.setIcon(next);
		this.nextWeek.setBounds(28,8,28,30);
		this.nextWeek.setBorder(BorderFactory.createEmptyBorder());
		this.nextWeek.setBackground(Color.WHITE);
		this.calendar.add(this.nextWeek);
		this.nextWeek.addActionListener(this);
	}
	
	@Override
	public void updateCalendar()
	{
		this.calendar.removeAll();
		this.calendar.repaint();
		DateTime today = DateTime.now().plusWeeks(this.weekNb);
		int dayOfWeek = today.getDayOfWeek();
		DateTime startWeek = new DateTime();
		DateTime endWeek = new DateTime();
		for (int day=0; day<7; day++){
			DayLabel label = new DayLabel(this.days.get(dayOfWeek-1)+" "+today.getDayOfMonth()+"/"+today.getMonthOfYear(), dayOfWeek); 
			
			if ((dayOfWeek+1)>7) {
				endWeek=today;
				today=today.minusDays(6);
				dayOfWeek=1;
			}
			else {
				if (dayOfWeek==1) {
					startWeek=today;
				}
				today=today.plusDays(1);
				dayOfWeek+=1;
			}
			this.calendar.add(label);
		}
	
		for (ScheduledEvent event : this.events) {
			if (!(event.getTimeSlot().getStartTime().isBefore(startWeek))&& !(event.getTimeSlot().getStartTime().isAfter(endWeek))) {
				JButton buttonOfEvent = new GraphicalEvent(event);
				this.calendar.add(buttonOfEvent);
			}
		}
		this.calendar.setLayout(null);
		this.previousWeek = new JButton();
		Icon previous = new ImageIcon("PreviousWeek.png");
		this.previousWeek.setIcon(previous);
		this.previousWeek.setBounds(1,8,28,30);
		this.previousWeek.setBorder(BorderFactory.createEmptyBorder());
		this.previousWeek.setBackground(Color.WHITE);
		this.calendar.add(this.previousWeek);
		this.previousWeek.addActionListener(this);
		
		this.nextWeek = new JButton();
		Icon next = new ImageIcon("NextWeek.png");
		this.nextWeek.setIcon(next);
		this.nextWeek.setBounds(28,8,28,30);
		this.nextWeek.setBorder(BorderFactory.createEmptyBorder());
		this.nextWeek.setBackground(Color.WHITE);
		this.calendar.add(this.nextWeek);
		this.nextWeek.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(previousWeek)) {
			this.weekNb--;
		}
		if(e.getSource().equals(nextWeek)) {
			this.weekNb++;
		}
		this.updateCalendar();
		
	}
	@Override
	public JPanel getCalendar()
	{
		return this.calendar;
	}
	@Override
	public int getWeekNB()
	{
		return this.weekNb;
	}

}
