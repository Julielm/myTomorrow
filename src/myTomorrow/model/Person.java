package myTomorrow.model;

/**
 * Person of a lesson or a appointment.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Person
{
	/** Person's name.*/
	private final String name;
	/** Person's first name.*/
	private final String firstName;
	
	/** 
	 * Constructor of a person.
	 * @param name
	 * @param firstName
	 */
	public Person(String name, String firstName)
	{
		this.name=name;
		this.firstName=firstName;		
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
	
	
}
