package com.nagarro.flightSearch.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.nagarro.flightSearch.model.FlightInfo;
import com.nagarro.flightSearch.resources.Constants;
import com.nagarro.flightSearch.resources.Resources;

public class FlightUtil
{
	public static ArrayList<FlightInfo> searchFlight(String depLOC, String arrLOC, Date flightDate, String flightClass, String orderBy)
	{
		ArrayList<FlightInfo> searchResults = Constants.DAO.searchFlight(depLOC, arrLOC, flightDate, flightClass);
		if ( orderBy.equals("fare") )
		{
			Collections.sort(searchResults, new FlightFareComparator());
		}
		else if ( orderBy.equals("duration") )
		{
			Collections.sort(searchResults, new FlightDurationComparator());
		}
		return searchResults;
	}

	public static void initResources()
	{
		try
		{
			Class.forName("com.nagarro.flightSearch.resources.Constants");
			Class.forName("com.nagarro.flightSearch.resources.Resources");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("ErrorR: " + e.getMessage());
		}

		System.out.println("\t\tUPDATING DATA FROM CSVs...\n");
		Resources.startDirPoller();
	}
}