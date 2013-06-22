package com.foo.flight.dao;


import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.foo.flight.dao.hibernate.DefaultDaoConfig;
import com.foo.flight.dao.interfaces.FlightDao;
import com.foo.flight.model.Flight;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DefaultDaoConfig.class }, 
  loader = AnnotationConfigContextLoader.class)
public class FlightDaoTest extends AbstractDaoTest {
  
  @Autowired
  private FlightDao flightDao;
  
  @Test
  public void flightDaoLifeCycle() {  
    Flight f = new Flight();
    f.setFromAirport(lax);
    f.setToAirport(jfk);

    DateTime departureTime = new DateTime(new Date()).plusDays(1);
    DateTime arrivalTime = departureTime.plusHours(8);
    f.setDepartureTime(departureTime);
    f.setArrivalTime(arrivalTime);
    f.setNumber("AA 123");
    f.setSeatsAvailable(300);
    f.setMiles(800);

    flightDao.save(f);
    
    assertEquals(f, flightDao.getFlight(f.getId()));
    flightDao.decrementSeat(f.getId(), 10);
    assertEquals(f.getSeatsAvailable() - 10, flightDao.getFlight(f.getId()).getSeatsAvailable());
  }
}

