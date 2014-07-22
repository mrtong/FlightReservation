package com.foo.flight.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.model.Flight;
import com.foo.flight.model.FlightSearchCriteria;
import com.foo.flight.model.Flights;
import com.foo.flight.service.AirlineServiceImpl;
import com.foo.flight.service.interfaces.AirportService;
/*
 * Based on http://www.javacodegeeks.com/2013/07/getting-started-with-springs-mvc-test-framework-part-1.html
 * Create test cased by Spring Test Framework
 */

public class FlightsControllerTestStandalone {
	
	private MockMvc mockMvc;
	
	@Mock
	private JpaFlightDaoImpl flightDao;
	
	@Mock
	private AirlineServiceImpl airlineService;
	
	@Mock
	private AirportService airportService;
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		FlightsController flightsController=new FlightsController();
		ReflectionTestUtils.setField(flightsController, "airlineService", airlineService);
		ReflectionTestUtils.setField(flightsController, "airportService", airportService);
		mockMvc = MockMvcBuilders.standaloneSetup(flightsController).build();
	}
	
	
	@Test
	public void test_getFlightsByCriteria() throws Exception {
		
		FlightSearchCriteria criteria = new FlightSearchCriteria();
		criteria.setFromAirportCode("SYD");
		criteria.setToAirportCode("HK");

		Flights results = new Flights();
		List<Flight> flights = new ArrayList<Flight>();
		mockMvc.perform(post("/searchFlights.html")).andExpect(status().isOk());
		
//		Mockito.when(airlineService.getFlights(criteria)).thenReturn(results);
		//System.out.println("aaaaa="+flightsController.flightSearch(criteria));
//		assertTrue(results == flightsController.flightSearch(criteria));
//		Mockito.verify(airlineService, Mockito.times(1)).getFlights(criteria);
	}
	@Test
	public void test_getFlights() throws Exception {
		MockHttpServletRequestBuilder getRequest = get("/flights").accept(MediaType.ALL);
		ResultActions results = mockMvc.perform(getRequest);
		results.andExpect(status().isOk());
//===========================================================
		//mockMvc.perform(get("/flights").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
//		Mockito.when(airlineService.getFlights(criteria)).thenReturn(results);
		//System.out.println("aaaaa="+flightsController.getFlights());
//		assertTrue(results == flightsController.flightSearch(criteria));
//		Mockito.verify(airlineService, Mockito.times(1)).getFlights(criteria);
	}
}
