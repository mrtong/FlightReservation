package com.foo.flight.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.foo.flight.dao.jpa.JpaAirportDaoImpl;
import com.foo.flight.model.Airport;
import com.foo.flight.service.interfaces.AirportService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MockDaoConfig.class,
    DefaultServiceConfig.class }, loader = AnnotationConfigContextLoader.class)
public class AirportServiceTest {
  @Autowired
  private JpaAirportDaoImpl airportDaoMock;

  @Autowired
  private ApplicationContext ctx;

  @Test
  public void getAirportCode() throws Exception {
    String code = "LAX";
    Airport airport = new Airport();

//    Mockito.when(airportDaoMock.getAirport(code)).thenReturn(airport);
    AirportService a = ctx.getBean(AirportService.class);
    assertTrue(airport == a.getAirport(code));
  }
  
  @Test
  public void getAirports() {
    List<Airport> airports = new ArrayList<Airport>();
    for (int i = 0; i < 10; i++) {
      Airport a = new Airport("Code:" + i, "Name:" + i, "City:" + i);
      airports.add(a);
    }
//    Mockito.when(airportDaoMock.getAirports()).thenReturn(airports);
    assertEquals(airports, ctx.getBean(AirportService.class).getAirports());
  }
}

