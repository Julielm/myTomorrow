package myTomorrow;

import sun.util.calendar.BaseCalendar.Date;

public class Period
{
	/** Start date of the period. */
	private final Date startDate;
	/** End date of the period. */
	private final Date endDate;
	
	/**
	 * Constructor of a Period.
	 * @param startDate
	 * @param endDate
	 */
	public Period(Date startDate, Date endDate)
	{
		this.startDate=startDate;
		this.endDate=endDate;
	}

	/**
	 * Getter for the start date.
	 * @return the startDate
	 */
	public Date getStartDate()
	{
		return this.startDate;
	}

	/**
	 * Getter for the end Date.
	 * @return the endDate
	 */
	public Date getEndDate()
	{
		return this.endDate;
	}
}
