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
	private final List<TimeSlot> calendar;
	
	/** 
	 * User's IHM.
	 */
	private final UserIHM myIHM;
	
	/**
	 * Constructor of an application.
	 */
	public Application(){
		this.calendar = new LinkedList<TimeSlot>();
		this.myIHM = new UserIHM();
	}
	
	//TODO JavaDoc and continue the method.
	public void addAppointment(){
		Appointment appointment = this.myIHM.inputAppointment();	
	}
	
	//TODO JavaDoc.
	private TimeSlot searchTimeSlot(Period period, int duration) {
		
		// N.B. replace period by a single day timeslot
		
		// search in calendar for the list of all timeslots that are on the same day than the specified timeslot
		// List<TimeSlots> timeSlotsOnSameDay = this.getAllTimeSlotsThatAreOnSameDay(timeSlot);
		
		// if result list is empty -> easy
		// if not
			// compute the list of durations between timeslots in result
			// search for a duration at least equal to the specified duration in list
			// if it exists -> easy
			// if not -> easy
		
		
		TimeSlot timeSlot = new TimeSlot(null, null);
		DateTime dateTime = new DateTime();
		while (period.getStartDate().isBefore(period.getEndDate()) && timeSlot.getStartTime() == null) {
			int index = 0;
			while (this.calendar.get(index).getStartTime().isBefore(period.getStartDate()) && index < this.calendar.size()) {
				index++;
			}
			if (index == this.calendar.size()) {
				if (this.calendar.get(index-1).getEndTime().isBefore(period.getStartDate()) || this.calendar.get(index-1).getEndTime().isEqual(period.getStartDate())){
					try {
						timeSlot.setTime(timeSlot.getStartTime(), period.getStartDate());
					}
					catch (SaturdayException e){
						period.getStartDate().plusDays(2);
						timeSlot.getStartTime().withDate(period.getStartDate().getYear(), period.getStartDate().getMonthOfYear(), period.getStartDate().getDayOfMonth());
					}
					catch (SundayException f) {
						period.getStartDate().plusDays(1);
						timeSlot.getStartTime().withDate(period.getStartDate().getYear(), period.getStartDate().getMonthOfYear(), period.getStartDate().getDayOfMonth());
					}
					finally {
						DateTime time = this.myIHM.askTime();
						timeSlot.getStartTime().withTime(time.getHourOfDay(), time.getMinuteOfHour(), 0, 0);
						time = timeSlot.getStartTime().plusMinutes(duration);
						timeSlot.getEndTime().withTime(time.getHourOfDay(), time.getMinuteOfHour(), 0, 0);
					}
				}
				
				else{
					try {
						timeSlot.setTime(timeSlot.getStartTime(), this.calendar.get(index-1).getEndTime());
					}
					catch (SaturdayException e){
						this.calendar.get(index-1).getEndTime().plusDays(2);
						timeSlot.getStartTime().withDate(this.calendar.get(index-1).getEndTime().getYear(), this.calendar.get(index-1).getEndTime().getMonthOfYear(), this.calendar.get(index-1).getEndTime().getDayOfMonth());
					}
					catch (SundayException f) {
						this.calendar.get(index-1).getEndTime().plusDays(1);
						timeSlot.getStartTime().withDate(this.calendar.get(index-1).getEndTime().getYear(), this.calendar.get(index-1).getEndTime().getMonthOfYear(), this.calendar.get(index-1).getEndTime().getDayOfMonth());
					}
					finally {
						DateTime time = this.myIHM.askTime();
						timeSlot.getStartTime().withTime(time.getHourOfDay(), time.getMinuteOfHour(), 0, 0);
						time = timeSlot.getStartTime().plusMinutes(duration);
						timeSlot.getEndTime().withTime(time.getHourOfDay(), time.getMinuteOfHour(), 0, 0);
					}
				}
				
			//TODO Continue the method and optimize it and review the algorithm.			
			}
			else{
				dateTime = this.calendar.get(index-1).getEndTime();
				if (dateTime.isBefore(period.getStartDate())){
					dateTime = this.calendar.get(index).getStartTime();
					DateTime datePlusDuration = period.getStartDate().plusMinutes(duration);
					if (datePlusDuration.isBefore(dateTime) || datePlusDuration.isEqual(dateTime)){
						timeSlot.getStartTime().withDate(period.getStartDate().getYear(), period.getStartDate().getMonthOfYear(), period.getStartDate().getDayOfYear());
						//TODO implementation of hours and timeSlot.getEndTime() and manage hours when they are between 18:00 and 8:00
					}
					
					else{
						
					}
				}
				else{
					
				}
			}
			
			
			
		}
		return timeSlot;
	}
	
	/**
	 * Getter for the calendar.
	 * @return the calendar
	 */
	public LinkedList<TimeSlot> getCalendar()
	{
		return (LinkedList<TimeSlot>)this.calendar;
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
