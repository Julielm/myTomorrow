package myTomorrow.view.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import myTomorrow.model.Appointment;
import myTomorrow.model.Lesson;
import myTomorrow.model.ScheduledEvent;

public class EventListener implements ActionListener
{
	private ScheduledEvent event;

	public EventListener(ScheduledEvent event)
	{
		this.event=event;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(event instanceof Appointment) {
			JDialog informations = new AppointmentDialog(event);
			informations.setVisible(true);
		}
		if(event instanceof Lesson) {
			JDialog informations = new LessonDialog(event);
			informations.setVisible(true);
		}

	}

}
