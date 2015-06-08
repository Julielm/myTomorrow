package myTomorrow.view;

import java.util.List;

import javax.swing.JPanel;

import org.joda.time.DateTime;

import myTomorrow.model.Answer;
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
	 * Suggest at the user a timeslot for his lesson, appointment, ...
	 * @param timeSlot
	 * @return the answer of the user
	 */
	public Answer suggestTimeSlot(TimeSlot timeSlot);
	
	/**
	 * Open a JDialogue : No TimeSlot correspond at the request of the user.
	 */
	public void freeTimeSlotIsEmpty();
	
	/**
	 * Open a JDialogue : No TimeSlot are good for the user.
	 */
	public void userDontWantTheseFreeTimeSlots();
	
	/**
	 * Ask title of a lesson.
	 * @return the title of a lesson
	 */
	public String askTitleOfTheLesson();
	
	/**
	 * Ask period are the user available.
	 * @return a period
	 */
	public TimeSlot askAvailablePeriod();
	
	/**
	 * Open a JDialogue : No lesson correspond at the request of the user.
	 */
	public void lessonsInThePeriodIsEmpty();
	
	/**
	 * Ask date for an event.
	 * @return a date
	 */
	public DateTime inputDateOfEvent();
	
	/**
	 * Inform user the person are not in a lesson
	 */
	public void thePersonInputIsNTInLesson();
	
	/**
	 * Initiation of the calendar
	 * @param events
	 * @param days
	 * @param application
	 * @param week
	 */
	public void initCalendar(List<ScheduledEvent> events, List<String> days, ScheduleManager application, int week);
	
	/**
	 * Get the calendar.
	 * @return calendar
	 */
	public JPanel getCalendar();
	
	/**
	 * Get the week number.
	 * @return the week number
	 */
	public int getWeekNB();
	
	/**
	 * Refresh display of the calendar.
	 */
	public void updateCalendar();
	
	/**
	 * Display the next/previous week.
	 * @param event
	 */
	public void displayFinishedAddition(ScheduledEvent event);
	
	/**
	 * No event are available at the date
	 */
	public void noEventAtThisDate();
	
	/**
	 * Inform user the event are deleted.
	 */
	public void eventDeleted();
	
	/**
	 * Inform user the person are deleted.
	 */
	public void personDeleted();

}
