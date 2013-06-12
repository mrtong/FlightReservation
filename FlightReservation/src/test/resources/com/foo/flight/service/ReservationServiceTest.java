package com.foo.flight.service;


import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.foo.flight.dao.interfaces.FlightDao;
import com.foo.flight.model.Reservation;
import com.foo.flight.service.exceptions.NoSeatAvailableException;
import com.foo.flight.service.interfaces.ReservationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MockDaoConfig.class,
    DefaultServiceConfig.class }, loader = AnnotationConfigContextLoader.class)
public class ReservationServiceTest {
  
  @Autowired
  private FlightDao flightDaoMock;

  @Autowired
  private ApplicationContext ctx;
  
  @Test
  public void reserveSeat() throws Exception {
    Long flightId = 10L;
    int quantity = 5;

    Reservation reservation = new Reservation();
    reservation.setFlightId(flightId);
    reservation.setQuantity(5);
    
    Mockito.when(flightDaoMock.decrementSeat(flightId, quantity)).thenReturn(true);
   
    ReservationService reservationService = ctx.getBean(ReservationService.class);

    try {
      reservationService.bookFlight(reservation);
      Mockito.verify(flightDaoMock, Mockito.times(1)).decrementSeat(flightId, quantity);
    }
    catch (NoSeatAvailableException e) {
      fail("Seat booking should have happened");
    }
    
    Mockito.reset(flightDaoMock);    
    Mockito.when(flightDaoMock.decrementSeat(flightId, quantity)).thenReturn(false);
    
    try {
      reservationService.bookFlight(reservation);
      fail("Booking should have failed");
    }
    catch (NoSeatAvailableException e) {
      System.out.println(e.getMessage());
      Mockito.verify(flightDaoMock, Mockito.times(1)).decrementSeat(flightId, quantity);
    }    
  }
}

