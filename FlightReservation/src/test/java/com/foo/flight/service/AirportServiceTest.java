package com.foo.flight.service;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.foo.flight.dao.jpa.JpaAirportDaoImpl;
import com.foo.flight.service.exceptions.NoSuchAirportException;

public class AirportServiceTest {
	
	Logger log = Logger.getLogger(AirportServiceTest.class);
	
	private AirportServiceImpl airportService;
	
	@Mock
	private JpaAirportDaoImpl airportDao;
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks( this );
		airportService=Mockito.spy(new AirportServiceImpl());
		ReflectionTestUtils.setField(airportService, "airportDao", airportDao);

	}
	
	@Test(expected=NoSuchAirportException.class)
	public void getAirportByCode() throws Exception {
		log.info("TEST:::getAirportByCode");
		String code = "LAX123";
		airportService.getAirport(code);
		Mockito.verify(airportDao).findOne(code);
	}
	
	@Test
	public void getAirports() {
		log.info("TEST:::getAirports");
		airportService.getAirports();
		Mockito.verify(airportDao).findAll();
	}
}
