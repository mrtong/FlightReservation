package com.foo.flight.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.foo.flight.dao.jpa.JpaAirportDaoImpl;
import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.model.Flight;
import com.foo.flight.model.Flights;
import com.foo.flight.model.support.FlightBuilder;
import com.foo.flight.service.AirportServiceImpl;
import com.foo.flight.service.interfaces.AirlineService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class FlightControllerTest {
	
	public static Long FLIGHT_ID=1L;
	
	@InjectMocks
	FlightsController flightsController;
	
	@Mock
	private AirlineService airlineService;

	@Configuration
	static class FlightControllerTestContextConfiguration {
		
		@Bean
		public AirportServiceImpl airportService() {
			return new AirportServiceImpl();
		}
		
		@Bean
		public JpaFlightDaoImpl flightDao() {
			return Mockito.mock(JpaFlightDaoImpl.class);
		}
		
		@Bean
		public JpaAirportDaoImpl airportDao() {
			return Mockito.mock(JpaAirportDaoImpl.class);
		}
	}

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(flightsController, "airlineService", airlineService);
		Flight flight = new FlightBuilder(FLIGHT_ID){
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
	public void test_getFlights() throws Exception {

		List<Flight> flightList = airlineService.getFlights();

		assertNotNull(flightList);
		assertEquals(flightList.size(),2);
		Flight flight=flightList.get(0);
		assertEquals(flight.getId(),FLIGHT_ID);
		
		Flights flights= flightsController.getFlights();
		assertTrue(flightList == flights.getFlights());
//		Mockito.verify(airlineServiceMock, Mockito.times(1)).getFlights(criteria);
	}

}
