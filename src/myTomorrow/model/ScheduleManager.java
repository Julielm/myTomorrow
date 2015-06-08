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
		this.events.add(new Appointment(new Person("Bourdon", "Laetitia"), new TimeSlot(new DateTime(2015,6,4,8,00), new DateTime(2015,6,4,9,15))));
		this.events.add(new Lesson("Cours de natation", new TimeSlot(new DateTime(2015,6,4,9,15), new DateTime(2015,6,4,11,15))));
		this.events.add(new Appointment(new Person("Montcarmel", "Elodie"), new TimeSlot(new DateTime(2015,6,4,16,15), new DateTime(2015,6,4,18,00))));
		this.events.add(new Lesson("Cours de Yoga", new TimeSlot(new DateTime(2015,6,6,9,15), new DateTime(2015,6,6,10,15))));
		this.events.add(new Appointment(new Person("Leprunier", "Hugo"), new TimeSlot(new DateTime(2015,6,6,10,15), new DateTime(2015,6,6,11,15))));
		
		this.events.add(new Lesson("Cours de Yoga", new TimeSlot(new DateTime(2015,6,6,11,15), new DateTime(2015,6,6,12,00))));
		this.events.add(new Lesson("Cours de Yoga", new TimeSlot(new DateTime(2015,6,6,14,15), new DateTime(2015,6,6,16,15))));
		this.events.add(new Appointment(new Person("Lacondemine", "Virgil"), new TimeSlot(new DateTime(2015,05,27,11,0), new DateTime(2015,05,27,11,15))));
		this.myIHM = ihm;
	}
	
	/**
	 * Add an appointment.
	 */
	public void addAppointment(){
		Appointment appointment = this.inputAppointment();
		if (appointment!=null) {
			Day availableDay = this.myIHM.askAvailableDay();
			if (availableDay!=null) {
				int duration = this.myIHM.askDurationOfEvent();
				if (duration!=0){
					List<TimeSlot> freeTimeSlot = this.searchTimeSlot(availableDay,duration);
					if (freeTimeSlot.isEmpty()) {
						this.myIHM.freeTimeSlotIsEmpty();
					}
					else {
						TimeSlot answer = this.askAnswer(freeTimeSlot);
						if (answer!=null){
							appointment.setTimeSlot(answer);
							addEventInASortList(appointment);
							this.myIHM.displayFinishedAddition(appointment);
						}
						else {
							this.myIHM.userDontWantTheseFreeTimeSlots();
						}
					}		
				}
			}
			
		}
	}
	
	/**
	 * Add an event in the sort list of events.
	 * @param event
	 */
	private void addEventInASortList(ScheduledEvent event) {
		int index = 0;
		while (index < this.events.size() && this.events.get(index).isBefore(event)) {
			index++;
		}
		this.events.add(index, event);
	}
	/**
	 * Create a new appointment with no defined time slot.
	 * @return an appointment
	 */
	private Appointment inputAppointment() {
		Person person =this.myIHM.askPersonInformations();
		if (person!=null){
			return new Appointment(person, new TimeSlot());
		}
		return null;
		
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
	
	/**
	 * Add a lesson in the list of events.
	 */
	public void addLesson() {
		Lesson lesson = this.inputLesson();	
		if (lesson!= null) {
			Day availableDay = this.myIHM.askAvailableDay();
			if (availableDay!=null) {
				int duration = this.myIHM.askDurationOfEvent();
				if (duration!=0){
					List<TimeSlot> freeTimeSlot = this.searchTimeSlot(availableDay,duration);
					if (freeTimeSlot.isEmpty()) {
						this.myIHM.freeTimeSlotIsEmpty();
					}
					else {
						TimeSlot answer = this.askAnswer(freeTimeSlot);
						if (answer!=null){
							lesson.setTimeSlot(answer);
							addEventInASortList(lesson);
							this.myIHM.displayFinishedAddition(lesson);
						}
						else {
							this.myIHM.userDontWantTheseFreeTimeSlots();
						}
					}
				}	
			}
				
		}
		
	}
	
	private TimeSlot askAnswer(List<TimeSlot> timeSlots)
	{
		boolean answer = false;
		int index = 0;
		while (answer==false && index < timeSlots.size()) {
			answer=this.myIHM.suggestTimeSlot(timeSlots.get(index));
			index++;
		}
		if (answer){
			return timeSlots.get(index-1);
		}
		return null;
	}

	private Lesson inputLesson()
	{
		String title =this.myIHM.askTitleOfTheLesson();
		if (title!=null)
			return new Lesson(title,new TimeSlot());
		return null;
	}
	
	/**
	 * Add a person to a lesson.
	 */
	public void addPersonToLesson() {
		Person person = this.myIHM.askPersonInformations();
		if (person != null) {
			TimeSlot period = this.myIHM.askAvailablePeriod();
			if (period!=null) {
				String title = this.myIHM.askTitleOfTheLesson();
				if (title!=null) {
					List<TimeSlot> lessonsInThePeriod = this.LessonInAList(this.EventsInAPeriod(period), title);
					if (lessonsInThePeriod.isEmpty()) {
						this.myIHM.lessonsInThePeriodIsEmpty();
					}
					else {
						TimeSlot answer = this.askAnswer(lessonsInThePeriod);
						if (answer != null){
							this.addPerson(answer, person);
						}
						else {
							this.myIHM.userDontWantTheseFreeTimeSlots();
						}
					}	
				}	
			}
			
		}
	}
	
	private void addPerson(TimeSlot answer, Person person)
	{
		int index =0;
		while(this.events.get(index).getTimeSlot()!=answer) {
			index++;
		}
		Lesson lesson = (Lesson) this.events.get(index);
		lesson.setPersonList(person);
		this.events.set(index, lesson);
	}

	private List<TimeSlot> LessonInAList(List<ScheduledEvent> eventsInAList, String title)
	{
		List<TimeSlot> lessons = new LinkedList<TimeSlot>();
		for (ScheduledEvent event : eventsInAList) {
			if (event instanceof Lesson) {
				Lesson lesson = (Lesson) event;
				if (lesson.hasTheSameTitle(title)) {
					if (lesson.hasFreePlace()) {
						lessons.add(lesson.getTimeSlot());
					}
				}
			}
		}
		return lessons;
	}
	

	private List<ScheduledEvent> EventsInAPeriod(TimeSlot period)
	{
		List<ScheduledEvent> eventsInThePeriod = new LinkedList<ScheduledEvent>();
		for (ScheduledEvent event : this.events) {
			DateTime dateOfCurrentEvent = event.getTimeSlot().getStartTime();
			if (dateOfCurrentEvent.isBefore(period.getStartTime())) {
				continue;
			}
			if (dateOfCurrentEvent.isAfter(period.getEndTime())) {
				continue;
			}
			eventsInThePeriod.add(event);
		}
		return eventsInThePeriod;
	}
	
	/**
	 * Remove an appointment or a person in a lesson.
	 */
	public void removeAppointmentOrPersonInLesson() {
		int index = this.searchEvent(this.myIHM.inputDateOfEvent());
		if (index>=0) {
			if (this.events.get(index) instanceof Appointment) {
				this.removeAppointment(index);
			}
			else 
				this.removePersonInLesson(index);
		}	
	}

	private void removePersonInLesson(int index)
	{
		Person personToRemove = this.myIHM.askPersonInformations();
		Lesson lesson = (Lesson)this.events.get(index);
		int personIndex = lesson.personIndex(personToRemove);
		if (personIndex >=0) {
			lesson.remove(personIndex);
			if (lesson.getPersNb()==0) {
				this.events.remove(index);
			}
			else 
				this.events.set(index, lesson);
		}
		else 
			this.myIHM.thePersonInputIsNTInLesson();
		
	}

	private void removeAppointment(int index)
	{
		this.events.remove(index);		
	}

	private int searchEvent(DateTime dateOfEvent)
	{
		
		int index = 0;
		DateTime currentEvent = events.get(index).getTimeSlot().getStartTime();
		while(currentEvent!=dateOfEvent && index+1 < events.size()) {
			index++;
			currentEvent = events.get(index).getTimeSlot().getStartTime();
		}
		if (currentEvent==dateOfEvent) {
			return index;
		}
		return -1;
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
