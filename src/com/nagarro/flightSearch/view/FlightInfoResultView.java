package com.nagarro.flightSearch.view;

import java.util.Date;

import com.nagarro.flightSearch.model.FlightInfo;
import com.nagarro.flightSearch.resources.Constants;

public class FlightInfoResultView extends FlightInfo
{
	private String airlineName;
	private String validTillDate;

	public FlightInfoResultView()
	{
	}

	public FlightInfoResultView(FlightInfo obj)
	{
		this.setFlightNo(obj.getFlightNo());
		this.setArrLOC(obj.getArrLOC());
		this.setDepLOC(obj.getDepLOC());
		this.setFlightDuration(obj.getFlightDuration());
		this.setFlightTime(obj.getFlightTime());
		this.setFare(obj.getFare());
		this.setClassType(obj.getClassType());
		this.setSeatAvailability(obj.getSeatAvailability());
		this.setValidTill(obj.getValidTill());
		this.setValidTillDate(obj.getValidTill());
		this.airlineName = "";
	}

	public String getAirlineName()
	{
		return airlineName;
	}

	public void setAirlineName(String airlineName)
	{
		this.airlineName = airlineName;
	}

	public String getValidTillDate()
	{
		return validTillDate;
	}

	public void setValidTillDate(String validTillDate)
	{
		this.validTillDate = validTillDate;
	}
	
	public void setValidTillDate(Date date)
	{
		validTillDate = Constants.DATE_FT.format(date);
		System.out.println("%%validTill = "+validTillDate);
	}
}
