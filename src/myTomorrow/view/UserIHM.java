package myTomorrow.view;

import myTomorrow.model.Day;
import myTomorrow.model.Person;
import myTomorrow.model.TimeSlot;

/**
 * Interface for the user.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public interface UserIHM
{
	/**
	 * Ask informations of a person.
	 * @return a person
	 */
	public Person askPersonInformations();
	/**
	 * Ask the day available of the person.
	 * @return a day
	 */
	public Day askAvailableDay();
	/**
	 * Ask the duration of the event to schedule.
	 * @return the duration 
	 */
	public int askDurationOfEvent();
	/**
	 * @param timeSlot
	 * @return the answer of the user
	 */
	public boolean suggestTimeSlot(TimeSlot timeSlot);

}
