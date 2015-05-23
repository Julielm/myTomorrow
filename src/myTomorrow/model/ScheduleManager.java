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
	public static final boolean MORNING=true;
	public static final boolean AFTERNOON=false;
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
		while (this.events.get(index).isBefore(event) && index < this.events.size()) {
			index++;
		}
		this.events.add(index, event);
	}
	/**
	 * Create a new appointment with no defined time slot.
	 * @return an appointment
	 */
	private Appointment inputAppointment() {
		TimeSlot timeSlot = new TimeSlot();
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
		freeTimeSlots.addAll(this.possibleEvents(day, duration, MORNING));
		freeTimeSlots.addAll(this.possibleEvents(day, duration, AFTERNOON));
		return freeTimeSlots;
	}
	
	
	private List<TimeSlot> possibleEvents(Day day, int duration, boolean isMorning)
	{
		List<ScheduledEvent> eventsOnSameDay = this.getAllEventsThatAreOnSameDay(day, isMorning);
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		if (eventsOnSameDay.isEmpty()) {
			//Case where all the morning is free.
			return this.GetAllTimeSlotsInTheDay(day, duration, isMorning);
		}
		freeTimeSlots = this.getAllFreeTimeSlotsInTheDay(eventsOnSameDay, duration, day, isMorning);
		return freeTimeSlots;
	}

	
	private List<TimeSlot> GetAllTimeSlotsInTheDay(Day day, int duration, boolean isMorning)
	{
		List<TimeSlot> list =new LinkedList<TimeSlot>();
		int hour=14;
		DateTime startTime = new DateTime(day.getStartTime().getYear(), day.getStartTime().getMonthOfYear(), day.getStartTime().getDayOfMonth(), hour, 0);
		if (isMorning) {
			hour=12;
			startTime= day.getStartTime();
		}
		DateTime endTime = startTime.plusMinutes(duration);
		DateTime endOfHalfDay = day.getEndTime();
		if (isMorning) {
			endOfHalfDay = new DateTime(day.getStartTime().getYear(), day.getStartTime().getMonthOfYear(), day.getStartTime().getDayOfMonth(), hour, 0);
		}
		while (endTime.isBefore(endOfHalfDay)|| endTime.isEqual(endOfHalfDay)) {
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
	private List<TimeSlot> getAllFreeTimeSlotsInTheDay(List<ScheduledEvent> eventsOnSameDay, int duration, Day day, boolean isMorning)
	{
		List<TimeSlot> freeTimeSlots = new LinkedList<TimeSlot>();
		//Case of the first event in the list.
		DateTime dateOfTheEvent = eventsOnSameDay.get(0).getTimeSlot().getStartTime();
		DateTime dateOfTheEventMinusDuration = dateOfTheEvent.minusMinutes(duration);
		
		DateTime date = new DateTime(day.getStartTime().getYear(), day.getStartTime().getMonthOfYear(), day.getStartTime().getDayOfMonth(), 14, 0);
		if (isMorning) {
			date=day.getStartTime();
		}
		
		if (dateOfTheEventMinusDuration.isAfter(date) || dateOfTheEventMinusDuration.isEqual(date)) {
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
		date = day.getEndTime();
		if (isMorning) {
			date = new DateTime(day.getStartTime().getYear(), day.getStartTime().getMonthOfYear(), day.getStartTime().getDayOfMonth(), 12, 0);
		}
		if (dateOfTheEventPlusDuration.isBefore(date) || dateOfTheEventPlusDuration.isEqual(date)) {
			freeTimeSlots.add(new TimeSlot(dateOfThePreviousEvent, dateOfTheEventPlusDuration));
		}		
		return freeTimeSlots;
	}

	private List<ScheduledEvent> getAllEventsThatAreOnSameDay(Day day, boolean isMorning)
	{
		List<ScheduledEvent> eventsOnSameDay = new LinkedList<ScheduledEvent>();
		DateTime dateOfTheEvent = new DateTime();
		DateTime dateOfTheDay = new DateTime();
		for (ScheduledEvent event : this.events) {
			dateOfTheEvent = event.getTimeSlot().getStartTime();
			dateOfTheDay = day.getStartTime();
			if (isMorning) {
				if ((dateOfTheEvent.dayOfMonth().get() == dateOfTheDay.dayOfMonth().get()) && (dateOfTheEvent.monthOfYear().get()== dateOfTheDay.monthOfYear().get()) && (dateOfTheEvent.year().get()==dateOfTheDay.year().get()) && 
						(dateOfTheEvent.hourOfDay().get()<12)) {
					eventsOnSameDay.add(event);
				}
			}
			else {
				if ((dateOfTheEvent.dayOfMonth().get() == dateOfTheDay.dayOfMonth().get()) && (dateOfTheEvent.monthOfYear().get()== dateOfTheDay.monthOfYear().get()) && (dateOfTheEvent.year().get()==dateOfTheDay.year().get()) && 
						(dateOfTheEvent.hourOfDay().get()>=14)) {
					eventsOnSameDay.add(event);
				}
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
