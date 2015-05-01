package myTomorrow;

import java.util.Scanner;

/**
 * User's IHM.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class UserIHM
{
	public static final int DEFAULT_APPOINTMENT_DURATION=0;
	Scanner scanner = new Scanner(System.in);
	
	public Appointment inputAppointment() {
		String name = "";
		String firstName = "";
		Period period = new Period(null, null);
		int duration=DEFAULT_APPOINTMENT_DURATION;
		askAppointmentInformations(name, firstName, period, duration);
		Person person = new Person(name, firstName, period);
		return new Appointment(duration, person, period.getStartDate(), period.getEndDate());
	}
	
	public void askAppointmentInformations(String name, String firstName, Period period, int duration) {
		System.out.println("Entrez le nom de la personne");
		String str = scanner.nextLine();
		name = str;
		System.out.println("Entrez le prénom de la personne");
		str = scanner.nextLine();
		firstName = str;
		System.out.println("Entrez la période de disponibilité de la personne");
		System.out.println("Entrez la date du début de la période: jour -->");
		int date = scanner.nextInt();
		System.out.println("mois -->");
		int month = scanner.nextInt();
		System.out.println("année -->");
		int year = scanner.nextInt();
		period.getStartDate().set(year, month, date);
		System.out.println("Entrez la date de fin de la période: jour -->");
		date = scanner.nextInt();
		System.out.println("mois -->");
		month = scanner.nextInt();
		System.out.println("année -->");
		year = scanner.nextInt();
		period.getEndDate().set(year, month, date);
		System.out.println("Entrez la duree du rendez-vous en minutes");
		duration = scanner.nextInt();
	}
}
