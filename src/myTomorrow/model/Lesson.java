package myTomorrow.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Lesson for a predefined number of persons.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Lesson extends ScheduledEvent
{
	/** Maximum number of persons in the lesson by default. */
	public static final int DEFAULT_MAX_PERS_NB=3;
	/** Number of persons in the lesson by default. */
	public static final int DEFAULT_PERS_NB=0;
	/** Title of the lesson.*/
	private final String title;
	/** Maximum number of persons. */
	private final int maxPersNb;
	/** Number of persons. */
	private int persNb;
	/** List of the persons in the lesson. */
	private final List<Person> personsList;
	
	/**
	 * Constructor of a Lesson.
	 * @param title
	 * @param duration 
	 * @param timeSlot 
	 */
	public Lesson(String title, TimeSlot timeSlot)
	{
		super(timeSlot);
		this.title=title;
		this.maxPersNb=DEFAULT_MAX_PERS_NB;
		this.persNb=DEFAULT_PERS_NB;
		this.personsList=new LinkedList<Person>();
	}

	/**
	 * Getter for the title.
	 * @return the title
	 */
	public String getTitle()
	{
		return this.title;
	}


	/**
	 * Getter for the maximum number of persons.
	 * @return the maxPersNb
	 */
	public int getMaxPersNb()
	{
		return this.maxPersNb;
	}

	/**
	 * Getter for the number of persons.
	 * @return the persNb
	 */
	public int getPersNb()
	{
		return this.persNb;
	}


	/**
	 * Getter for the list of persons.
	 * @return the personList
	 */
	public LinkedList<Person> getPersonList()
	{
		return (LinkedList<Person>)this.personsList;
	}

	public boolean hasTheSameTitle(String title)
	{
		if (this.title.equalsIgnoreCase(title))
			return true;
		return false;
	}

	public boolean hasFreePlace()
	{
		if (this.persNb<DEFAULT_MAX_PERS_NB) {
			return true;
		}
		return false;
	}

	public void setPersonList(Person person)
	{
		this.personsList.add(person);
		this.persNb++;
	}
	
	public int personIndex(Person person) {
		int index = 0;
		Person currentPerson = this.personsList.get(index);
		while (!currentPerson.equals(person) && index+1<this.personsList.size()) {
			index++;
			currentPerson = this.personsList.get(index);
		}
		if (currentPerson.equals(person))
			return index;
		return -1;
	}

	public void remove(int personIndex)
	{
		this.personsList.remove(personIndex);
		this.persNb--;
		
	}
	
	public String displayPersons() {
		StringBuilder str = new StringBuilder();
		str.append("<html> <body>");
		for (Person person : this.personsList) {
			str.append(person+ "<br>");
		}
		str.append("</body></html>");
		return str.toString();
	}
}
