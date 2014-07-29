package com.foo.flight.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foo.flight.dao.jpa.JpaAirportDaoImpl;
import com.foo.flight.model.Airport;
import com.foo.flight.model.support.AirportBuilder;
import com.foo.flight.service.AirportServiceImpl;
import com.foo.flight.service.exceptions.NoSuchAirportException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AirportServiceIntegrationTest {
	
	@Configuration
	static class AirportServiceTestContextConfiguration {
		@Bean
		public AirportServiceImpl airlineService() {
			return new AirportServiceImpl();
		}

		@Bean
		public JpaAirportDaoImpl flightDao() {
			return Mockito.mock(JpaAirportDaoImpl.class);
		}
	}
	@Autowired
	private AirportServiceImpl airportServiceMock;
	
	@Autowired
	private JpaAirportDaoImpl airportDaoMock;
	
	@Before
	public void setup() {
		Airport airport = new AirportBuilder(){
			{
				buildAirport("LAX","Los Angeles International","Los Angeles");
			}
			
		}.build(true);

		Mockito.when(airportDaoMock.findOne("LAX")).thenReturn(airport);
	}
	


	@Test
	public void getAirportByCode() throws Exception {
		String code = "LAX";
		Airport airport = airportServiceMock.getAirport(code);
		assertNotNull(airport);
		assertEquals("Los Angeles International", airport.getName());
	}
	
	@Test(expected=NoSuchAirportException.class)
	public void getAirportByCodeNotFound() throws Exception {
		String code = "LAX123";
		Airport airport = airportServiceMock.getAirport(code);
		//assertEquals("Los Angeles International", airport.getName());
	}
	
	@Test
	public void getAirports() {
		List<Airport> airports = airportServiceMock.getAirports();
		assertNotNull(airports);
	}
}
