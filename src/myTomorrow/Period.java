package myTomorrow;

import org.joda.time.DateTime;


/**
 * Period when a person can take appointment or lessons.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Period
{
	/** Start date of the period. */
	private DateTime startDate;
	/** End date of the period. */
	private DateTime endDate;
	
	/**
	 * Constructor of a Period.
	 * @param startDate
	 * @param endDate
	 */
	public Period(DateTime startDate, DateTime endDate)
	{
		this.startDate=startDate;
		this.endDate=endDate;
	}

	/**
	 * Getter for the start date.
	 * @return the startDate
	 */
	public DateTime getStartDate()
	{
		return this.startDate;
	}

	/**
	 * Getter for the end Date.
	 * @return the endDate
	 */
	public DateTime getEndDate()
	{
		return this.endDate;
	}
}
