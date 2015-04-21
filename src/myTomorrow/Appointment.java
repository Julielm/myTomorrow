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
}
