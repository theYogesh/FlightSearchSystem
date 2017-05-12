package com.nagarro.flightSearch.model;

import java.util.HashSet;
import java.util.Set;

public class Airline
{
	private Set<FlightInfo>	flights	= new HashSet<FlightInfo>();
	private int				id;
	private String			airlineName;

	public Set<FlightInfo> getFlights()
	{
		return flights;
	}

	public void setFlights(Set<FlightInfo> flightDataSet)
	{
		for (FlightInfo flight : flightDataSet)
		{
			flight.setAirline(this);
		}
		this.flights = flightDataSet;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getAirlineName()
	{
		return airlineName;
	}

	public void setAirlineName(String airlineName)
	{
		this.airlineName = airlineName;
	}

	public void addFlight(FlightInfo flight)
	{
		flight.setAirline(this);
		this.flights.add(flight);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Airline [flights=" + flights + ", id=" + id + ", airlineName=" + airlineName + "]";
	}
}
