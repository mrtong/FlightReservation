package com.foo.flight.integration;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import com.foo.flight.model.Airport;
import com.foo.flight.model.Airports;
import com.foo.flight.model.Flight;
import com.foo.flight.model.FlightSearchCriteria;
import com.foo.flight.model.Flights;
import com.foo.flight.model.Reservation;
import com.foo.flight.model.Reservations;
import com.foo.flight.model.Ticket;

/**
 * Integration tests the web service calls
 */
public class WebServiceIntegrationTest {
  private RestTemplate template;

  private static final String BASE_URL = "http://localhost:8080/FlightReservation/";

  public List<HttpMessageConverter<?>> getMessageConverters() {
    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
    MarshallingHttpMessageConverter converter = new MarshallingHttpMessageConverter();
    Jaxb2Marshaller m = new Jaxb2Marshaller();
  
    m.setClassesToBeBound(Airports.class, Airport.class, Reservations.class, Reservation.class,
      Ticket.class, Flight.class, FlightSearchCriteria.class, Flights.class);

    converter.setMarshaller(m);
    converter.setUnmarshaller(m);

    converters.add(converter);
    converters.add(new StringHttpMessageConverter());
    return converters;
  }

  @Before
  public void setUp() {
    template = new RestTemplate();
    template.setMessageConverters(getMessageConverters());
  }

  @Test
  public void airport() {
    Airports airports = template.getForObject(BASE_URL + "/airports", Airports.class);
    assertTrue(airports.getAirport().size() > 0);
    System.out.println("Airports are:" + airports);

    Airport jfk = template.getForObject(BASE_URL + "/airports/JFK", Airport.class);
    assertNotNull(jfk);
    System.out.println("Single Airport:" + jfk);
  }

  private static final String FLIGHT_NUMBER = "LH 235";

  @Test
  public void flights() {
    FlightSearchCriteria criteria = new FlightSearchCriteria();
    criteria.setFromAirportCode("LAX");
    criteria.setToAirportCode("ORD");

    Flights results = template.postForObject(BASE_URL + "/searchFlights", criteria,
      Flights.class);
    
    assertTrue(results.getFlightCount() > 0);
    System.out.println("Flight Search Results:" + results);

    Flight singleFlight = template.getForObject(BASE_URL + "/flights/" + FLIGHT_NUMBER,
      Flight.class);
    assertNotNull(singleFlight);
    System.out.println("Single Flight:" + singleFlight);
  }
  
  @Test
  public void reservations() {
    Flight singleFlight = template.getForObject(BASE_URL + "/flights/" + FLIGHT_NUMBER,
      Flight.class);
    
    Reservation res = new Reservation();
    res.setFlightId(singleFlight.getId());
    res.setQuantity(10);
    res.setReservationName("Sanjay Acharya");
    
    Ticket ticket = template.postForObject(BASE_URL + "/bookFlight", res, Ticket.class);
    assertNotNull(ticket);
    System.out.println("Ticket reserved:" + ticket);
    
    // All Reservations
    Reservations reservations = template.getForObject(BASE_URL  + "/reservations", Reservations.class); 
    assertTrue(reservations.getTickets().size() == 1);
 
    // Single reservation
    assertNotNull(template.getForObject(BASE_URL + "/reservations/" + ticket.getId(), Ticket.class));
  }
}

