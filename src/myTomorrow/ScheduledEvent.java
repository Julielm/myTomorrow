package myTomorrow;

/**
 * Scheduled event.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class ScheduledEvent
{
	/**
	 * Time slot of the scheduled event.
	 */
	private TimeSlot timeSlot;
	
	/**
	 * Constructor of a schedule event.
	 * @param timeSlot
	 */
	public ScheduledEvent(TimeSlot timeSlot) {
		this.timeSlot= timeSlot;
	}
	
	/**
	 * Getter for the time slot.
	 * @return the time slot
	 */
	public TimeSlot getTimeSlot()
	{
		return this.timeSlot;
	}
}
