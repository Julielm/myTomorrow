package myTomorrow.model;

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
	 * Constructor of a time slot to initialize a time slot to null.
	 */
	public TimeSlot() {
		this.startTime=null;
		this.endTime=null;
	}
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
	
	/**
	 * Display a TimeSlot
	 * @return a string
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		String newLine = System.getProperty("line.separator");
		str.append("Créneau : ");
		str.append(newLine);
		str.append("Date début : ");
		str.append(this.getStartTime());
		str.append(newLine);
		str.append("Date fin : ");
		str.append(this.getEndTime());
		str.append(newLine);
		return str.toString();
	}
	
	public boolean isBefore(TimeSlot timeslot) {
		return this.startTime.isBefore(timeslot.startTime);
	}
	
	public String toString(DateTime time){
		StringBuilder str = new StringBuilder();
		str.append(time.getHourOfDay());
		str.append("h");
		str.append(time.getMinuteOfHour());
		return str.toString();
	}
}
