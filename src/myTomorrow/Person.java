package myTomorrow;

/**
 * Person of a lesson or a appointment.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */

public class Person
{
	/** Number of lessons by default.*/
	public final static int DEFAULT_LESSONS_NUMBER=0;
	/** Person's name.*/
	private final String name;
	/** Person's first name.*/
	private final String firstName;
	private final Period period;
	/** Number of lessons of the person.*/
	private final int lessonsNumber;
	
	/** 
	 * Constructor of a person for an appointment.
	 * @param name
	 * @param firstName
	 */
	public Person(String name, String firstName, Period period)
	{
		this.name=name;
		this.firstName=firstName;
		this.period=period;
		this.lessonsNumber=DEFAULT_LESSONS_NUMBER;
		
	}
	
	/**
	 * Constructor of a person for a lesson.
	 * @param name
	 * @param firstName
	 * @param lessonNumber
	 */
	public Person(String name, String firstName, Period period, int lessonsNumber)
	{
		//TODO exception for lessonsNumber<0
		this.name=name;
		this.firstName=firstName;
		this.period=period;
		this.lessonsNumber=lessonsNumber;
		
	}
	
	/**
	 * Getter for the name.
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Getter for the firstName.
	 * @return firstName
	 */
	public String getFirstName()
	{
		return this.firstName;
	}

	/**
	 * Getter for the number of lessons.
	 * @return lessonsNumber
	 */
	public int getLessonNumber()
	{
		return this.lessonsNumber;
	}

	/**
	 * Getter for the period.
	 * @return the period
	 */
	public Period getPeriod()
	{
		return this.period;
	}
	
}
