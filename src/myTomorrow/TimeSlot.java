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
	 * Set in the time put in parameters a predefined time. 
	 * 
	 * @param timeToSet
	 * @param time
	 * @throws SaturdayException
	 * @throws SundayException
	 */
	public void setTime(DateTime timeToSet, DateTime time){
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


	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(DateTime startTime)
	{
		this.startTime = startTime;
	}


	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(DateTime endTime)
	{
		this.endTime = endTime;
	}
}
