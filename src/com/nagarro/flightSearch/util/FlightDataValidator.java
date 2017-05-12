package com.nagarro.flightSearch.util;

import java.text.ParseException;
import java.util.Date;

import com.nagarro.flightSearch.exception.FlightInvalidInputException;
import com.nagarro.flightSearch.resources.Constants;

public class FlightDataValidator extends Exception
{
	private static final long serialVersionUID = 1L;

	public static void flightNoValidator(String flightNo)
	{
		if ( flightNo.equals("") )
		{
			throw new FlightInvalidInputException("Invalid Flight No : FlightNo can not be empty");
		}
	}

	public static void depLocValidator(String depLOC)
	{
		if ( depLOC.length() != 3 )
		{
			throw new FlightInvalidInputException("Invalid Departure Location Code: Not 3 length code");
		}
	}

	public static void arrLocValidator(String arrLOC)
	{
		if ( arrLOC.length() != 3 )
		{
			throw new FlightInvalidInputException("Invalid Arrival Location Code: Not 3 length code");
		}
	}

	public static void dateValidator(String checkDate)
	{
		Date tempDt = null;
		try
		{
			tempDt = Constants.DATE_FT.parse(checkDate);
		}
		catch (ParseException e)
		{
			throw new FlightInvalidInputException("Invalid Date: Invalid format");
		}

		if ( tempDt.compareTo(new Date()) < 0 )
		{
			throw new FlightInvalidInputException("Invalid Date: Date is passed");
		}
	}

	public static void flightTimeValidator(String fTime)
	{
		if ( fTime.length() != 4 )
		{
			throw new FlightInvalidInputException("Invalid Flight Time: length is not 4");
		}
		else if ( !fTime.matches("[0-9]+") )
		{
			throw new FlightInvalidInputException("Invalid Flight Time: Time can not contain letter");
		}
		int hr = Integer.parseInt(fTime.substring(0, 1));
		int min = Integer.parseInt(fTime.substring(2, 3));

		if ( !((hr >= 0 && hr <= 24) && (min >= 0) && (min <= 59)) )
			throw new FlightInvalidInputException("Invalid Flight Time: Invalid Time Cycle");
	}

	public static void flightDurationValidator(float fDur)
	{
		if ( fDur == 0 )
		{
			throw new FlightInvalidInputException("Invalid Flight Duration: flight duration can not be zero");
		}
		else if ( fDur < 0 )
		{
			throw new FlightInvalidInputException("Invalid Flight Duration: flight duration can not be negative");
		}
	}

	public static void fareValidator(int farePrice)
	{
		if ( farePrice == 0 )
		{
			throw new FlightInvalidInputException("Invalid Flight Fare: flight fare can not be zero");
		}
		else if ( farePrice < 0 )
		{
			throw new FlightInvalidInputException("Invalid Flight Fare: flight fare can not be negative");
		}
	}

	public static void seatAvailabilityValidator(char sAvail)
	{
		if ( (sAvail != 'Y') && (sAvail != 'y') && (sAvail != 'N') && (sAvail != 'n') )
		{
			throw new FlightInvalidInputException("Invalid Seat Availability Input: Input is other than 'Y' and 'N'");
		}
	}

	public static void flightClassValidator(String fClass)
	{
		if ( !((fClass.equalsIgnoreCase("E")) || (fClass.equalsIgnoreCase("B")) || (fClass.equalsIgnoreCase("EB"))) )
		{
			throw new FlightInvalidInputException("Invalid Flight Class: Input is other than 'E','B','EB'");
		}
	}

	public static void flightDataValidator(String flightNo, String depLoc, String arrLoc, String validTill, String flightTime, float flightDuration, int fare, char seatAvailability, String flightClass)
	{
		depLocValidator(depLoc);
		arrLocValidator(arrLoc);
		dateValidator(validTill);
		flightTimeValidator(flightTime);
		flightDurationValidator(flightDuration);
		fareValidator(fare);
		seatAvailabilityValidator(seatAvailability);
		flightClassValidator(flightClass);
	}
}
