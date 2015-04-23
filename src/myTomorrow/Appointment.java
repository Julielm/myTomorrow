package myTomorrow;

/**
 * Appointment at person's home.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Appointment
{
	/** Duration of the appointment.*/
	private final int duration;
	/** Person who takes the appointment.*/
	private final Person person;
	/** Time slot of the appointment.*/
	private final TimeSlot timeSlot;
	
	/**
	 * Constructor of an appointment.
	 * @param duration
	 * @param person
	 * @param timeSlot
	 */
	public Appointment(int duration, Person person, TimeSlot timeSlot)
	{
		this.duration=duration;
		this.person=person;
		this.timeSlot=timeSlot;
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

	/**
	 * Getter for the timeSlot.
	 * @return the timeSlot
	 */
	public TimeSlot getTimeSlot()
	{
		return this.timeSlot;
	}
}
