package myTomorrow.view.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import org.joda.time.DateTime;

import myTomorrow.model.Answer;
import myTomorrow.model.Appointment;
import myTomorrow.model.Day;
import myTomorrow.model.Lesson;
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
	private JSplitPane navigation; 
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
		this.navigation = new Navigation();
		split.setTopComponent(this.navigation);
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
		JDialog personInformation = new PersonInformationDialog();
		personInformation.setVisible(true);
		return ((PersonInformationDialog) personInformation).getPersonInput();
	}

	@Override
	public Day askAvailableDay()
	{
		JDialog availableDay = new AvailableDayDialog();
		availableDay.setVisible(true);
		return ((AvailableDayDialog) availableDay).getAvailableDay();
	}
	@Override
	public int askDurationOfEvent()
	{
		JDialog durationOfEvent = new DurationOfEventDialog();
		durationOfEvent.setVisible(true);
		return ((DurationOfEventDialog) durationOfEvent).getDurationInput();
	}


	@Override
	public Answer suggestTimeSlot(TimeSlot timeSlot)
	{
		JDialog suggestionOfTimeSlot = new SuggestionOfTimeSlotDialog(timeSlot);
		suggestionOfTimeSlot.setVisible(true);
		
		return ((SuggestionOfTimeSlotDialog) suggestionOfTimeSlot).getSuggestionInput();
	}

	@Override
	public void freeTimeSlotIsEmpty()
	{
		JOptionPane.showMessageDialog(this, "Il n'y a pas de créneaux libres"," Attention ",JOptionPane.WARNING_MESSAGE);
		
	}

	@Override
	public void userDontWantTheseFreeTimeSlots()
	{
		JOptionPane.showMessageDialog(this, "Il n'y a plus de créneaux libres"," Attention ",JOptionPane.WARNING_MESSAGE);
		
	}

	@Override
	public String askTitleOfTheLesson()
	{
		JDialog lessonInformation = new LessonInformationDialog();
		lessonInformation.setVisible(true);
		return ((LessonInformationDialog) lessonInformation).getTitleInput();
	}

	@Override
	public TimeSlot askAvailablePeriod()
	{
		JDialog availablePeriod = new AvailablePeriodDialog();
		availablePeriod.setVisible(true);
		return ((AvailablePeriodDialog) availablePeriod).getPeriod();
	}

	@Override
	public void lessonsInThePeriodIsEmpty()
	{
		JOptionPane.showMessageDialog(this, "Il n'y a pas de cours avec cet intitulé dans la période.\n Ajouter d'abord un cours"," Attention ",JOptionPane.WARNING_MESSAGE);
		
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
		JOptionPane.showMessageDialog(this, "La personne a bien été ajoutée au cours"," Information ",JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	@Override
	public void initCalendar(List<ScheduledEvent> events, List<String> days,ScheduleManager application, int week)
	{
		this.application=application;
		((Navigation) this.navigation).initNavigation(this.application);
		this.events=events;
		this.days = days;
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
				if (event instanceof Appointment) {
					buttonOfEvent.setBackground(new Color(0, 168, 255));
					buttonOfEvent.setText(((Appointment) event).getPerson().toString());
				}
				if (event instanceof Lesson) {
					buttonOfEvent.setBackground(new Color(39, 207, 0));
					buttonOfEvent.setText(((Lesson) event).getTitle());
				}
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
				endWeek=new DateTime(today.getYear(), today.getMonthOfYear(), today.getDayOfMonth(), 18,0);
				today=today.minusDays(6);
				dayOfWeek=1;
			}
			else {
				if (dayOfWeek==1) {
					startWeek=new DateTime(today.getYear(), today.getMonthOfYear(), today.getDayOfMonth(), 8,0);
				}
				today=today.plusDays(1);
				dayOfWeek+=1;
			}
			this.calendar.add(label);
		}
	
		for (ScheduledEvent event : this.events) {
			if (!(event.getTimeSlot().getStartTime().isBefore(startWeek))&& !(event.getTimeSlot().getStartTime().isAfter(endWeek))) {
				JButton buttonOfEvent = new GraphicalEvent(event);
				if (event instanceof Appointment) {
					buttonOfEvent.setText(((Appointment) event).getPerson().toString());
					buttonOfEvent.setBackground(new Color(0, 168, 255));		
				}
				if (event instanceof Lesson) {
					buttonOfEvent.setBackground(new Color(39, 207, 0));
					buttonOfEvent.setText(((Lesson) event).getTitle());
				}
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
	
	public void setWeekNb(int nb) {
		this.weekNb=nb;
	}
	
	public ScheduleManager getApplication() {
		return this.application;
	}

	@Override
	public void displayFinishedAddition(ScheduledEvent event)
	{
		this.weekNb=event.getTimeSlot().getStartTime().getWeekOfWeekyear()-DateTime.now().getWeekOfWeekyear();
		this.updateCalendar();
		
	}

}
