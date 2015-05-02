package myTomorrow;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;


/**
 * Application. 
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Application
{
	/**
	 * List of the used time slots.
	 */
	private final List<TimeSlot> calendar;
	
	/** 
	 * User's IHM.
	 */
	private final UserIHM myIHM;
	
	/**
	 * Constructor of an application.
	 */
	public Application(){
		this.calendar = new LinkedList<TimeSlot>();
		this.myIHM = new UserIHM();
	}
	
	//TODO JavaDoc and continue the method.
	public void addAppointment(){
		Appointment appointment = this.myIHM.inputAppointment();	
	}
	
	//TODO JavaDoc.
	private TimeSlot searchTimeSlot(Period period, int duration) {
		TimeSlot timeSlot = new TimeSlot(null, null);
		DateTime dateTime = new DateTime();
		while (period.getStartDate().isBefore(period.getEndDate()) && timeSlot.getStartTime() == null) {
			int index = 0;
			while (this.calendar.get(index).getStartTime().isBefore(period.getStartDate()) && index < this.calendar.size()) {
				index++;
			}
			if (index == this.calendar.size()) {
				try {
					timeSlot.setTime(timeSlot.getStartTime(), period.getStartDate());
				}
				catch (SaturdayException e){
					period.getStartDate().plusDays(2);
					timeSlot.getStartTime().withDate(period.getStartDate().getDayOfYear(), period.getStartDate().getMonthOfYear(), period.getStartDate().getDayOfMonth());
				}
				catch (SundayException f) {
					period.getStartDate().plusDays(1);
					timeSlot.getStartTime().withDate(period.getStartDate().getDayOfYear(), period.getStartDate().getMonthOfYear(), period.getStartDate().getDayOfMonth());
				}
				finally {
					DateTime time = this.myIHM.askTime();
					timeSlot.getStartTime().withTime(time.getHourOfDay(), time.getMinuteOfHour(), 0, 0);
					time = timeSlot.getStartTime().plusMinutes(duration);
					timeSlot.getEndTime().withTime(time.getHourOfDay(), time.getMinuteOfHour(), 0, 0);
				}
			//TODO Continue the method and optimize it and review the algorithm.			
			}
		}
		return timeSlot;
	}
	
	/**
	 * Getter for the calendar.
	 * @return the calendar
	 */
	public LinkedList<TimeSlot> getCalendar()
	{
		return (LinkedList<TimeSlot>)this.calendar;
	}

	/**
	 * Getter for the IHM.
	 * @return the myIHM
	 */
	public UserIHM getMyIHM()
	{
		return this.myIHM;
	}
	
}
