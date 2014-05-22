package com.foo.flight.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.foo.flight.dao.jpa.JpaAirportDaoImpl;
import com.foo.flight.dao.jpa.JpaDefaultDaoConfig;
import com.foo.flight.model.Airport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaDefaultDaoConfig.class }, loader = AnnotationConfigContextLoader.class)
public class AirportServiceTest {
	@Autowired
	private JpaAirportDaoImpl airportDaoMock;

	@Test
	public void getAirportByCode() throws Exception {
		String code = "LAX";
		Airport airport = airportDaoMock.findOne(code);
		assertNotNull(airport);
		assertEquals("Los Angeles International", airport.getName());
	}

	@Test
	public void getAirports() {
		List<Airport> airports = airportDaoMock.findAll();
		assertNotNull(airports);
		assertTrue(airports.size() > 0);
	}
}
