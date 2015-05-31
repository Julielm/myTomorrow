package myTomorrow.view.gui;

import java.util.LinkedList;
import java.util.List;

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

public class MainWindow extends JFrame implements Runnable, UserIHM
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel calendar;
	
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
		
		this.getContentPane().add(split);
		this.setVisible(true);
	}
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		
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
	public void updateCalendar(List<ScheduledEvent> events)
	{
		for (ScheduledEvent event : events) {
			JButton buttonOfEvent = new GraphicalEvent(event);
			this.calendar.add(buttonOfEvent);
		}
		
	}

}
