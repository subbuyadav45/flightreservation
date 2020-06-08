package com.subbu.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subbu.flightreservation.entities.Passenger;


public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
