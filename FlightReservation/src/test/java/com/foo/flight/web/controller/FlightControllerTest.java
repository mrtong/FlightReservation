package com.foo.flight.web.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.foo.flight.model.Airport;
import com.foo.flight.model.FlightSearchCriteria;
import com.foo.flight.model.Flights;
import com.foo.flight.service.interfaces.AirlineService;
import com.foo.flight.service.interfaces.AirportService;

@RunWith(MockitoJUnitRunner.class)
public class FlightControllerTest {
	@Autowired
	private ApplicationContext context;

	@Mock
	private AirlineService airlineServiceMock;

	@InjectMocks
	FlightsController flightsController;

	@Mock
	private AirportService airportServiceMock;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_getFlights() throws Exception {

		FlightSearchCriteria criteria = new FlightSearchCriteria();
		criteria.setFromAirportCode("LAX");
		criteria.setToAirportCode("ORD");

		Flights results = new Flights();

		Mockito.when(airlineServiceMock.getFlights(criteria)).thenReturn(results);
		System.out.println(results.toString());
		assertTrue(results == flightsController.flightSearch(criteria));
		Mockito.verify(airlineServiceMock, Mockito.times(1)).getFlights(criteria);
	}
	
	@Test
	public void test_getAirport(){
		List<Airport> expectedAirports=new ArrayList<Airport>();
		Mockito.when(airportServiceMock.getAirports()).thenReturn(expectedAirports);
	}
}
