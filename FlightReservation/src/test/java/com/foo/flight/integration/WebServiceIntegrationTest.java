package com.foo.flight.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	// private static final String BASE_URL = "http://localhost:9091";
	private MediaType contentType;
	private MediaType accept;
	@SuppressWarnings("rawtypes")
	private HttpEntity entity;
	private HttpHeaders headers;
	
	@Before
	public void setUp() {
		template = new RestTemplate();
		// template.setMessageConverters(getMessageConverters());
		// template.setMessageConverters(messageConverters);
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
	    acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setAccept(acceptableMediaTypes);
		entity = new HttpEntity(headers);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void airport() {
		
		ResponseEntity<Airports> responseEntity = template.exchange(BASE_URL+ "/airports", HttpMethod.GET, entity, Airports.class);
		Airports airports=responseEntity.getBody();
		assertNotNull(airports);
		
		ResponseEntity<Airport> jfkEntity = template.exchange(BASE_URL+ "/airports/JFK", HttpMethod.GET, entity, Airport.class);
		assertNotNull(jfkEntity.getBody());
	}

	private static final String FLIGHT_NUMBER = "LH 235";

	@Test
	public void flights() {
		
		FlightSearchCriteria criteria = new FlightSearchCriteria();
		criteria.setFromAirportCode("LAX");
		criteria.setToAirportCode("ORD");

		ResponseEntity<Flights> responseEntity = template.exchange(BASE_URL+ "/flights", HttpMethod.GET, entity, Flights.class);
		assertNotNull(responseEntity.getBody());

	}

	@Test
	@Ignore
	public void reservations() {
		Flight singleFlight = template.getForObject(BASE_URL + "/flights/"
				+ FLIGHT_NUMBER, Flight.class);

		Reservation res = new Reservation();
		res.setFlightId(singleFlight.getId());
		res.setQuantity(10);
		res.setReservationName("Sanjay Acharya");

		Ticket ticket = template.postForObject(BASE_URL + "/bookFlight", res,
				Ticket.class);
		assertNotNull(ticket);
		System.out.println("Ticket reserved:" + ticket);

		// All Reservations
		Reservations reservations = template.getForObject(BASE_URL
				+ "/reservations", Reservations.class);
		assertTrue(reservations.getTickets().size() == 1);

		// Single reservation
		assertNotNull(template.getForObject(BASE_URL + "/reservations/"
				+ ticket.getId(), Ticket.class));
	}
}
