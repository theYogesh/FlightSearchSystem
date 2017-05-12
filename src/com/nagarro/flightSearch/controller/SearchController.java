package com.nagarro.flightSearch.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.flightSearch.model.FlightInfo;
import com.nagarro.flightSearch.model.FlightSearchFormModel;
import com.nagarro.flightSearch.util.FlightUtil;

@Controller
public class SearchController
{
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(value = "/")
	public String index()
	{
		return "redirect:/login";
	}
	
	@RequestMapping(value = "/flightSearch", method = RequestMethod.GET)
	public ModelAndView flightSearch(Model model)
	{
		model.addAttribute("flightSearchForm", new FlightSearchFormModel());
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = simpleDateFormat.format(new Date());

		ModelAndView mnv = new ModelAndView();
		mnv.addObject("maxDate", currentDate);
		mnv.setViewName("flightSearchForm");

		return mnv;
	}


	@RequestMapping(value = "/flightSearch", method = RequestMethod.POST)
	public ModelAndView flightSearchProcess(@Valid @ModelAttribute("flightSearchForm") FlightSearchFormModel fsFormModel, BindingResult result)
	{
		if ( result.hasErrors() )
		{
			ModelAndView model = new ModelAndView();
			model.setViewName("flightSearchForm");
			return model;
		}

		ArrayList<FlightInfo> searchResults;
		FlightUtil.initResources();
		searchResults =
				FlightUtil.searchFlight(fsFormModel.getDepLoc(), fsFormModel.getArrLoc(), fsFormModel.getFlightDate(), fsFormModel.getFlightClass(), fsFormModel.getOrderBy());
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("flightResults");

		if(searchResults.isEmpty())
		{
			mnv.addObject("errorMsg", "No Flights Found!");
		}
		mnv.addObject("resultList", searchResults);
		return mnv;
	}
}
