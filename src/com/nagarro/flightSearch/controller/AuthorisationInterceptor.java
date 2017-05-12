package com.nagarro.flightSearch.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthorisationInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
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
						res = true;
					}
					else
					{
						res=false;
					}
					break;
				}
			}
		}

		if(res==false)
		{
			response.sendRedirect("./login");
		}
		return res;
	}
}
