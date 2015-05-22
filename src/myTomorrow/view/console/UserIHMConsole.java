package myTomorrow.view.console;

import java.util.Scanner;

import myTomorrow.model.Day;
import myTomorrow.model.Person;
import myTomorrow.model.TimeSlot;
import myTomorrow.view.UserIHM;

/**
 * User's IHM.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class UserIHMConsole implements UserIHM
{
	Scanner scanner = new Scanner(System.in);
	
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

	@Override
	public boolean suggestTimeSlot(TimeSlot timeSlot)
	{
		System.out.println(timeSlot.toString());
		System.out.println("Ce créneau vous convient-il ?");
		System.out.println("1: Oui");
		System.out.println("2: Non");
		int answer = scanner.nextInt();
		if (answer==1){
			return true;
		}
		return  false;
	}

	@Override
	public void freeTimeSlotIsEmpty()
	{
		System.out.println("Il n'y a pas de créneaux libres ce jour.");
		
	}

	@Override
	public void userDontWantTheseFreeTimeSlots()
	{
		System.out.println("Il n'y a plus de créneaux.");
		
	}

	@Override
	public String askTitleOfTheLesson()
	{
		System.out.println("Entrez l'intitulé du cours -->");
		return scanner.nextLine();
	}
}
