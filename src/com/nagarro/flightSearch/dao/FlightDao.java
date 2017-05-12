package com.nagarro.flightSearch.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.nagarro.flightSearch.model.FlightInfo;

public interface FlightDao
{
	public void addData(File fname, HashSet<FlightInfo> FlightDataSet);

	public void addDataFromCSV(File fname);

	public void deleteData(File fname);

	public void modifyData(File fname);

	public List<FlightInfo> getAllRecords();

	public HashMap<String, HashSet<FlightInfo>> getDataMap();
	
	public void printData();
	
	public ArrayList<FlightInfo> searchFlight(String depLOC, String arrLOC, Date flightDate, String flightClass);
}
