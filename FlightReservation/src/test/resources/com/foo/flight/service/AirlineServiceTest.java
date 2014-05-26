package com.foo.flight.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.model.Airport;
import com.foo.flight.model.Flight;
import com.foo.flight.model.FlightSearchCriteria;
import com.foo.flight.model.Flights;
import com.foo.flight.model.support.FlightBuilder;
import com.foo.flight.service.exceptions.NoSuchFlightException;
/*
 * looked a lot from this URL
 * http://java.dzone.com/articles/junit-testing-spring-mvc-0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AirlineServiceTest {
	
	Logger log = Logger.getLogger(AirlineServiceTest.class);
	
	public static Long FLIGHT_ID=1L;
	
	@Configuration
	static class AirlineServiceTestContextConfiguration {
		@Bean
		public AirlineServiceImpl airlineService() {
			return new AirlineServiceImpl();
		}

		@Bean
		public JpaFlightDaoImpl flightDao() {
			return Mockito.mock(JpaFlightDaoImpl.class);
		}
	}

	@Autowired
	private JpaFlightDaoImpl flightDao;
	@Autowired
	AirlineServiceImpl airlineService;

	@Before
	public void setup() {
		Flight flight = new FlightBuilder(FLIGHT_ID){
			{
			fromAirport("SYD", "Sydney International", "Sydney");
			toAirport("HK", "Hong Kong International", "HK");
			flightBasicInfo(new DateTime(2013, 10, 3, 19, 0),new DateTime(2013, 10, 4, 9, 0),1000,3,"AV100");
			}
			
		}.build(true);

		Mockito.when(flightDao.findOne(FLIGHT_ID)).thenReturn(flight);
	}

	@Test
	public void getFlightById() throws NoSuchFlightException {

		assertNotNull(airlineService);
		Flight flight = airlineService.getFlight(FLIGHT_ID);
		log.debug(flight);
		assertNotNull(flight);
		assertEquals(flight.getId(), FLIGHT_ID);
	}
	
	@Test(expected=NoSuchFlightException.class)
	public void getFlightByIdNotFound() throws NoSuchFlightException {

		Long id = new Long(111);
		assertNotNull(airlineService);
		Flight flight = airlineService.getFlight(id);

	}
	@Test
	public void getFlights() {
		String fromAirportCode = "SYD";
		String toAirportCode = "HK";
		FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
		flightSearchCriteria.setFromAirportCode(fromAirportCode);
		flightSearchCriteria.setToAirportCode(toAirportCode);

		Flights flights = airlineService.getFlights(flightSearchCriteria);

		assertNotNull(flights);
	}

}
