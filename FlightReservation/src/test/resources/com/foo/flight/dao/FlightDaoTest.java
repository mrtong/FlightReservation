package com.foo.flight.dao;


import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.foo.flight.dao.jpa.JpaDefaultDaoConfig;
import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.model.Flight;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaDefaultDaoConfig.class }, loader = AnnotationConfigContextLoader.class)
public class FlightDaoTest extends AbstractDaoTest {
  
  @Autowired
  private JpaFlightDaoImpl flightDao;
  
  @Test
  public void flightDaoLifeCycle() {  
    Flight f = new Flight();
    f.setFromAirport(syd);
    f.setToAirport(hk);

    DateTime departureTime = new DateTime(new Date()).plusDays(1);
    DateTime arrivalTime = departureTime.plusHours(8);
    f.setDepartureTime(departureTime);
    f.setArrivalTime(arrivalTime);
    f.setNumber("AA 123");
    f.setSeatsAvailable(300);
    f.setMiles(800);
    f.setId(new Long(999));
    Flight newFlight=flightDao.save(f);
    
    flightDao.delete(newFlight);
  }
}

