package com.subbu.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.subbu.flightreservation.entities.User;
import com.subbu.flightreservation.repos.UserRepository;
import com.subbu.flightreservation.services.SecurityService;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userrepository;

	
	private static final Logger LOGGER =LoggerFactory.getLogger(UserController.class);

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private SecurityService securityService;
	
	
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
	}
	
	
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register()"+user);
		user.setPassword(encoder.encode(user.getPassword()));
		userrepository.save(user);
		return "login/login";
	}
	
	@RequestMapping("/showLogin")
	public String showLoginPage() {
		LOGGER.info("Inside showLoginPage()");
		return "login/login";
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email,@RequestParam("password") String password, ModelMap modelmap) {
		LOGGER.info("Inside login() and the email is:"+ email);
		/*
		 * User user = userrepository.findByEmail(email); 
		 * if(user.getPassword().equals(password)){
		 *  return "findFlights";
		 * } else {
		 * modelmap.addAttribute("msg","Invalid username or password. please try again" ); 
		 * }
		 * return "login/login"; 
		 */
		boolean loginresponse = securityService.login(email, password);
		if (loginresponse){
			return "findFlights";
		}
		else {
			modelmap.addAttribute("msg","Invalid username or password. please try again");
		}
		
		return "login/login";
	}
	
	

}
