package com.subbu.flightreservation.services;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.subbu.flightreservation.controllers.FlightController;
import com.subbu.flightreservation.dto.ReservationRequest;
import com.subbu.flightreservation.entities.Flight;
import com.subbu.flightreservation.entities.Passenger;
import com.subbu.flightreservation.entities.Reservation;
import com.subbu.flightreservation.repos.FightRepository;
import com.subbu.flightreservation.repos.PassengerRepository;
import com.subbu.flightreservation.repos.ReservationRepository;
import com.subbu.flightreservation.util.EmailUtil;
import com.subbu.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	
	@Value("${com.subbu.flightreservation.itinerary.dirpath}")
	private  String ITINERARY_DIR ;
	
	@Autowired
	FightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailutil;
	
	
	private static final Logger LOGGER =LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		
		LOGGER.info("Inside bookFlight()");
		
		/*The first step, would typically be,to make a payment. we are not going to do that,
		 *  we can retrieve request.getCardNumber(); and all that,and we can invoke a payment gate way webservice,typically might land on a payment gate way which is a third party software 
		 *  and that is where payment is processed , if we pass in all the information. But we are skipping that step */ 
		 
		/* make payment*/
		 /*  if the payment fails we would throw an exception right from here, otherwise the next step is to retriew the flight information first*/
		Long flightId = request.getFlightId();
		LOGGER.info("fetching flight for flight id:"+flightId);
		Flight flight = flightRepository.findById(flightId).get();
		
		
		Passenger passenger= new Passenger();
		passenger.setFirstname(request.getPassengerFirstName());
		passenger.setLastname(request.getPassengerLastName());
		passenger.setMiddlename(request.getPassengerMiddleName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());
		LOGGER.info("Saving the passenger: "+passenger);
		Passenger savedpassenger = passengerRepository.save(passenger);
		
		Reservation reservation=new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedpassenger);
		reservation.setCheckedIn(false);
	
		LOGGER.info("Saving the reservation:"+reservation);
		Reservation savedreservation = reservationRepository.save(reservation);
		
		String filepath = ITINERARY_DIR+savedreservation.getId()+".pdf";
		LOGGER.info("Getting the itinerary");
		  pdfGenerator.generateItinerary(savedreservation,filepath);
		LOGGER.info("Emailing the itinerary");
		 emailutil.sendItinerary(passenger.getEmail(), filepath);
		
		
		return savedreservation;
	}

}
