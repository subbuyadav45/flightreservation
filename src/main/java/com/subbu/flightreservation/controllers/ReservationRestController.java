package com.subbu.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.subbu.flightreservation.dto.ReservationUpdtaeRequest;
import com.subbu.flightreservation.entities.Reservation;
import com.subbu.flightreservation.repos.ReservationRepository;

@RestController
@CrossOrigin    //By this Annotation,Different applications running on different domains and ports can communicate with each other.
public class ReservationRestController {
	@Autowired
	ReservationRepository reservationRepository;
	

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("findReservation() for id:"+id );
		return reservationRepository.findById(id).get();
		
	}
	
	@RequestMapping(value = "/reservations",method = RequestMethod.POST)
	public Reservation updateReservation(@RequestBody ReservationUpdtaeRequest request) {
		LOGGER.info("updateReservation() for:"+request );
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setCheckedIn(request.getCheckedIn());
		reservation.setNumberOfBags(request.getNumberOfBags());
		LOGGER.info("saving Reservation");
		return reservationRepository.save(reservation);
	
	}
	
	

}
