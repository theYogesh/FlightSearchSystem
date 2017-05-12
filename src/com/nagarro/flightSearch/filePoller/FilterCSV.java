package com.nagarro.flightSearch.filePoller;

import java.io.*;

public class FilterCSV implements FileFilter
{
	private static final String filter = "csv";

	@Override
	public boolean accept(File file)
	{
		if ( "".equals(filter) )
		{
			return true;
		}
		return (file.getName().endsWith(filter));
	}
}
