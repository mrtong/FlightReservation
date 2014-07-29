package com.foo.flight.service;

import static org.mockito.Mockito.verify;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.foo.flight.dao.jpa.JpaTicketDaoImpl;
import com.foo.flight.model.Ticket;
import com.foo.flight.service.exceptions.NoSeatAvailableException;
import com.foo.flight.service.exceptions.NoSuchFlightException;
import com.foo.flight.service.exceptions.NoSuchReservationException;


public class ReservationServiceTest {
	
	Logger log = Logger.getLogger(ReservationServiceTest.class);
	
	public static Long RESERVATION_ID=999L;
	
	@Mock
	private JpaTicketDaoImpl ticketDao;


	private ReservationServiceImpl reservationService;

	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		reservationService=Mockito.spy(new ReservationServiceImpl());
		ReflectionTestUtils.setField(reservationService, "ticketDao", ticketDao);
	}
	
	@Test(expected=NoSuchReservationException.class)
	public void getReservationById() throws NoSuchReservationException{
		
		log.info("TEST getReservationById");
		reservationService.getReservation(RESERVATION_ID);
		verify(ticketDao, Mockito.times(1)).findOne(RESERVATION_ID);
	}
	
	@Test
	public void getReservations() {
		log.info("TEST getReservations");
		reservationService.getReservations();
		verify(ticketDao, Mockito.times(1)).findAll();
	}
	
	@Test
	public void bookFlight() throws NoSeatAvailableException, NoSuchFlightException {
		log.info("TEST bookFlight");
		Ticket ticket=new Ticket();
		reservationService.bookFlight(ticket);
		verify(ticketDao, Mockito.times(1)).save(ticket);
	}
	
}
