package com.foo.flight.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.foo.flight.model.Airport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DefaultDaoConfig.class }, 
  loader = AnnotationConfigContextLoader.class)
public class AirportDaoTest extends AbstractDaoTest {
  
  @Test
  public void airportDaoLifeCycle() {
    Airport a  = new Airport("LAX", "Los Angeles International", "LA");
    airportDao.save(a);
    Airport b = airportDao.getAirport("LAX");
    assertEquals(a, b);
    assertTrue(airportDao.getAirports().size() > 0);
  }
}

