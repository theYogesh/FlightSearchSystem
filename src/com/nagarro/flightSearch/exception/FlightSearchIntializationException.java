package com.nagarro.flightSearch.exception;

/**
 * User Defined Exception Class to handle exceptions related to Application Initialization
 */
public class FlightSearchIntializationException extends Exception
{
	private static final long serialVersionUID = 1L;

	public FlightSearchIntializationException(String msg)
	{
		super(msg);
	}

	public FlightSearchIntializationException(String msg, String path)
	{
		super(msg + "\nFile Path: " + path);
	}
}
