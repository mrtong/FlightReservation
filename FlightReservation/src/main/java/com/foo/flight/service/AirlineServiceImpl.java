package com.foo.flight.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foo.flight.dao.interfaces.FlightDao;
import com.foo.flight.model.Flight;
import com.foo.flight.model.FlightSearchCriteria;
import com.foo.flight.model.Flights;
import com.foo.flight.service.exceptions.NoSuchFlightException;
import com.foo.flight.service.interfaces.AirlineService;

@Service
@Transactional(readOnly = true)
public class AirlineServiceImpl implements AirlineService {

  private final FlightDao flightDao;

  public AirlineServiceImpl(FlightDao flightDao) {
    this.flightDao = flightDao;
  }
  
  @Override
  public List<Flight> getFlights() {
    return flightDao.getFlights();
  }

  @Override
  public Flight getFlight(Long id) throws NoSuchFlightException {
    Flight flight = flightDao.getFlight(id);
    if (flight != null) {
      return flight;
    }
    else {
      throw new NoSuchFlightException("Flight:" + id + " not found");
    }
  }

  @Override
  public Flights getFlights(FlightSearchCriteria criteria) {
    String fromAirportCode = criteria.getFromAirportCode();
    String toAirportCode = criteria.getToAirportCode();

    List<Flight> flights = flightDao.findFlights(fromAirportCode, toAirportCode);

    Flights results = new Flights();
    results.setFlights(flights);

    return results;
  }

  @Override
  public Flight getFlight(String flightNumber) throws NoSuchFlightException {
    Flight flight = flightDao.getFlight(flightNumber);
    if (flight != null) {
      return flight;
    }
    throw new NoSuchFlightException("Could not find flight:" + flightNumber);
  }
}

