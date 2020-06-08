package com.subbu.flightreservation.services;

import com.subbu.flightreservation.dto.ReservationRequest;
import com.subbu.flightreservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);

}
