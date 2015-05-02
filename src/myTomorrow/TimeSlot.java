package myTomorrow;

import org.joda.time.DateTime;

/**
 * Time slot of an appointment or a lesson.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class TimeSlot
{
	/** Start time of the task.*/
	private DateTime startTime;
	/** End time of the task. */
	private DateTime endTime;
	
	
	/**
	 * Constructor of a time slot.
	 * @param startTime
	 * @param endTime
	 */
	public TimeSlot(DateTime startTime, DateTime endTime)
	{
		this.startTime=startTime;
		this.endTime=endTime;
		
	}

	/**
	 * Set in a calendar a new date, check if this date is not a saturday of a sunday.
	 * @param time
	 * @param year
	 * @param month
	 * @param dayOfMonth
	 * @param dayOfWeek
	 * @param hour
	 * @param minute
	 * @throws SaturdayException
	 * @throws SundayException
	 */
	public void setTime(DateTime timeToSet, DateTime time) throws SaturdayException, SundayException {
		if (time.dayOfWeek().get() == 7) {
			throw new SaturdayException();
		}
		if (time.dayOfWeek().get() == 1) {
			throw new SundayException();
		}
		timeToSet=time;		
	}
	
	
	/**
	 * Getter for the start time.
	 * @return the startTime
	 */
	public DateTime getStartTime()
	{
		return this.startTime;
	}

	/**
	 * Getter for the endTime.
	 * @return the endTime
	 */
	public DateTime getEndTime()
	{
		return this.endTime;
	}
}
