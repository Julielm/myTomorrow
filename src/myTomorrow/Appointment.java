package myTomorrow;

import org.joda.time.DateTime;

/**
 * Appointment at person's home.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Appointment extends TimeSlot
{
	/** Duration of the appointment in minutes.*/
	private final int duration;
	/** Person who takes the appointment.*/
	private final Person person;
	

	/**
	 * Constructor of an appointment.
	 * @param duration
	 * @param person
	 * @param startTime
	 * @param endTime
	 */
	public Appointment(int duration, Person person, DateTime startTime, DateTime endTime)
	{
		super(startTime, endTime);
		this.duration=duration;
		this.person=person;
	}

	/**
	 * Getter for the duration.
	 * @return the duration
	 */
	public int getDuration()
	{
		return this.duration;
	}

	/**
	 * Getter for the person.
	 * @return the person
	 */
	public Person getPerson()
	{
		return this.person;
	}
}
