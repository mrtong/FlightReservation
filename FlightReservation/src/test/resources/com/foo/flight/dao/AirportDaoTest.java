package com.foo.flight.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
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
public class AirportDaoTest {
	@Autowired
	JpaAirportDaoImpl airportDao;
	private Airport a;
	@Before
	public void setUp() {
		a = new Airport("TEST", "Test Airport", "TESTER");
    }

	@Test
	public void airportDaoLifeCycle() {
		
		Airport a1 = airportDao.save(a);
		assertNotNull(a1);
		assertEquals(a, a1);
		
		Airport a2 = airportDao.findOne("TEST");
		assertNotNull(a2);
		assertEquals(a, a2);
		
		airportDao.delete(a);
	}
}
