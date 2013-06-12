package com.foo.flight.web.controller;


import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.foo.flight.model.FlightSearchCriteria;
import com.foo.flight.model.Flights;
import com.foo.flight.service.interfaces.AirlineService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MockServiceConfig.class,
    ControllerConfig.class }, loader = AnnotationConfigContextLoader.class)
public class FlightControllerTest {
  @Autowired
  private ApplicationContext context;
  
  @Autowired
  private AirlineService airlineServiceMock;
  
  @Test
  public void flightController() {
    FlightsController f = context.getBean(FlightsController.class);
    FlightSearchCriteria criteria = new FlightSearchCriteria();
    criteria.setFromAirportCode("LAX");
    criteria.setToAirportCode("ORD");
    
    Flights results = new Flights();
    
    Mockito.when(airlineServiceMock.getFlights(criteria)).thenReturn(results);
    assertTrue(results == f.flightSearch(criteria));
    Mockito.verify(airlineServiceMock, Mockito.times(1)).getFlights(criteria);
  }
  // Other tests.....
}

