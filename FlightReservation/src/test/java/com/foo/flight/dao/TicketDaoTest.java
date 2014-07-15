package com.foo.flight.dao;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.foo.flight.dao.jpa.JpaDefaultDaoConfig;
import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.dao.jpa.JpaTicketDaoImpl;
import com.foo.flight.model.Flight;
import com.foo.flight.model.Ticket;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JpaDefaultDaoConfig.class }, loader = AnnotationConfigContextLoader.class)
public class TicketDaoTest extends AbstractDaoTest {

	@Autowired
	private JpaTicketDaoImpl ticketDao;
	@Autowired
	private JpaFlightDaoImpl flightDao;

	@Test
	public void ticketDaoLifeCycle() {

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
		flightDao.save(f);

		Ticket t = new Ticket();
		t.setId(new Long(999));
		LocalDate issueDate = new LocalDate();
		t.setIssueDate(issueDate);
		t.setNumberOfSeats(8);
		t.setReservationName("123456");
		t.setFlight(f);
		ticketDao.save(t);

		// assertEquals(f, flightDao.getFlight(f.getId()));
		// flightDao.decrementSeat(f.getId(), 10);
		// assertEquals(f.getSeatsAvailable() - 10,
		// flightDao.getFlight(f.getId()).getSeatsAvailable());
	}
}
