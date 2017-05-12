package com.nagarro.flightSearch.resources;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.nagarro.flightSearch.filePoller.DirScanner;

final public class Resources
{
	public static final HibernateTemplate			H_TEMPLATE;
	public static final ScheduledExecutorService	EXEC_SERVICE;

	static
	{
		AbstractApplicationContext context = new ClassPathXmlApplicationContext("jpaContext.xml");
		H_TEMPLATE = (HibernateTemplate) context.getBean("template");
		EXEC_SERVICE = getExecuterService();
	}

	private Resources()
	{
	};

	public static void close()
	{
		//H_TEMPLATE.flush();
		EXEC_SERVICE.shutdownNow();
	}

	public static ScheduledExecutorService getExecuterService()
	{
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		return service;
	}

	public static void startDirPoller()
	{
		EXEC_SERVICE.scheduleAtFixedRate(new DirScanner(), 0, Constants.SYNC_TIME, TimeUnit.SECONDS);
	}
}
