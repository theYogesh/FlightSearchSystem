package com.nagarro.flightSearch.resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Properties;

import com.nagarro.flightSearch.dao.FlightDao;
import com.nagarro.flightSearch.dao.FlightDaoHibernateImpl;
import com.nagarro.flightSearch.exception.FlightSearchIntializationException;

final public class Constants
{
	public static final String						CSV_DIR;
	public static final long						SYNC_TIME;
	public static final float						BCLASS_SURCHARGE;
	public static final FlightDao					DAO;
	public static final SimpleDateFormat			DATE_FT;

	private static Properties						flightProperties;

	static
	{
		try
		{
			loadProperties();
		}
		catch (FlightSearchIntializationException e)
		{
			System.out.println(e.getMessage());
		}

		CSV_DIR = getCSVDirPath();
		SYNC_TIME = getSyncTime();
		BCLASS_SURCHARGE = getBusinessClassSurcharge();
		DAO = getDao();
		DATE_FT = getDateFormat();

	}

	private Constants() {};
	private static void loadProperties() throws FlightSearchIntializationException
	{
		String propPath = "D:/workspace/Assignment10/resources/FlightDataInfo.properties";
		try
		{
			flightProperties = new Properties();
			FileInputStream in = new FileInputStream(propPath);
			flightProperties.load(in);
			in.close();
		}
		catch (FileNotFoundException e)
		{
			throw new FlightSearchIntializationException("Applicaion Initialization Error: Cannot find property File", propPath);
		}
		catch (IOException e)
		{
			throw new FlightSearchIntializationException("Applicaion Initialization Error: Cannot load Property File", propPath);
		}
	}

	public static String getCSVDirPath()
	{
		return flightProperties.getProperty("CSVDir");
	}

	public static long getSyncTime()
	{
		return Long.parseLong(flightProperties.getProperty("dataSyncTime"));
	}

	public static float getBusinessClassSurcharge()
	{
		return Float.parseFloat(flightProperties.getProperty("businessClassSurcharge"));
	}

	public static FlightDao getDao()
	{
		return new FlightDaoHibernateImpl();
	}

	public static SimpleDateFormat getDateFormat()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		dateFormat.setLenient(false);
		return dateFormat;
	}
}
