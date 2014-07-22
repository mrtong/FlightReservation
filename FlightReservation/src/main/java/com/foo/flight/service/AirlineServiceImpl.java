package com.foo.flight.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.model.Flight;
import com.foo.flight.model.FlightSearchCriteria;
import com.foo.flight.model.Flights;
import com.foo.flight.model.sepecification.FlightSpecifications;
import com.foo.flight.service.exceptions.NoSuchFlightException;
import com.foo.flight.service.interfaces.AirlineService;

@Service
@Transactional(readOnly = true)
public class AirlineServiceImpl implements AirlineService {
	@Resource
	private JpaFlightDaoImpl flightDao;

	public AirlineServiceImpl() {
	}

	@Override
	@Transactional(readOnly = true)
	public List<Flight> getFlights() {
		return flightDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Flight getFlight(Long id) throws NoSuchFlightException {
		Flight flight = flightDao.findOne(id);
		if (flight != null) {
			return flight;
		} else {
			throw new NoSuchFlightException("Flight:" + id + " not found");
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public Flights getFlights(FlightSearchCriteria criteria) {
		String fromAirportCode = criteria.getFromAirportCode();
		String toAirportCode = criteria.getToAirportCode();

		Specification<Flight> spec=FlightSpecifications.FromToLike(fromAirportCode, toAirportCode);
		List<Flight> flights =flightDao.findAll(spec);
		Flights results = new Flights();
		results.setFlights(flights);

		return results;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Flights getFlights(Specification<Flight> spec) {

		List<Flight> flights =flightDao.findAll(spec);
		Flights results = new Flights();
		results.setFlights(flights);

		return results;
	}
	
	@Override
	public Flight save(Flight flight) {

		Flight flight1=flightDao.save(flight);
		return flight1;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Flight getFlight(String flightNumber) throws NoSuchFlightException {
		Specification<Flight> spec=FlightSpecifications.FlightNumberLike(flightNumber);
		Flight flight = flightDao.findOne(spec);
		if (flight != null) {
			return flight;
		}
		throw new NoSuchFlightException("Could not find flight:" + flightNumber);
	}

	/**
     * This setter method should be used only by unit tests.
     */
	public void setAirlineDao(JpaFlightDaoImpl flightDao) {
		// TODO Auto-generated method stub
		this.flightDao=flightDao;
	}

}
