package myTomorrow.view.console;

import java.util.Scanner;

import myTomorrow.model.Appointment;
import myTomorrow.model.Day;
import myTomorrow.model.Person;
import myTomorrow.model.TimeSlot;

/**
 * User's IHM.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class UserIHM
{
	Scanner scanner = new Scanner(System.in);
	/**
	 * Create a new appointment with no defined time slot.
	 * @return an appointment
	 */
	public Appointment inputAppointment() {
		TimeSlot timeSlot = new TimeSlot(null, null);
		Person person = this.askPersonInformations();
		return new Appointment(person, timeSlot);
	}
	
	/**
	 * Person's informations for the event.
	 * @return a Person
	 */
	public Person askPersonInformations() {
		
		System.out.println("Entrez le nom de la personne");
		String name = scanner.nextLine();
		System.out.println("Entrez le prénom de la personne");
		String firstName = scanner.nextLine();
		return new Person(name, firstName);
	}
		
	/**
	 * Ask available day for the event.
	 * @return a day
	 */
	public Day askAvailableDay() {
		
		System.out.println("Entrez le jour disponible de la personne");
		System.out.println("Entrez la date : jour -->");
		int date = scanner.nextInt();
		System.out.println("mois -->");
		int month = scanner.nextInt();
		System.out.println("année -->");
		int year = scanner.nextInt();
		return new Day(date, month, year);
	}
	
	/**
	 * Ask duration of the event.
	 * @return a duration
	 */
	public int askDurationOfEvent() {
		System.out.println("Entrez la duree du rendez-vous en minutes");
		int duration = scanner.nextInt();
		return duration;
	}
}
