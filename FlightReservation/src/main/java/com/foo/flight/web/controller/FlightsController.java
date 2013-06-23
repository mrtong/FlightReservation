package com.foo.flight.web.controller;


import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.foo.flight.service.exceptions.NoSuchFlightException;
import com.foo.flight.service.interfaces.AirlineService;
import com.foo.flight.service.interfaces.AirportService;

@Controller
public class FlightsController {

  private final AirlineService airlineService;
  private final AirportService airportService;

  public FlightsController(AirlineService airlineService, AirportService airportService) {
    this.airlineService = airlineService;
    this.airportService = airportService;
  }

  @RequestMapping(value = "/searchFlights.html", method = RequestMethod.GET)
  public ModelAndView searchFlights() throws Exception {
    List<Airport> airports = airportService.getAirports();

    ModelAndView mav = new ModelAndView("searchFlights");

    mav.addObject("airports", airports);

    return mav;
  }

  @RequestMapping(value = "/searchFlights.html", method = RequestMethod.POST)
  public ModelAndView searchFlights(FlightSearchCriteria criteria) throws Exception {

    ModelAndView mav = new ModelAndView("searchFlights");

    mav.addObject("airports", airportService.getAirports());

    mav.addObject("from", criteria.getFromAirportCode());
    mav.addObject("to", criteria.getToAirportCode());

    Flights searchResult = airlineService.getFlights(criteria);
    System.out.println("searchFlights="+searchResult.getFlightCount());
    mav.addObject("flightSearchResult", searchResult);

    return mav;
  }

  @RequestMapping(value = "/searchFlights", method = RequestMethod.POST, 
      consumes={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
      produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  @ResponseBody
  public Flights flightSearch(@RequestBody FlightSearchCriteria criteria) {
	  System.out.println("flightSearch");
    return airlineService.getFlights(criteria);
  }
  
  @RequestMapping(value = "/flights", method = RequestMethod.GET,
      produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  @ResponseBody
  public Flights getFlights() {
	  System.out.println("getFlights");
    return new Flights(airlineService.getFlights());
  }

  @RequestMapping(value = "/flights/{flightNumber}", method = RequestMethod.GET,
      produces={MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
  @ResponseBody
  public Flight getFlightDetail(@PathVariable String flightNumber) throws NoSuchFlightException {
    return airlineService.getFlight(flightNumber);
  }
  
  @ExceptionHandler(NoSuchFlightException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Error handleException(NoSuchFlightException e, final HttpServletRequest request, Writer writer) {  
    return new Error(e.getMessage());
  }
}

