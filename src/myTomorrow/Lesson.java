package myTomorrow;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Lesson for a predefined number of persons.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Lesson extends TimeSlot
{
	/** Maximum number of persons in the lesson by default. */
	public static final int DEFAULT_MAX_PERS_NB=3;
	/** Number of persons in the lesson by default. */
	public static final int DEFAULT_PERS_NB=0;
	/** Title of the lesson.*/
	private final String title;
	/** Duration of the lesson in minutes.*/
	private final int duration;
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
	 * @param startTime
	 * @param endTime
	 */
	public Lesson(String title, int duration, Calendar startTime, Calendar endTime)
	{
		super(startTime, endTime);
		this.title=title;
		this.duration=duration;
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
	 * Getter for the duration.
	 * @return the duration
	 */
	public int getDuration()
	{
		return this.duration;
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
}
