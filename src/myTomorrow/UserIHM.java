package myTomorrow;

import java.util.Scanner;

import org.joda.time.DateTime;

/**
 * User's IHM.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class UserIHM
{
	Scanner scanner = new Scanner(System.in);
	
	public Appointment inputAppointment(Day day, int duration) {
		String name = "";
		String firstName = "";
		TimeSlot timeSlot=new TimeSlot(null, null);
		day = askAppointmentInformations(name, firstName, duration);
		Person person = new Person(name, firstName);
		return new Appointment(person, timeSlot);
	}
	
	public Day askAppointmentInformations(String name, String firstName, int duration) {
		System.out.println("Entrez le nom de la personne");
		String str = scanner.nextLine();
		name = str;
		System.out.println("Entrez le prénom de la personne");
		str = scanner.nextLine();
		firstName = str;
		System.out.println("Entrez le jour disponible de la personne");
		System.out.println("Entrez la date : jour -->");
		int date = scanner.nextInt();
		System.out.println("mois -->");
		int month = scanner.nextInt();
		System.out.println("année -->");
		int year = scanner.nextInt();
		System.out.println("Entrez la duree du rendez-vous en minutes");
		duration = scanner.nextInt();
		return new Day(date, month, year);
	}
	
	public DateTime askTime() {
		DateTime timeToReturn= new DateTime();
		System.out.println("Entrez l'heure  --> ");
		int hours = scanner.nextInt();
		System.out.println("Entrez les minutes -->");
		int minutes = scanner.nextInt();
		return timeToReturn.withTime(hours, minutes, 0, 0);
	}
}
