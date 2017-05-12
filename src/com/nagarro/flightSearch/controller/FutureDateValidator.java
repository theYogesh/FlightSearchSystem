package com.nagarro.flightSearch.controller;

import java.text.ParseException;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nagarro.flightSearch.resources.Constants;

public class FutureDateValidator implements ConstraintValidator<FutureDate, String>
{

	public void initialize(FutureDate paramA)
	{
	}

	public boolean isValid(String checkDate, ConstraintValidatorContext ctx)
	{
		if ( checkDate == null )
		{
			return false;
		}

		Date tempDt = null;
		try
		{
			tempDt = Constants.DATE_FT.parse(checkDate);
		}
		catch (ParseException e)
		{
			return false;
		}

		if ( tempDt.compareTo(new Date()) < 0 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}

