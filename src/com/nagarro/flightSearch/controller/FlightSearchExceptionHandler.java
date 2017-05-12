package com.nagarro.flightSearch.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class FlightSearchExceptionHandler
{
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public ModelAndView handleException(Exception e)
	{
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("exceptionPage");
		mnv.addObject("errorMsg", e.getMessage());
		return mnv;
	}
}
