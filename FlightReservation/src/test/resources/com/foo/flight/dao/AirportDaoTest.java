package com.foo.flight.dao;


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
@ContextConfiguration(classes = { JpaDefaultDaoConfig.class }, 
  loader = AnnotationConfigContextLoader.class)
public class AirportDaoTest extends AbstractDaoTest {
	@Autowired
	JpaAirportDaoImpl airportDao;
  @Test
  public void airportDaoLifeCycle() {

    Airport a  = new Airport("LAX", "Los Angeles International1", "LA");
    airportDao.save(a);
//    Airport b = airportDao.getAirport("LAX");
//    assertEquals(a, b);
//    assertTrue(airportDao.getAirports().size() > 0);
  }
}

