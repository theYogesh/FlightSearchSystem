package com.nagarro.flightSearch.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.nagarro.flightSearch.dao.UserDaoImpl;
import com.nagarro.flightSearch.model.User;

@Controller
public class LoginController
{
	UserDaoImpl userDao;

	public UserDaoImpl getUserDao()
	{
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao)
	{
		this.userDao = userDao;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(Model model,HttpServletRequest req, HttpServletResponse response)
	{
		model.addAttribute("flightSearchUser", new User());
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("login");
		return mnv;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView checkLogin(HttpServletRequest request, HttpServletResponse response, @Valid @ModelAttribute("flightSearchUser") User flightSearchUser,BindingResult result)
	{
		if ( result.hasErrors() )
		{
			ModelAndView model = new ModelAndView();
			model.setViewName("login");
			return model;
		}
		if ( !(userDao.isExists(flightSearchUser)) )
		{
			ModelAndView model = new ModelAndView();
			
			model.setViewName("login");
			model.addObject("errorMsg", "Invalid Username and Password");

			return model;
		}

		Cookie usernameCookie = new Cookie("username", flightSearchUser.getUsername());
		Cookie passCookie = new Cookie("password", flightSearchUser.getPassword());
		// 24hr
		usernameCookie.setMaxAge(60 * 60 * 24);
		passCookie.setMaxAge(60 * 60 * 24);

		response.addCookie(usernameCookie);
		response.addCookie(passCookie);
		
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("redirect:/flightSearch");
		return mnv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register(Model model)
	{
		model.addAttribute("flightSearchUser", new User());
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("register");
		return mnv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerProcess(@Valid @ModelAttribute("flightSearchUser") User flightSearchUser, BindingResult result)
	{
		if ( result.hasErrors() )
		{
			ModelAndView model = new ModelAndView();
			model.setViewName("register");
			return model;
		}
		Boolean res = userDao.saveUser(flightSearchUser);
		if ( res == false )
		{
			ModelAndView model = new ModelAndView();
			model.setViewName("register");
			model.addObject("errorMsg", "Username Already Exists, Please choose another username");
			return model;
		}

		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("redirect:/login");
		return mnv;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
	{
		Cookie usernameCookie = new Cookie("username", "");
		Cookie passCookie = new Cookie("password", "");
		// 24hr
		usernameCookie.setMaxAge(0);
		passCookie.setMaxAge(0);

		response.addCookie(usernameCookie);
		response.addCookie(passCookie);
		
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("redirect:/login");
		return mnv;
	}
}
