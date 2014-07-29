package com.foo.flight.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.util.ReflectionTestUtils;

import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.model.Flight;
import com.foo.flight.model.sepecification.FlightFromToLikeSpecification;
import com.foo.flight.model.sepecification.FlightNumberLikeSpecification;
import com.foo.flight.model.support.FlightBuilder;
import com.foo.flight.service.exceptions.NoSuchFlightException;

public class NewAirlineServiceTest {
	
	Logger log = Logger.getLogger(NewAirlineServiceTest.class);
	
	public static Long FLIGHT_ID=1L;


	@Mock
	private JpaFlightDaoImpl flightDao;
	
	AirlineServiceImpl airlineService;
	
	
	@Before
	public void setup() throws Exception{
		MockitoAnnotations.initMocks( this );
		airlineService=Mockito.spy(new AirlineServiceImpl());
		ReflectionTestUtils.setField(airlineService, "flightDao", flightDao);
	}

	@Test(expected=NoSuchFlightException.class)
	public void getFlightById() throws NoSuchFlightException {
		assertNotNull(airlineService);
		airlineService.getFlight(FLIGHT_ID);
		verify(flightDao, Mockito.times(1)).findOne(FLIGHT_ID);
	}
	
	@Test
	public void getFlightsBySpec() {
		String fromAirportCode = "SYD";
		String toAirportCode = "HK";
		
		Specification<Flight> spec=FlightFromToLikeSpecification.FromToLike(fromAirportCode, toAirportCode);
		airlineService.getFlights(spec);
		
		verify(flightDao, Mockito.times(1)).findAll(spec);
	}
	
	@Test
	public void getFlights() {
		List<Flight >flightList = airlineService.getFlights();

		assertNotNull(flightList);
		verify(flightDao, Mockito.times(1)).findAll();
	}
	
	@Test(expected=NoSuchFlightException.class)
	 public void getFlightsByFlightNumber() throws NoSuchFlightException {
		Specification<Flight> spec=FlightNumberLikeSpecification.FlightNumberLike("SYD");
		airlineService.getFlight("SYD");
		
		verify(flightDao, Mockito.times(1)).findOne(spec);
		verify(flightDao, Mockito.never()).findAll();
	}
	
	@Test
	public void save(){
		Flight flight = new FlightBuilder(FLIGHT_ID){
			{
			fromAirport("SYD", "Sydney International", "Sydney");
			toAirport("HK", "Hong Kong International", "HK");
			flightBasicInfo(new DateTime(2013, 10, 3, 19, 0),new DateTime(2013, 10, 4, 9, 0),1000,3,"AV100");
			}
		}.build(true);

		airlineService.save(flight);
		verify(flightDao, Mockito.times(1)).save(flight);

	}
}
