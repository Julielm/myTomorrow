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
	private Calendar startTime;
	/** End time of the task. */
	private Calendar endTime;
	
	
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
	public void setCalendar(Calendar time, int year, int month, int dayOfMonth, int dayOfWeek, int hour, int minute) throws SaturdayException, SundayException {
		if (dayOfWeek == 7) {
			throw new SaturdayException();
		}
		if (dayOfWeek == 1) {
			throw new SundayException();
		}
		time.set(year, month, dayOfMonth, hour, minute);		
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
