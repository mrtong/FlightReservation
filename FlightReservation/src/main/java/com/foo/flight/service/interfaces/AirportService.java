package com.foo.flight.service.interfaces;

import java.util.List;

import com.foo.flight.model.Airport;
import com.foo.flight.service.exceptions.NoSuchAirportException;

public interface AirportService {
	  List<Airport> getAirports();
	  
	  Airport getAirport(String code) throws NoSuchAirportException;
	}
