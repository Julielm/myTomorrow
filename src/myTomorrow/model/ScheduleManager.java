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
		List<TimeSlot> freeTimeSlot = this.searchTimeSlot(this.myIHM.askAvailableDay(),this.myIHM.askDurationOfEvent());
		if (freeTimeSlot.isEmpty()) {
			this.myIHM.freeTimeSlotIsEmpty();
		}
		else {
			boolean answer = false;
			int index = 0;
			while (answer==false && index < freeTimeSlot.size()) {
				answer=this.myIHM.suggestTimeSlot(freeTimeSlot.get(index));
				index++;
			}
			if (answer){
				appointment.setTimeSlot(freeTimeSlot.get(index-1));
				addEventInASortList(appointment);
			}
			this.myIHM.userDontWantTheseFreeTimeSlots();
		}
		
	}
	
	/**
	 * Add an event in the sort list of events.
	 * @param event
	 */
	private void addEventInASortList(ScheduledEvent event) {
		int index = 0;
		while (this.events.get(index).getTimeSlot().getStartTime().isBefore(event.getTimeSlot().getStartTime()) && index < this.events.size()) {
			index++;
		}
		this.events.add(index, event);
	}
	/**
	 * Create a new appointment with no defined time slot.
	 * @return an appointment
	 */
	private Appointment inputAppointment() {
		TimeSlot timeSlot = new TimeSlot(null, null);
		return new Appointment(this.myIHM.askPersonInformations(), timeSlot);
	}
	
	/**
	 * Research of a free time slot.
	 * @param day
	 * @param duration
	 * @return a TimeSlot
	 */
	private List<TimeSlot> searchTimeSlot(Day day, int duration) {
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		freeTimeSlots.addAll(this.possibleEventsInTheMorning(day, duration));
		freeTimeSlots.addAll(this.possibleEventsInTheAfternoon(day, duration));
		return freeTimeSlots;
	}
	
	
	private List<TimeSlot> possibleEventsInTheMorning(Day day, int duration)
	{
		List<ScheduledEvent> eventsOnSameDay = this.getAllEventsThatAreOnSameMorning(day);
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		if (eventsOnSameDay.isEmpty()) {
			//Case where all the morning is free.
			return this.GetAllTimeSlotsInTheMorning(day, duration);
		}
		freeTimeSlots = this.getAllFreeTimeSlotsInTheMorning(eventsOnSameDay, duration, day);
		return freeTimeSlots;
	}

	private List<TimeSlot> possibleEventsInTheAfternoon(Day day, int duration)
	{
		List<ScheduledEvent> eventsOnSameDay = this.getAllEventsThatAreOnSameAfternoon(day);
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		if (eventsOnSameDay.isEmpty()) {
			//Case where all the afternoon is free.
			return this.GetAllTimeSlotsInTheAfternoon(day, duration);
		}
		freeTimeSlots = this.getAllFreeTimeSlotsInTheAfternoon(eventsOnSameDay, duration, day);
		return freeTimeSlots;
	}
	
	private List<TimeSlot> GetAllTimeSlotsInTheAfternoon(Day day, int duration)
	{
		List<TimeSlot> list =new LinkedList<TimeSlot>();
		DateTime startTime = new DateTime(day.getStartTime().getYear(), day.getStartTime().getMonthOfYear(), day.getStartTime().getDayOfMonth(), 14, 0);
		DateTime endTime = startTime.plusMinutes(duration);
		while (endTime.isBefore(day.getEndTime())|| endTime.isEqual(day.getEndTime())) {
			list.add(new TimeSlot(startTime, endTime));
			startTime=endTime;
			endTime = startTime.plusMinutes(duration);
		}
		return list;
	}

	private List<TimeSlot> GetAllTimeSlotsInTheMorning(Day day, int duration)
	{
		List<TimeSlot> list =new LinkedList<TimeSlot>();
		DateTime startTime = new DateTime(day.getStartTime());
		DateTime endTime = startTime.plusMinutes(duration);
		while (endTime.getHourOfDay()<=12) {
			list.add(new TimeSlot(startTime, endTime));
			startTime=endTime;
			endTime = startTime.plusMinutes(duration);
		}
		return list;
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
	
	public void addLesson() {
		Lesson lesson = this.inputLesson();	
		List<TimeSlot> freeTimeSlot = this.searchTimeSlot(this.myIHM.askAvailableDay(),this.myIHM.askDurationOfEvent());
		if (freeTimeSlot.isEmpty()) {
			this.myIHM.freeTimeSlotIsEmpty();
		}
		else {
			boolean answer = false;
			int index = 0;
			while (answer==false && index < freeTimeSlot.size()) {
				answer=this.myIHM.suggestTimeSlot(freeTimeSlot.get(index));
				index++;
			}
			if (answer){
				lesson.setTimeSlot(freeTimeSlot.get(index-1));
				addEventInASortList(lesson);
			}
			this.myIHM.userDontWantTheseFreeTimeSlots();
		}
	}
	private Lesson inputLesson()
	{
		TimeSlot timeSlot = new TimeSlot(null, null);
		return new Lesson(this.myIHM.askTitleOfTheLesson(),timeSlot);
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
