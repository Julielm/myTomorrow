package myTomorrow.view.gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import myTomorrow.model.ScheduledEvent;

public class GraphicalEvent extends JButton
{
	public static final int EVENT_WIDTH=132;
	
	private ScheduledEvent associatedEvent;
	
	public GraphicalEvent(ScheduledEvent event){
		this.associatedEvent=event;
		
		DateTime startTime = this.associatedEvent.getTimeSlot().getStartTime();
		int dayOfWeek = startTime.getDayOfWeek();
		int hours = startTime.getHourOfDay();
		int minutes = startTime.getMinuteOfHour();
		
		Duration durationOfEvent = new Duration(startTime, this.associatedEvent.getTimeSlot().getEndTime());

		this.setBounds(58+((dayOfWeek-1)*133),38+((hours-8)*61)+minutes,EVENT_WIDTH, durationOfEvent.toStandardMinutes().getMinutes());	
		
	}
}
