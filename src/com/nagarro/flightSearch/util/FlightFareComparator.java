package com.nagarro.flightSearch.util;

import java.util.Comparator;

import com.nagarro.flightSearch.model.FlightInfo;

public class FlightFareComparator implements Comparator<FlightInfo>
{
	@Override
	public int compare(FlightInfo f1, FlightInfo f2)
	{
		if ( f1.getFare() == f2.getFare() )
			return 0;
		else if ( f1.getFare() > f2.getFare() )
			return 1;
		else
			return -1;
	}
}
