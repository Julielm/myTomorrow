package myTomorrow;

import sun.util.calendar.BaseCalendar.Date;


/**
 * Time slot of an appointment or a lesson.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class TimeSlot
{
	/** Start time of the task.*/
	private final Date startTime;
	/** End time of the task. */
	private final Date endTime;
	
	
	/**
	 * Constructor of a time slot.
	 * @param startTime
	 * @param endTime
	 */
	public TimeSlot(Date startTime, Date endTime)
	{
		this.startTime=startTime;
		this.endTime=endTime;
		
	}

	/**
	 * Getter for the start time.
	 * @return the startTime
	 */
	public Date getStartTime()
	{
		return this.startTime;
	}

	/**
	 * Getter for the endTime.
	 * @return the endTime
	 */
	public Date getEndTime()
	{
		return this.endTime;
	}
}
