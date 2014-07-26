package com.foo.flight.web.controller;

import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.foo.flight.model.Airport;
import com.foo.flight.model.Flight;
import com.foo.flight.model.FlightSearchCriteria;
import com.foo.flight.model.Flights;
import com.foo.flight.model.sepecification.FlightFromToLikeSpecification;
import com.foo.flight.service.exceptions.NoSuchFlightException;
import com.foo.flight.service.interfaces.AirlineService;
import com.foo.flight.service.interfaces.AirportService;

@Controller
public class FlightsController {
	@Autowired
	private AirlineService airlineService;
	@Autowired
	private AirportService airportService;

	Logger log = Logger.getLogger(FlightsController.class);

	@RequestMapping(value = "/searchFlights.html", method = RequestMethod.GET)
	public ModelAndView searchFlights() throws Exception {
		List<Airport> airports = airportService.getAirports();

		ModelAndView mav = new ModelAndView("searchFlights");

		mav.addObject("airports", airports);

		return mav;
	}
	
	@RequestMapping(value = "/searchFlights.html", method = RequestMethod.POST)
	public ModelAndView searchFlights(FlightSearchCriteria criteria)
			throws Exception {

		ModelAndView mav = new ModelAndView("searchFlights");
		
		mav.addObject("airports", airportService.getAirports());
		
		String _fromAirport=criteria.getFromAirportCode();
		String _toAirport=criteria.getToAirportCode();
		mav.addObject("from", _fromAirport);
		mav.addObject("to", _toAirport);
		
		Specification<Flight> spec=FlightFromToLikeSpecification.FromToLike(_fromAirport, _toAirport);
		log.info("searchFlights by spec searchFlights.html");
		Flights searchResult = airlineService.getFlights(spec);
		if (searchResult != null) {
			log.info("/searchFlights.html there are "+searchResult.getFlightCount()+" Flights totally");
		}
		mav.addObject("flightSearchResult", searchResult);

		return mav;
	}
	@RequestMapping(value = "/searchFlights", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Flights flightSearch(@RequestBody FlightSearchCriteria criteria) {
		log.info("flightSearch by criteria");
		return airlineService.getFlights(criteria);
	}

	@RequestMapping(value = "/flights", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Flights getFlights() {
		log.info("URL:/flights:::method name:getFlights");
		return new Flights(airlineService.getFlights());
	}

	@RequestMapping(value = "/flights/{flightNumber}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Flight getFlightDetail(@PathVariable String flightNumber)
			throws NoSuchFlightException {
		return airlineService.getFlight(flightNumber);
	}

	@ExceptionHandler(NoSuchFlightException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error handleException(NoSuchFlightException e,
			final HttpServletRequest request, Writer writer) {
		return new Error(e.getMessage());
	}
}
