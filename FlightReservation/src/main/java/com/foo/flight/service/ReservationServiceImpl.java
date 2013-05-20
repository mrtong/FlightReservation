package com.foo.flight.service;


import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;

import com.foo.flight.dao.interfaces.FlightDao;
import com.foo.flight.dao.interfaces.TicketDao;
import com.foo.flight.model.Reservation;
import com.foo.flight.model.Ticket;
import com.foo.flight.service.exceptions.NoSeatAvailableException;
import com.foo.flight.service.exceptions.NoSuchReservationException;
import com.foo.flight.service.interfaces.ReservationService;

@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {
  private final FlightDao flightDao;
  private final TicketDao ticketDao;

  public ReservationServiceImpl(FlightDao flightDao, TicketDao ticketDao) {
    this.flightDao = flightDao;
    this.ticketDao = ticketDao;
  }

  @Override
  @Transactional(rollbackFor = { NoSeatAvailableException.class }, readOnly=false)
  public Ticket bookFlight(Reservation booking) throws  NoSeatAvailableException {
   
    if (!flightDao.decrementSeat(booking.getFlightId(), booking.getQuantity())) {
      throw new NoSeatAvailableException("Could not book seats on flight:" + booking.getFlightId()
        + " of quantity:" + booking.getQuantity());
    }
    
    Ticket ticket = new Ticket();
    ticket.setIssueDate(new LocalDate());
    ticket.setFlight(flightDao.getFlight(booking.getFlightId()));
    ticket.setReservationName(booking.getReservationName());
    ticket.setNumberOfSeats(booking.getQuantity());

    ticketDao.save(ticket);

    return ticket;
  }

  @Override
  public List<Ticket> getReservations() {
    return ticketDao.getTickets();
  }

  @Override
  public Ticket getReservation(Long reservationId) throws NoSuchReservationException {
    Ticket ticket = ticketDao.getTicket(reservationId);
    if (ticket == null) {
      throw new NoSuchReservationException("Ticket not found:" + reservationId);
    }
    
    return ticket;
  }
}

