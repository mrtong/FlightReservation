package com.foo.flight.service.interfaces;

import java.util.List;

import com.foo.flight.model.Flight;
import com.foo.flight.model.FlightSearchCriteria;
import com.foo.flight.model.Flights;
import com.foo.flight.service.exceptions.NoSuchFlightException;

public interface AirlineService {

	  Flight getFlight(Long id) throws NoSuchFlightException;
	  
	  Flight getFlight(String flightNumber) throws NoSuchFlightException;

	  List<Flight> getFlights();
	  
	  Flights getFlights(FlightSearchCriteria criteria);
	}
