package com.subbu.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.subbu.flightreservation.dto.ReservationRequest;
import com.subbu.flightreservation.entities.Flight;
import com.subbu.flightreservation.entities.Reservation;
import com.subbu.flightreservation.repos.FightRepository;
import com.subbu.flightreservation.services.ReservationService;

@Controller
public class ReservationController {
	
    @Autowired
	FightRepository flightrepository;
    @Autowired
    ReservationService reservationservice;
    

  private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
    
    @RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightid,ModelMap modelmap)
	
	{
    	LOGGER.info("showCompleteReservation() invoked with the flight id: "+flightid);
		Flight flight = flightrepository.findById(flightid).get();
		modelmap.addAttribute("flight",flight);
		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation",method =RequestMethod.POST)
	public String completeReservation(ReservationRequest request,ModelMap modelmap) {
		LOGGER.info("completeReservation()  "+ request);
		Reservation reservation = reservationservice.bookFlight(request);
		modelmap.addAttribute("msg", "Reservation Created Successfully and the Id is:" +reservation.getId());
		
		return "reservationConfirmation";
		
		
		
	}

}
