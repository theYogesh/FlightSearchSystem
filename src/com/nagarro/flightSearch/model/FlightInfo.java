package com.nagarro.flightSearch.model;

import java.util.Date;

import com.nagarro.flightSearch.resources.Constants;

public class FlightInfo
{
	private int 	id;
	private Airline airline;
	private String	flightNo;
	private String	depLOC;
	private String	arrLOC;
	private Date	validTill;
	private String	flightTime;
	private float	flightDuration;
	private int		fare;
	private char	seatAvailability;
	private String	classType;

	public FlightInfo()
	{
		flightNo = "";
		depLOC = "";
		arrLOC = "";
		validTill = null;
		flightTime = "";
		flightDuration = 0;
		fare = 0;
		seatAvailability = 'N';
		classType = "";
	}

	public FlightInfo(String flightNo, String depLOC, String arrLOC, Date validTill, String flightTime, float flightDuration, int fare, char seatAvailability, String classType)
	{
		super();
		this.flightNo = flightNo;
		this.depLOC = depLOC;
		this.arrLOC = arrLOC;
		this.validTill = validTill;
		this.flightTime = flightTime;
		this.flightDuration = flightDuration;
		this.fare = fare;
		this.seatAvailability = seatAvailability;
		this.classType = classType;
	}

	public FlightInfo(FlightInfo obj)
	{
		this.flightNo = obj.flightNo;
		this.depLOC = obj.depLOC;
		this.arrLOC = obj.arrLOC;
		this.validTill = obj.validTill;
		this.flightTime = obj.flightTime;
		this.flightDuration = obj.flightDuration;
		this.fare = obj.fare;
		this.seatAvailability = obj.seatAvailability;
		this.classType = obj.classType;
	}

	
	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @return the airline
	 */
	public Airline getAirline()
	{
		return airline;
	}

	/**
	 * @param airline the airline to set
	 */
	public void setAirline(Airline airline)
	{
		this.airline = airline;
	}

	/**
	 * @return the flightNo
	 */
	public String getFlightNo()
	{
		return flightNo;
	}

	/**
	 * @param flightNo
	 *            the flightNo to set
	 */
	public void setFlightNo(String flightNo)
	{
		this.flightNo = flightNo;
	}

	/**
	 * @return the depLOC
	 */
	public String getDepLOC()
	{
		return depLOC;
	}

	/**
	 * @param depLOC
	 *            the depLOC to set
	 */
	public void setDepLOC(String depLOC)
	{
		this.depLOC = depLOC;
	}

	/**
	 * @return the arrLOC
	 */
	public String getArrLOC()
	{
		return arrLOC;
	}

	/**
	 * @param arrLOC
	 *            the arrLOC to set
	 */
	public void setArrLOC(String arrLOC)
	{
		this.arrLOC = arrLOC;
	}

	/**
	 * @return the validTill
	 */
	public Date getValidTill()
	{
		return validTill;
	}

	/**
	 * @param validTill
	 *            the validTill to set
	 */
	public void setValidTill(Date validTill)
	{
		this.validTill = validTill;
	}

	/**
	 * @return the flightTime
	 */
	public String getFlightTime()
	{
		return flightTime;
	}

	/**
	 * @param flightTime
	 *            the flightTime to set
	 */
	public void setFlightTime(String flightTime)
	{
		this.flightTime = flightTime;
	}

	/**
	 * @return the flightDuration
	 */
	public float getFlightDuration()
	{
		return flightDuration;
	}

	/**
	 * @param flightDuration
	 *            the flightDuration to set
	 */
	public void setFlightDuration(float flightDuration)
	{
		this.flightDuration = flightDuration;
	}

	/**
	 * @return the fare
	 */
	public int getFare()
	{
		return fare;
	}

	/**
	 * @param fare
	 *            the fare to set
	 */
	public void setFare(int fare)
	{
		this.fare = fare;
	}

	/**
	 * @return the seatAvailability
	 */
	public char getSeatAvailability()
	{
		return seatAvailability;
	}

	/**
	 * @param seatAvailability
	 *            the seatAvailability to set
	 */
	public void setSeatAvailability(char seatAvailability)
	{
		this.seatAvailability = seatAvailability;
	}

	/**
	 * @return the classType
	 */
	public String getClassType()
	{
		return classType;
	}

	/**
	 * @param classType
	 *            the classType to set
	 */
	public void setClassType(String classType)
	{
		this.classType = classType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((arrLOC == null) ? 0 : arrLOC.hashCode());
		result = prime * result + ((depLOC == null) ? 0 : depLOC.hashCode());
		result = prime * result + ((flightNo == null) ? 0 : flightNo.hashCode());
		result = prime * result + ((flightTime == null) ? 0 : flightTime.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if ( this == obj )
		{
			return true;
		}
		if ( obj == null )
		{
			return false;
		}
		if ( !(obj instanceof FlightInfo) )
		{
			return false;
		}
		FlightInfo other = (FlightInfo) obj;
		if ( arrLOC == null )
		{
			if ( other.arrLOC != null )
			{
				return false;
			}
		}
		else if ( !arrLOC.equals(other.arrLOC) )
		{
			return false;
		}
		if ( depLOC == null )
		{
			if ( other.depLOC != null )
			{
				return false;
			}
		}
		else if ( !depLOC.equals(other.depLOC) )
		{
			return false;
		}
		if ( flightNo == null )
		{
			if ( other.flightNo != null )
			{
				return false;
			}
		}
		else if ( !flightNo.equals(other.flightNo) )
		{
			return false;
		}
		if ( flightTime == null )
		{
			if ( other.flightTime != null )
			{
				return false;
			}
		}
		else if ( !flightTime.equals(other.flightTime) )
		{
			return false;
		}
		return true;
	}

	public String getFlightTimeFormatted()
	{
		String resStr;
		resStr = flightTime.substring(0, 2) + ":" + flightTime.substring(2, 4) + "(HH:MM)";
		return resStr;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "[" + flightNo + "|" + depLOC + "|" + arrLOC + "|" + Constants.DATE_FT.format(validTill) + "|"
				+ flightTime + "|"
				+ flightDuration + "|" + fare + "|" + seatAvailability + "|" + classType + "]";
	}
}
