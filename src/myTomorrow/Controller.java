package myTomorrow;

import javax.swing.SwingUtilities;

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
		gui.updateCalendar(application.getEvents());
	}
	
}
