package myTomorrow;

import java.util.Calendar;

/**
 * Period when a person can take appointment or lessons.
 * 
 * @author myTomorrowProject
 * @version 1.0.0
 */
public class Period
{
	/** Start date of the period. */
	private Calendar startDate;
	/** End date of the period. */
	private Calendar endDate;
	
	/**
	 * Constructor of a Period.
	 * @param startDate
	 * @param endDate
	 */
	public Period(Calendar startDate, Calendar endDate)
	{
		this.startDate=startDate;
		this.endDate=endDate;
	}

	/**
	 * Getter for the start date.
	 * @return the startDate
	 */
	public Calendar getStartDate()
	{
		return this.startDate;
	}

	/**
	 * Getter for the end Date.
	 * @return the endDate
	 */
	public Calendar getEndDate()
	{
		return this.endDate;
	}
}
