package com.foo.flight.web.controller;


import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.foo.flight.model.Airport;
import com.foo.flight.model.Airports;
import com.foo.flight.service.exceptions.NoSuchAirportException;
import com.foo.flight.service.interfaces.AirportService;

@Controller
public class AirportController {
  private final AirportService airportService;

  public AirportController(AirportService airportService) {
    this.airportService = airportService;
  }

  @RequestMapping(value = "/airports", method = RequestMethod.GET, produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
  @ResponseBody
  public Airports getAirports() {
    return new Airports(airportService.getAirports());
  }

  @RequestMapping(value = "/airports/{code}", method = RequestMethod.GET,  produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
  @ResponseBody
  public Airport getAirport(@PathVariable String code) throws NoSuchAirportException {
    return airportService.getAirport(code);
  }
  
  @ExceptionHandler(NoSuchAirportException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Error handleException(NoSuchAirportException e, final HttpServletRequest request, Writer writer) {  
    return new Error(e.getMessage());
  }
}
