package myTomorrow;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


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
		Calendar calendar = Calendar.getInstance();
		while (period.getStartDate().before(period.getEndDate()) && timeSlot.getStartTime() == null) {
			int index = 0;
			while (this.calendar.get(index).getStartTime().before(period.getStartDate()) && index < this.calendar.size()) {
				index++;
			}
			if (index == this.calendar.size()) {
				try {
					timeSlot.setCalendar(timeSlot.getStartTime(), period.getStartDate().YEAR, period.getStartDate().MONTH, period.getStartDate().DAY_OF_MONTH, period.getStartDate().DAY_OF_WEEK, 9, 0);
				}
				catch (SaturdayException e){
					period.getStartDate().add(Calendar.DAY_OF_MONTH, 2);
					timeSlot.getStartTime().set(period.getStartDate().YEAR, period.getStartDate().MONTH,period.getStartDate().DAY_OF_MONTH , 9, 0);
				}
				catch (SundayException f) {
					period.getStartDate().add(Calendar.DAY_OF_MONTH, 1);
					timeSlot.getStartTime().set(period.getStartDate().YEAR, period.getStartDate().MONTH,period.getStartDate().DAY_OF_MONTH , 9, 0);
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
