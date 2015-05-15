package myTomorrow;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Instant;


/**
 * Application. 
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Application
{
	/**
	 * List of the used time slots.
	 */
	private final List<ScheduledEvent> events;
	
	/** 
	 * User's IHM.
	 */
	private final UserIHM myIHM;
	
	/**
	 * Constructor of an application.
	 */
	public Application(){
		this.events = new LinkedList<ScheduledEvent>();
		this.myIHM = new UserIHM();
	}
	
	/**
	 * Add an appointment.
	 */
	public void addAppointment(){
		//Appointment appointment = this.myIHM.inputAppointment();	
		//test of a research of a time slot.
		ScheduledEvent event = new ScheduledEvent(this.searchTimeSlot(new Day(15,05,2015), 30));
		this.events.add(event);
		
	}
	
	//TODO JavaDoc.
	private TimeSlot searchTimeSlot(Day day, int duration) {
		List<ScheduledEvent> eventsOnSameDay = this.getAllEventsThatAreOnSameDay(day);
		if (eventsOnSameDay.isEmpty()) {
			return new TimeSlot(day.getStartTime(), day.getStartTime().plusMinutes(duration));
		}
		List<TimeSlot> freeTimeSlots = this.getAllFreeTimeSlots(eventsOnSameDay, duration, day);
		if (freeTimeSlots.isEmpty()) {
			return null;
		}
		return freeTimeSlots.get(0);	
		
	}
	
	private List<TimeSlot> getAllFreeTimeSlots(List<ScheduledEvent> eventsOnSameDay, int duration, Day day)
	{
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		
		DateTime dateOfTheEvent = eventsOnSameDay.get(0).getTimeSlot().getStartTime();
		DateTime dateOfTheEventMinusDuration = dateOfTheEvent.minusMinutes(duration);
		
		if (dateOfTheEventMinusDuration.isAfter(day.getStartTime()) || dateOfTheEventMinusDuration.isEqual(day.getStartTime())) {
			freeTimeSlots.add(new TimeSlot(dateOfTheEventMinusDuration, dateOfTheEvent));
		}
		
		DateTime dateOfThePreviousEvent = new DateTime();
		for (int index=1; index < eventsOnSameDay.size(); index++) {
			dateOfThePreviousEvent = eventsOnSameDay.get(index-1).getTimeSlot().getEndTime();
			dateOfTheEvent = eventsOnSameDay.get(index).getTimeSlot().getStartTime();
			dateOfTheEventMinusDuration = dateOfTheEvent.minusMinutes(duration);
			
			if (dateOfTheEventMinusDuration.isAfter(dateOfThePreviousEvent) ||dateOfTheEventMinusDuration.isEqual(dateOfThePreviousEvent)) {
				freeTimeSlots.add(new TimeSlot(dateOfTheEventMinusDuration, dateOfTheEvent));
			}
		}
		dateOfThePreviousEvent = eventsOnSameDay.get(eventsOnSameDay.size()-1).getTimeSlot().getEndTime();
		DateTime dateOfTheEventPlusDuration = dateOfThePreviousEvent.plusMinutes(duration);
		if (dateOfTheEventPlusDuration.isBefore(day.getEndTime()) || dateOfTheEventPlusDuration.isEqual(day.getEndTime())) {
			freeTimeSlots.add(new TimeSlot(dateOfThePreviousEvent, dateOfTheEventPlusDuration));
		}		
		return freeTimeSlots;
	}

	private List<ScheduledEvent> getAllEventsThatAreOnSameDay(Day day)
	{
		List<ScheduledEvent> eventsOnSameDay = new LinkedList<ScheduledEvent>();
		DateTime dateOfTheEvent = new DateTime();
		DateTime dateOfTheDay = new DateTime();
		for (ScheduledEvent event : this.events) {
			dateOfTheEvent = event.getTimeSlot().getStartTime();
			dateOfTheDay = day.getStartTime();
			if ((dateOfTheEvent.dayOfMonth().get() == dateOfTheDay.dayOfMonth().get()) && (dateOfTheEvent.monthOfYear().get()== dateOfTheDay.monthOfYear().get()) && (dateOfTheEvent.year().get()==dateOfTheDay.year().get())) {
				eventsOnSameDay.add(event);
			}
		}
		return eventsOnSameDay;
	}

	/**
	 * Getter for the events.
	 * @return the events
	 */
	public LinkedList<ScheduledEvent> getEvents()
	{
		return (LinkedList<ScheduledEvent>)this.events;
	}

	/**
	 * Getter for the IHM.
	 * @return the myIHM
	 */
	public UserIHM getMyIHM()
	{
		return this.myIHM;
	}
	
}
