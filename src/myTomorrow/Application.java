package myTomorrow;

import java.util.ArrayList;
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
		this.calendar = new ArrayList<TimeSlot>();
		this.myIHM = new UserIHM();
	}
	
	/**
	 * Getter for the calendar.
	 * @return the calendar
	 */
	public ArrayList<TimeSlot> getCalendar()
	{
		return (ArrayList<TimeSlot>)this.calendar;
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
