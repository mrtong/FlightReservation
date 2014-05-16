package com.foo.flight.service;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.model.Flight;
import com.foo.flight.model.FlightSearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    DefaultServiceConfig.class }, loader = AnnotationConfigContextLoader.class)
public class AirlineServiceTest {
 
  @Autowired
  private JpaFlightDaoImpl flightDaoMock;

  @Test
  public void searchFlights() {
    FlightSearchCriteria criteria = new FlightSearchCriteria();
    criteria.setFromAirportCode("LAX");
    criteria.setToAirportCode("ORD");

    List<Flight> flights = new ArrayList<Flight>();
    flightDaoMock.findOne(new Long(1));
//    Mockito.when(flightDaoMock.findFlights("LAX", "ORD")).thenReturn(flights);

//    AirlineService a = new AirlineServiceImpl(flightDaoMock);
//    Flights results = a.getFlights(criteria);
//    assertTrue(flights == results.getFlights());
//    Mockito.verify(flightDaoMock, Mockito.times(1)).findFlights("LAX", "ORD");
  }
}

