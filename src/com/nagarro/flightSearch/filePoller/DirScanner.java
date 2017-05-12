package com.nagarro.flightSearch.filePoller;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.nagarro.flightSearch.resources.Constants;

public class DirScanner implements Runnable
{
	private File				filesArray[];
	private HashMap<File, Long>	dir	= new HashMap<File, Long>();
	private FilterCSV			dfw;
	private boolean				taskDone;

	public DirScanner()
	{
		taskDone = false;
		dfw = new FilterCSV();
		filesArray = new File(Constants.CSV_DIR).listFiles(dfw);

		//transfer to the hashmap be used a reference and keep the lastModfied value
		for (int i = 0; i < filesArray.length; i++)
		{
			dir.put(filesArray[i], new Long(filesArray[i].lastModified()));
		}

		for (int i = 0; i < filesArray.length; i++)
		{
			onAdd(filesArray[i]);
		}
		System.out.println("\n\nUpdated Data:-\n");

		Constants.DAO.printData();
	}

	public final void run()
	{
		try
		{

			HashSet<File> checkedFiles = new HashSet<File>();
			filesArray = new File(Constants.CSV_DIR).listFiles(dfw);

			// scan the files and check for modification/addition
			for (int i = 0; i < filesArray.length; i++)
			{
				Long current = (Long) dir.get(filesArray[i]);
				checkedFiles.add(filesArray[i]);
				if ( current == null )
				{
					// new file
					dir.put(filesArray[i], new Long(filesArray[i].lastModified()));
					onAdd(filesArray[i]);
				}
				else if ( current.longValue() != filesArray[i].lastModified() )
				{
					// modified file
					dir.put(filesArray[i], new Long(filesArray[i].lastModified()));
					onChange(filesArray[i]);
				}
			}

			// now check for deleted files
			@SuppressWarnings("unchecked")
			Set<File> ref = ((HashMap<File, Long>) dir.clone()).keySet();
			ref.removeAll((Set<File>) checkedFiles);
			Iterator<File> it = ref.iterator();
			while (it.hasNext())
			{
				File deletedFile = (File) it.next();
				dir.remove(deletedFile);
				onDelete(deletedFile);
			}
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public void onAdd(File file)
	{
		System.out.println("\nNEW FILE ADDED : " + file.getName() + "\n");
		Constants.DAO.addDataFromCSV(file);
	}

	public void onChange(File file)
	{
		System.out.println("\nFILE MODIFIED : " + file.getName() + "\n");
		Constants.DAO.modifyData(file);

	}

	public void onDelete(File file)
	{
		System.out.println("\nFILE DELETED : " + file.getName() + "\n");
		Constants.DAO.deleteData(file);
	}

	/**
	 * @return the taskDone
	 */
	public boolean isTaskDone()
	{
		return taskDone;
	}

	/**
	 * @param taskDone the taskDone to set
	 */
	public void setTaskDone(boolean taskDone)
	{
		this.taskDone = taskDone;
	}
}
