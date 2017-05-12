/**
 * group of Exception classes of Retail Item
 */
package com.nagarro.flightSearch.exception;

/**
 * User Defined Exception Class to handle exceptions related to Item inputs
 */
public class FlightInvalidInputException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public FlightInvalidInputException(String msg)
	{
		super(msg);
	}
}
