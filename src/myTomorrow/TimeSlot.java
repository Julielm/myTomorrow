package myTomorrow;

import java.util.Calendar;


/**
 * Time slot of an appointment or a lesson.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class TimeSlot
{
	/** Start time of the task.*/
	private final Calendar startTime;
	/** End time of the task. */
	private final Calendar endTime;
	
	
	/**
	 * Constructor of a time slot.
	 * @param startTime
	 * @param endTime
	 */
	public TimeSlot(Calendar startTime, Calendar endTime)
	{
		this.startTime=startTime;
		this.endTime=endTime;
		
	}

	/**
	 * Getter for the start time.
	 * @return the startTime
	 */
	public Calendar getStartTime()
	{
		return this.startTime;
	}

	/**
	 * Getter for the endTime.
	 * @return the endTime
	 */
	public Calendar getEndTime()
	{
		return this.endTime;
	}
}
