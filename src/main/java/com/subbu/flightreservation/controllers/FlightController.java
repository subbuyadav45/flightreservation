package com.subbu.flightreservation.controllers;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.subbu.flightreservation.entities.Flight;
import com.subbu.flightreservation.repos.FightRepository;

@Controller
public class FlightController {
	
	@Autowired
	FightRepository flightrepository;
	
	
	private static final Logger LOGGER =LoggerFactory.getLogger(FlightController.class);

	@RequestMapping("/findflights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy")Date departureDate,ModelMap modelmap) {
	
      /*insert into flight values(1,'AA1','American Airlines','AUS','NYC',STR_TO_DATE('02-05-2018', '%m-%d-%Y'),'2018-02-05 03:14:07')*/
	  /* What ever pattern we used in the insert statement ['%m-%d-%Y'], the same pattern should be used in our java application as well*/	
		
		LOGGER.info("Inside findFlights() From :"+from+"TO:"+to+"DepartureDate:"+departureDate);
		List<Flight> flights = flightrepository.findFlights(from,to,departureDate);
		modelmap.addAttribute("flights", flights);
		LOGGER.info("flights found are:"+flights);
		return "displayFlights";
		
	}
	
	@RequestMapping("admin/showAddFlight")
	public String showAddFlight() {
		
		return "addFlight";
		
	}
	
	

}
