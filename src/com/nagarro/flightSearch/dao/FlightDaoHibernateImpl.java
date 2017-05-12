package com.nagarro.flightSearch.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.nagarro.flightSearch.exception.FlightInvalidInputException;
import com.nagarro.flightSearch.model.Airline;
import com.nagarro.flightSearch.model.FlightInfo;
import com.nagarro.flightSearch.resources.Constants;
import com.nagarro.flightSearch.resources.Resources;
import com.nagarro.flightSearch.util.FlightDataValidator;
import com.nagarro.flightSearch.view.FlightInfoResultView;

public class FlightDaoHibernateImpl implements FlightDao
{
	private SessionFactory sessionFactory;

	public FlightDaoHibernateImpl()
	{	
		sessionFactory = Resources.H_TEMPLATE.getSessionFactory();
	}

	@Override
	public synchronized void addData(File fname, HashSet<FlightInfo> flightDataSet)
	{
		Airline tempAirline = new Airline();

		String fileName = fname.getName().substring(0, fname.getName().lastIndexOf('.'));
		tempAirline.setAirlineName(fileName);
		tempAirline.setFlights(flightDataSet);
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			session.save(tempAirline);
			tx.commit();
		}
		catch (HibernateException e)
		{
			if ( tx != null )
				tx.rollback();
			System.out.println("Error: " + e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		finally
		{
			session.close();
		}
	}

	@Override
	public synchronized void addDataFromCSV(File fname)
	{
		Scanner inStreamCSV = null;
		FlightInfo tempFIobj;

		try
		{
			inStreamCSV = new Scanner(fname);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		String flightNo, depLOC, arrLOC, classType, flightTime, tempValidTill;
		int fare;
		float flightDuration;
		char seatAvailability;
		int rowCount;
		Date validTill;

		Set<FlightInfo> flightDataSet = new HashSet<FlightInfo>();

		// excluding first row
		if ( inStreamCSV.hasNext() )
			inStreamCSV.next();

		rowCount = 0;
		while (inStreamCSV.hasNext())
		{
			rowCount++;
			try
			{
				String data = inStreamCSV.next();
				String[] values = data.split("\\|");

				if ( values.length < 9 )
				{
					throw new FlightInvalidInputException("Input Fields Missing(Less than 9 Fields)");
				}
				else if ( values.length > 9 )
				{
					throw new FlightInvalidInputException("Wrong Input Fields: More than 9 Fields");
				}

				flightNo = values[0];
				depLOC = values[1];
				arrLOC = values[2];
				tempValidTill = values[3];
				flightTime = values[4];
				flightDuration = Float.parseFloat(values[5]);
				fare = Integer.parseInt(values[6]);
				seatAvailability = values[7].charAt(0);
				classType = values[8].toUpperCase();

				FlightDataValidator.flightDataValidator(flightNo, depLOC, arrLOC, tempValidTill, flightTime, flightDuration, fare, seatAvailability, classType);

				validTill = Constants.DATE_FT.parse(tempValidTill);

				tempFIobj =
						new FlightInfo(flightNo, depLOC, arrLOC, validTill, flightTime, flightDuration, fare, seatAvailability, classType);

				if ( flightDataSet.contains(tempFIobj) )
				{
					throw new FlightInvalidInputException("Duplicate Flight Data");
				}
				flightDataSet.add(tempFIobj);
			}
			catch (Exception e)
			{
				System.err.println("Error: Invalid data in '" + fname.getName() + "' at Row# " + rowCount
						+ "\n\tError msg: " + e.getMessage());

			}
		}

		// to remove file extension from file name
		String fileName = fname.getName().substring(0, fname.getName().lastIndexOf('.'));

		Airline airlineTemp = new Airline();
		airlineTemp.setAirlineName(fileName);
		airlineTemp.setFlights(flightDataSet);

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			session.save(airlineTemp);
			tx.commit();
		}
		catch (HibernateException e)
		{
			if ( tx != null )
				tx.rollback();

			System.out.println("Error: " + e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		finally
		{
			session.close();
		}
		inStreamCSV.close();
	}

	@Override
	public void deleteData(File file)
	{
		String fileName = file.getName().substring(0, file.getName().lastIndexOf('.'));

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			Query q = session.createQuery("from Airline where airlineName = :airlineCode ");
			q.setParameter("airlineCode", fileName);
			Airline tempAirline = (Airline) q.getResultList().get(0);
			session.delete(tempAirline);
			tx.commit();
		}
		catch (HibernateException e)
		{
			if ( tx != null )
				tx.rollback();
			System.out.println("Error: " + e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		finally
		{
			session.close();
		}
	}

	@Override
	public synchronized void modifyData(File fname)
	{
		deleteData(fname);
		addDataFromCSV(fname);
	}

	@Override
	public synchronized List<FlightInfo> getAllRecords()
	{
		ArrayList<FlightInfo> resultList = new ArrayList<FlightInfo>();
		List<Airline> res = null;

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			Query q = session.createQuery("from Airline");
			res = q.getResultList();
			tx.commit();
		}
		catch (HibernateException e)
		{
			if ( tx != null )
				tx.rollback();
			System.out.println("Error: " + e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		finally
		{
			session.close();
		}

		for (int i = 0; i < res.size(); i++)
		{
			Iterator<FlightInfo> iter = res.get(i).getFlights().iterator();

			while (iter.hasNext())
			{
				resultList.add(iter.next());
			}
		}
		return resultList;
	}

	@Override
	public synchronized void printData()
	{
		List<Airline> res = null;

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			Query q = session.createQuery("from Airline");
			res = q.getResultList();
			tx.commit();
		}
		catch (HibernateException e)
		{
			if ( tx != null )
				tx.rollback();
			System.out.println("Error: " + e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		finally
		{
			session.close();
		}

		for (int i = 0; i < res.size(); i++)
		{
			System.out.println("\n" + res.get(i).getAirlineName() + ":-");
			Iterator<FlightInfo> iter = res.get(i).getFlights().iterator();
			while (iter.hasNext())
			{
				System.out.println(iter.next());
			}
		}

	}

	@Override
	public synchronized HashMap<String, HashSet<FlightInfo>> getDataMap()
	{
		HashMap<String, HashSet<FlightInfo>> ret = new HashMap<String, HashSet<FlightInfo>>();
		HashSet<FlightInfo> sret;
		List<Airline> res = null;

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			Query q = session.createQuery("from Airline");
			res = q.getResultList();
			tx.commit();
		}
		catch (HibernateException e)
		{
			if ( tx != null )
				tx.rollback();
			System.out.println("Error: " + e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}
		finally
		{
			session.close();
		}
		for (int i = 0; i < res.size(); i++)
		{
			sret = new HashSet<FlightInfo>();
			Iterator<FlightInfo> iter = res.get(i).getFlights().iterator();

			while (iter.hasNext())
			{
				sret.add(iter.next());
			}
			ret.put(res.get(i).getAirlineName(), sret);
		}

		return ret;
	}

	@Override
	public synchronized ArrayList<FlightInfo> searchFlight(String depLOC, String arrLOC, Date flightDate, String flightClass)
	{
		ArrayList<FlightInfo> resultList = new ArrayList<>();
		FlightInfoResultView resView;
		
		String hqlQuery ="select F.flightNo, A.airlineName, F.flightDuration, F.flightTime, F.fare  "
				+ "from FlightInfo as F "
				+ "join F.airline as A "
				+ "where F.arrLOC = :ArrCode AND "
				+ "F.depLOC = :DepCode AND "
				+ "F.seatAvailability = :SeatAvail AND "
				+ "F.validTill > :TravelDate AND "
				+ "F.classType LIKE :ClassCode ";

		List<Object[]> res= null;
		
		Session session = sessionFactory.openSession();
		Transaction tx= null;
		try
		{
			tx=session.beginTransaction();
			Query q = session.createQuery(hqlQuery);
			q.setParameter("ArrCode", arrLOC);
			q.setParameter("DepCode", depLOC);
			q.setParameter("TravelDate", flightDate);
			q.setParameter("ClassCode", "%"+flightClass+"%");
			q.setParameter("SeatAvail", 'Y');
			
			res= (List<Object[]>)q.getResultList();
			tx.commit();
		}
		catch(HibernateException e)
		{
			if (tx!=null) 
				tx.rollback();
			System.out.println("Error: "+e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("Error: "+e.getMessage());
		}
		finally
		{
			session.close();
		}
		
		String airlineName, flightNo, flightTime ;
		float flightDur;
		int fare;
		for(Object[] array : res) 
		{
			flightNo = (String) array[0];
			airlineName = (String) array[1];
			flightDur = (float) array[2];
			flightTime = (String) array[3];
			fare = (int) array[4];
			
			resView = new FlightInfoResultView();
			resView.setFlightNo(flightNo);
			resView.setAirlineName(airlineName);
			resView.setFlightDuration(flightDur);
			resView.setFlightTime(flightTime);
			resView.setFare((int)(fare * Constants.BCLASS_SURCHARGE));
			resView.setArrLOC(arrLOC.toUpperCase());
			resView.setDepLOC(depLOC.toUpperCase());
			resultList.add(resView);
		}
		return resultList;
	}
}