package com.foo.flight.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.model.Flight;
import com.foo.flight.model.FlightSearchCriteria;
import com.foo.flight.model.sepecification.FlightFromToLikeSpecification;
import com.foo.flight.model.support.FlightBuilder;
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
		MockHttpServletRequestBuilder getRequest = post("/searchFlights.html");
		ResultActions resultActions = mockMvc.perform(getRequest);
		resultActions.andDo(print());
		resultActions.andExpect(status().isOk());
		Specification<Flight> spec=FlightFromToLikeSpecification.FromToLike("SYD", "HK");
		/**
		 * Argument(s) are different! Wanted:
		  airlineService.getFlights(FlightFromToLikeSpecification$1@4ccd43ce);
		  -> at FlightsControllerTestStandalone.test_getFlightsByCriteria(FlightsControllerTestStandalone.java:98)
		  Actual invocation has different arguments:
		  airlineService.getFlights(FlightFlightFromToLikeSpecificationSpecifications$1@381eb0c6);
		  -> at FlightsController.searchFlights(FlightsController.java:87)
		  =========================================================================
		  Apparently the problem is although they are both use the same object FlightFromToLikeSpecification, they are using differnt FlightSpecifications instances.
		  Then the solution is easy, in the FlightFromToLikeSpecification FromToLike method make it singleton.
		 */
		Mockito.verify(airlineService).getFlights(spec);
	}
	@Test
	public void test_getFlights() throws Exception {
		final String mediaType = "application/json";
		MockHttpServletRequestBuilder getRequest = get("/flights").accept(MediaType.APPLICATION_JSON);
		ResultActions resultActions = mockMvc.perform(getRequest);
		resultActions.andDo(print());
		resultActions.andExpect(content().contentType(mediaType));
		resultActions.andExpect(status().isOk());
		Mockito.verify(airlineService).getFlights();

	}
}
