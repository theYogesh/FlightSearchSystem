package com.nagarro.flightSearch.util;

import java.util.Comparator;

import com.nagarro.flightSearch.model.FlightInfo;


public class FlightDurationComparator implements Comparator<FlightInfo>
{
	@Override
	public int compare(FlightInfo f1, FlightInfo f2)
	{

		if ( f1.getFlightDuration() == f2.getFlightDuration() )
			return 0;
		else if ( f1.getFlightDuration() > f2.getFlightDuration() )
			return 1;
		else
			return -1;
	}
}
