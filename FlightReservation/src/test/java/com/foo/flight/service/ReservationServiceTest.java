package com.foo.flight.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.dao.jpa.JpaTicketDaoImpl;
import com.foo.flight.model.Flight;
import com.foo.flight.model.Ticket;
import com.foo.flight.model.support.TicketBuilder;
import com.foo.flight.service.exceptions.NoSeatAvailableException;
import com.foo.flight.service.exceptions.NoSuchFlightException;
import com.foo.flight.service.exceptions.NoSuchReservationException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ReservationServiceTest {
	
	Logger log = Logger.getLogger(ReservationServiceTest.class);
	
	public static Long FLIGHT_ID=1L;
	
	public static Long RESERVATION_ID=999L;
	
	@Configuration
	static class ReservationServiceTestContextConfiguration {
		
		@Bean
		public ReservationServiceImpl reservationService() {
			return new ReservationServiceImpl();
		}

		@Bean
		public JpaTicketDaoImpl ticketDao() {
			return Mockito.mock(JpaTicketDaoImpl.class);
		}
		
		@Bean
		public JpaFlightDaoImpl flightDao(){
			return Mockito.mock(JpaFlightDaoImpl.class);
		}
	}

	@Autowired
	private JpaTicketDaoImpl ticketDao;
	
	@Resource
	private  JpaFlightDaoImpl flightDao;

	@Autowired
	private ReservationServiceImpl reservationService;

	@Before
	public void setup() {
		Ticket ticket = new TicketBuilder(RESERVATION_ID) {
			{
				ticketBuilder("Luke", new LocalDate(),1);
				flight(FLIGHT_ID);
			}

		}.build(true);

		Mockito.when(ticketDao.findOne(RESERVATION_ID)).thenReturn(ticket);
	}
	
	@Test
	public void getReservationById() throws NoSuchReservationException{
		assertNotNull(reservationService);
		Ticket ticket=reservationService.getReservation(RESERVATION_ID);
		assertNotNull(ticket);
		
		Flight flight=ticket.getFlight();
		log.info("Get Reservation By Id "+RESERVATION_ID+". The return Flight is"+flight);
		assertNotNull(flight);
		assertEquals(flight.getId(),FLIGHT_ID);
	}
	
	@Test(expected=NoSuchReservationException.class)
	public void getReservationByIdNotFound() throws NoSuchReservationException{
		Ticket ticket=reservationService.getReservation(new Long(99));
	}
	
	@Test
	public void getReservations() {
		
		List<Ticket> ticketList = reservationService.getReservations();
		assertNotNull(ticketList);
	}
	
	@Test
	public void bookFlight() throws NoSeatAvailableException, NoSuchFlightException {
		
		Ticket ticket=new Ticket();
		ticket.setId(888L);
		
		Flight flight=new Flight(888L);
		ticket.setNumberOfSeats(8);
		ticket.setReservationName("Hello");
		ticket.setFlight(flight);
		
		
		Ticket t=reservationService.bookFlight(ticket);
		log.info("Try to book Flight id 1 the return Ticket is "+t);
		assertNotNull(ticket);
	}
	
}
