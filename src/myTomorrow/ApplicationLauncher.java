package myTomorrow;

import myTomorrow.model.ScheduleManager;
import myTomorrow.model.ScheduledEvent;
import myTomorrow.model.TimeSlot;
import myTomorrow.view.UserIHM;
import myTomorrow.view.console.UserIHMConsole;

import org.joda.time.DateTime;


/**
 * Launcher of the application.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class ApplicationLauncher
{

	/**
	 * Main function, launcher of the application.
	 * @param args
	 */
	public static void main(String[] args)
	{

		// test addition of events and a research of a time slot
		UserIHM console = new UserIHMConsole();
		ScheduleManager application = new ScheduleManager(console);
		
		application.getEvents().add(new ScheduledEvent(new TimeSlot(new DateTime(2015,05,15,8,15), new DateTime(2015,05,15,9,15))));
		application.getEvents().add(new ScheduledEvent(new TimeSlot(new DateTime(2015,05,15,11,0), new DateTime(2015,05,15,11,15))));
		application.getEvents().add(new ScheduledEvent(new TimeSlot(new DateTime(2015,05,15,15,0), new DateTime(2015,05,15,15,30))));
		application.addLesson();
		for (ScheduledEvent events : application.getEvents()) {
			System.out.println("évenement : ");
			System.out.println("Date début : ");
			System.out.println(events.getTimeSlot().getStartTime());
			System.out.println("Date fin : ");
			System.out.println(events.getTimeSlot().getEndTime());
		}
		
	}

}
