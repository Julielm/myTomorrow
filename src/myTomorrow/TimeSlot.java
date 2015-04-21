package myTomorrow;

/**
 * Time slot of an appointment or a lesson.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class TimeSlot
{
	//TODO watch how to link the Time type and Date type with Java
	/** Start time of the task.*/
	private final Time startTime;
	/** End time of the task. */
	private final Time endTime;
	/** Day of the task.*/
	private final Date date;
	
	/**
	 * Constructor of a time slot.
	 * @param startTime
	 * @param endTime
	 * @param date
	 */
	public TimeSlot(Time startTime, Time endTime, Date date)
	{
		this.startTime=startTime;
		this.endTime=endTime;
		this.date=date;
	}
}
