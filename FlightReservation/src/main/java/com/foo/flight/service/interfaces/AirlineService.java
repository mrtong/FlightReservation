package com.foo.flight.service.interfaces;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.model.Flight;
import com.foo.flight.model.FlightSearchCriteria;
import com.foo.flight.model.Flights;
import com.foo.flight.service.exceptions.NoSuchFlightException;

public interface AirlineService {

	  Flight getFlight(Long id) throws NoSuchFlightException;
	  
	  Flight getFlight(String flightNumber) throws NoSuchFlightException;

	  List<Flight> getFlights();
	  
	  List<Flight> getFlightListByCriteria(FlightSearchCriteria criteria);
	  
	  Flights getFlights(FlightSearchCriteria criteria);
	  
	  Flights getFlights(Specification<Flight> spec);
	  
	  Flight save(Flight flight);
	  
	}
