package com.nagarro.flightSearch.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AlreadyLoginInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		Boolean login=false;
		Boolean res=false;

		Cookie ck[] = request.getCookies();
		
		if(ck!=null)
		{
			for (int i = 0; i < ck.length; i++)
			{
				if(ck[i].getName().equals("password"))
				{
					if(ck[i].getValue()!=null && !ck[i].getValue().isEmpty())
					{
						login = true;
						break;
					}
					
				}
			}
		}
		
		if(login==true)
		{
			response.sendRedirect("./flightSearch");
			res=false;
		}
		else
		{
			res=true;
		}
		return res;
	}
}
