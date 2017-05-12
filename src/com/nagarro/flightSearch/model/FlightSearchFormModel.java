package com.nagarro.flightSearch.model;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class FlightSearchFormModel
{
	@NotNull
	@Size(min = 3, max = 3)
	private String	depLoc;

	@NotNull
	@Size(min = 3, max = 3)
	private String	arrLoc;

	@NotNull
	@DateTimeFormat(pattern= "dd-MM-yyyy")
	@Future(message = "The value \"${formatter.format('%1$tY-%1$tm-%1$td', validatedValue)}\" is not in future!")
	private Date	flightDate;

	@NotNull
	@Size(min=1, max=1)
	private String	flightClass;
	
	@NotNull
	private String orderBy;

	public String getDepLoc()
	{
		return depLoc;
	}

	public void setDepLoc(String depLoc)
	{
		this.depLoc = depLoc;
	}

	public String getArrLoc()
	{
		return arrLoc;
	}

	public void setArrLoc(String arrLoc)
	{
		this.arrLoc = arrLoc;
	}

	public Date getFlightDate()
	{
		return flightDate;
	}

	public void setFlightDate(Date flightDate)
	{
		this.flightDate = flightDate;
	}

	public String getFlightClass()
	{
		return flightClass;
	}

	public void setFlightClass(String flightClass)
	{
		this.flightClass = flightClass;
	}

	public String getOrderBy()
	{
		return orderBy;
	}

	public void setOrderBy(String orderBy)
	{
		this.orderBy = orderBy;
	}

	@Override
	public String toString()
	{
		return "FlightSearchFormModel [depLoc=" + depLoc + ", arrLoc=" + arrLoc + ", flightDate=" + flightDate
				+ ", flightClass=" + flightClass + "]";
	}

}
