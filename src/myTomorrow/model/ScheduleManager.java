package myTomorrow.model;

import java.util.LinkedList;
import java.util.List;

import myTomorrow.view.UserIHM;
import org.joda.time.DateTime;

/**
 * Application. 
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class ScheduleManager
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
	 * @param ihm 
	 * 		chosen ihm, console or GUI
	 */
	public ScheduleManager(UserIHM ihm){
		this.events = new LinkedList<ScheduledEvent>();
		this.myIHM = ihm;
	}
	
	/**
	 * Add an appointment.
	 */
	public void addAppointment(){
		Appointment appointment = this.inputAppointment();	
		appointment.setTimeSlot(this.searchTimeSlot(this.myIHM.askAvailableDay(),this.myIHM.askDurationOfEvent()));
		//TODO Following methods of the case addAppointment.
		this.events.add(appointment);
	}
	
	/**
	 * Create a new appointment with no defined time slot.
	 * @return an appointment
	 */
	public Appointment inputAppointment() {
		TimeSlot timeSlot = new TimeSlot(null, null);
		Person person = this.myIHM.askPersonInformations();
		return new Appointment(person, timeSlot);
	}
	
	/**
	 * Research of a free time slot.
	 * @param day
	 * @param duration
	 * @return a TimeSlot
	 */
	private TimeSlot searchTimeSlot(Day day, int duration) {
		List<ScheduledEvent> eventsOnSameDay = this.getAllEventsThatAreOnSameMorning(day);
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		
		if (eventsOnSameDay.isEmpty()) {
			//Case where all the morning is free.
			return new TimeSlot(day.getStartTime(), day.getStartTime().plusMinutes(duration));
		}
		freeTimeSlots = this.getAllFreeTimeSlotsInTheMorning(eventsOnSameDay, duration, day);
		
		if (freeTimeSlots.isEmpty()) {
			eventsOnSameDay = this.getAllEventsThatAreOnSameAfternoon(day);
			
			if (!eventsOnSameDay.isEmpty()) {
				freeTimeSlots = this.getAllFreeTimeSlotsInTheAfternoon(eventsOnSameDay, duration, day);
				if (!freeTimeSlots.isEmpty()) {
					//Case where there are place only in the afternoon.
					return freeTimeSlots.get(0);
				}
				//Case where the isn't free place in all day.
			}
			//Case where all the afternoon is free.
			DateTime date = new DateTime(day.getStartTime().getYear(), day.getStartTime().getMonthOfYear(), day.getStartTime().getDayOfMonth(), 14, 0);
			return new TimeSlot(date, date.plusMinutes(duration));
		}
		//Case where there are place in the morning.
		return freeTimeSlots.get(0);	
	}
	
	/**
	 * Research of free time slots in the morning.
	 * @param eventsOnSameDay
	 * @param duration
	 * @param day
	 * @return a list of time slots
	 */
	private List<TimeSlot> getAllFreeTimeSlotsInTheMorning(
			List<ScheduledEvent> eventsOnSameDay, int duration, Day day)
	{
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		//Case of the first event in the list.
		DateTime dateOfTheEvent = eventsOnSameDay.get(0).getTimeSlot().getStartTime();
		DateTime dateOfTheEventMinusDuration = dateOfTheEvent.minusMinutes(duration);
		
		if (dateOfTheEventMinusDuration.isAfter(day.getStartTime()) || dateOfTheEventMinusDuration.isEqual(day.getStartTime())) {
				freeTimeSlots.add(new TimeSlot(dateOfTheEventMinusDuration, dateOfTheEvent));
		}
		//Case of the others.
		DateTime dateOfThePreviousEvent = new DateTime();
		for (int index=1; index < eventsOnSameDay.size(); index++) {
			dateOfThePreviousEvent = eventsOnSameDay.get(index-1).getTimeSlot().getEndTime();
			dateOfTheEvent = eventsOnSameDay.get(index).getTimeSlot().getStartTime();
			dateOfTheEventMinusDuration = dateOfTheEvent.minusMinutes(duration);
			
			if (dateOfTheEventMinusDuration.isAfter(dateOfThePreviousEvent) ||dateOfTheEventMinusDuration.isEqual(dateOfThePreviousEvent)) {
				freeTimeSlots.add(new TimeSlot(dateOfTheEventMinusDuration, dateOfTheEvent));
			}
		}
		//Case of the last.
		dateOfThePreviousEvent = eventsOnSameDay.get(eventsOnSameDay.size()-1).getTimeSlot().getEndTime();
		DateTime dateOfTheEventPlusDuration = dateOfThePreviousEvent.plusMinutes(duration);
		DateTime date = new DateTime(day.getStartTime().getYear(), day.getStartTime().getMonthOfYear(), day.getStartTime().getDayOfMonth(), 12, 0);
		if (dateOfTheEventPlusDuration.isBefore(date) || dateOfTheEventPlusDuration.isEqual(date)) {
			freeTimeSlots.add(new TimeSlot(dateOfThePreviousEvent, dateOfTheEventPlusDuration));
		}		
		return freeTimeSlots;
	}

	private List<TimeSlot> getAllFreeTimeSlotsInTheAfternoon(
			List<ScheduledEvent> eventsOnSameDay, int duration, Day day)
	{
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		//Case of the first event in the afternoon.
		DateTime dateOfTheEvent = eventsOnSameDay.get(0).getTimeSlot().getStartTime();
		DateTime dateOfTheEventMinusDuration = dateOfTheEvent.minusMinutes(duration);
		DateTime date = new DateTime(day.getStartTime().getYear(), day.getStartTime().getMonthOfYear(), day.getStartTime().getDayOfMonth(), 14, 0);
		if (dateOfTheEventMinusDuration.isAfter(date) || dateOfTheEventMinusDuration.isEqual(date)) {
				freeTimeSlots.add(new TimeSlot(dateOfTheEventMinusDuration, dateOfTheEvent));
		}
		//Case of others.
		DateTime dateOfThePreviousEvent = new DateTime();
		for (int index=1; index < eventsOnSameDay.size(); index++) {
			dateOfThePreviousEvent = eventsOnSameDay.get(index-1).getTimeSlot().getEndTime();
			dateOfTheEvent = eventsOnSameDay.get(index).getTimeSlot().getStartTime();
			dateOfTheEventMinusDuration = dateOfTheEvent.minusMinutes(duration);
			
			if (dateOfTheEventMinusDuration.isAfter(dateOfThePreviousEvent) ||dateOfTheEventMinusDuration.isEqual(dateOfThePreviousEvent)) {
				freeTimeSlots.add(new TimeSlot(dateOfTheEventMinusDuration, dateOfTheEvent));
			}
		}
		//Case of the last.
		dateOfThePreviousEvent = eventsOnSameDay.get(eventsOnSameDay.size()-1).getTimeSlot().getEndTime();
		DateTime dateOfTheEventPlusDuration = dateOfThePreviousEvent.plusMinutes(duration);
		if (dateOfTheEventPlusDuration.isBefore(day.getEndTime()) || dateOfTheEventPlusDuration.isEqual(day.getEndTime())) {
			freeTimeSlots.add(new TimeSlot(dateOfThePreviousEvent, dateOfTheEventPlusDuration));
		}		
		return freeTimeSlots;
	}

	private List<ScheduledEvent> getAllEventsThatAreOnSameMorning(Day day)
	{
		List<ScheduledEvent> eventsOnSameDay = new LinkedList<ScheduledEvent>();
		DateTime dateOfTheEvent = new DateTime();
		DateTime dateOfTheDay = new DateTime();
		for (ScheduledEvent event : this.events) {
			dateOfTheEvent = event.getTimeSlot().getStartTime();
			dateOfTheDay = day.getStartTime();
			if ((dateOfTheEvent.dayOfMonth().get() == dateOfTheDay.dayOfMonth().get()) && (dateOfTheEvent.monthOfYear().get()== dateOfTheDay.monthOfYear().get()) && (dateOfTheEvent.year().get()==dateOfTheDay.year().get()) && 
					(dateOfTheEvent.hourOfDay().get()<12)) {
				eventsOnSameDay.add(event);
			}
		}
		return eventsOnSameDay;
	}

	private List<ScheduledEvent> getAllEventsThatAreOnSameAfternoon(Day day)
	{
		List<ScheduledEvent> eventsOnSameDay = new LinkedList<ScheduledEvent>();
		DateTime dateOfTheEvent = new DateTime();
		DateTime dateOfTheDay = new DateTime();
		for (ScheduledEvent event : this.events) {
			dateOfTheEvent = event.getTimeSlot().getStartTime();
			dateOfTheDay = day.getStartTime();
			if ((dateOfTheEvent.dayOfMonth().get() == dateOfTheDay.dayOfMonth().get()) && (dateOfTheEvent.monthOfYear().get()== dateOfTheDay.monthOfYear().get()) && (dateOfTheEvent.year().get()==dateOfTheDay.year().get()) && 
					(dateOfTheEvent.hourOfDay().get()>=14)) {
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
