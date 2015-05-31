package myTomorrow.view;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import org.joda.time.DateTime;

import myTomorrow.model.Day;
import myTomorrow.model.Person;
import myTomorrow.model.ScheduleManager;
import myTomorrow.model.ScheduledEvent;
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
	
	public void freeTimeSlotIsEmpty();
	
	public void userDontWantTheseFreeTimeSlots();
	public String askTitleOfTheLesson();
	public TimeSlot askAvailablePeriod();
	public void lessonsInThePeriodIsEmpty();
	public DateTime inputDateOfEvent();
	public void thePersonInputIsNTInLesson();
	public void updateCalendar(List<ScheduledEvent> events, List<String> days, ScheduleManager application, int week);
	public JPanel getCalendar();
	public int getWeekNB();

}
