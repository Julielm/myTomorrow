package myTomorrow;

import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.joda.time.DateTime;

import myTomorrow.model.ScheduleManager;
import myTomorrow.view.UserIHM;
import myTomorrow.view.console.UserIHMConsole;
import myTomorrow.view.gui.MainWindow;

public class Controller
{
	public Controller() {
		UserIHM gui = new MainWindow();
		ScheduleManager application = new ScheduleManager(gui);
		SwingUtilities.invokeLater((Runnable) gui);
		List<String> days =new LinkedList<String>();
		days.add("Lundi");
		days.add("Mardi");
		days.add("Mercredi");
		days.add("Jeudi");
		days.add("Vendredi");
		days.add("Samedi");
		days.add("Dimanche");
		gui.initCalendar(application.getEvents(),days, application);
		
	}
	
}
