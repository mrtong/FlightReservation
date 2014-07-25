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
import com.foo.flight.model.sepecification.FlightSpecifications;
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
		//=========
		Flight flight = new FlightBuilder(1L){
			{
			fromAirport("SYD", "Sydney International", "Sydney");
			toAirport("HK", "Hong Kong International", "HK");
			flightBasicInfo(new DateTime(2013, 10, 3, 19, 0),new DateTime(2013, 10, 4, 9, 0),1000,3,"AV100");
			}
		}.build(true);

		Flight flight1 = new FlightBuilder(2L){
			{
			fromAirport("SYD", "Sydney International", "Sydney");
			toAirport("HK", "Hong Kong International", "HK");
			flightBasicInfo(new DateTime(2014, 01, 13, 16, 15),new DateTime(2014, 01, 14, 19, 0),1000,13,"AV200");
			}
		}.build(true);
		
		List<Flight> flightList=new ArrayList<Flight>(2);
		flightList.add(flight);
		flightList.add(flight1);
		Mockito.when(airlineService.getFlights()).thenReturn(flightList);
		
	}
	
	
	@Test
	public void test_getFlightsByCriteria() throws Exception {
		
		FlightSearchCriteria criteria = new FlightSearchCriteria();
		criteria.setFromAirportCode("SYD");
		criteria.setToAirportCode("HK");

		
		List<Flight> flights = new ArrayList<Flight>();
		mockMvc.perform(post("/searchFlights.html")).andExpect(status().isOk());
		
//		Mockito.when(airlineService.getFlights(criteria)).thenReturn(results);
		//System.out.println("aaaaa="+flightsController.flightSearch(criteria));
//		assertTrue(results == flightsController.flightSearch(criteria));
		Specification<Flight> spec=FlightSpecifications.FromToLike("SYD", "HK");

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
