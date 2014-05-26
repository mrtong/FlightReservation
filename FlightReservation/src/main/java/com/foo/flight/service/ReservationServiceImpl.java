package com.foo.flight.service;


import java.util.List;

import javax.annotation.Resource;

import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foo.flight.dao.jpa.JpaFlightDaoImpl;
import com.foo.flight.dao.jpa.JpaTicketDaoImpl;
import com.foo.flight.model.Flight;
import com.foo.flight.model.Reservation;
import com.foo.flight.model.Ticket;
import com.foo.flight.service.exceptions.NoSeatAvailableException;
import com.foo.flight.service.exceptions.NoSuchFlightException;
import com.foo.flight.service.exceptions.NoSuchReservationException;
import com.foo.flight.service.interfaces.ReservationService;

@Service
@Transactional(readOnly = true)
public class ReservationServiceImpl implements ReservationService {
  @Resource
  private  JpaFlightDaoImpl flightDao;
  @Resource
  private  JpaTicketDaoImpl ticketDao;

  public ReservationServiceImpl() {
  }

  @Override
  @Transactional(rollbackFor = { NoSeatAvailableException.class }, readOnly=false)
  public Ticket bookFlight(Reservation booking) throws  NoSeatAvailableException,NoSuchFlightException {
   
    Ticket ticket = new Ticket();
    ticket.setIssueDate(new LocalDate());
    Long flightId=booking.getFlightId();
    if(flightId==null){
    	throw new NoSuchFlightException("The Flight ID can not be NULL in the variable booking");
    }
    
    Flight flight=flightDao.findOne(flightId);
    
    if(flight==null){
    	throw new NoSuchFlightException("The Flight ID "+flightId+" can not be FOUND");
    }
    
    ticket.setFlight(flightDao.findOne(booking.getFlightId()));
    ticket.setReservationName(booking.getReservationName());
    ticket.setNumberOfSeats(booking.getQuantity());
    ticketDao.save(ticket);

    return ticket;
  }
  
  @Override
  @Transactional(rollbackFor = { NoSeatAvailableException.class }, readOnly=false)
  public Ticket bookFlight(Ticket ticket) throws  NoSeatAvailableException {
   
    ticketDao.save(ticket);

    return ticket;
  }
  @Override
  public List<Ticket> getReservations() {
    return ticketDao.findAll();
  }

  @Override
  public Ticket getReservation(Long reservationId) throws NoSuchReservationException {
    Ticket ticket = ticketDao.findOne(reservationId);
    if (ticket == null) {
      throw new NoSuchReservationException("Ticket not found:" + reservationId);
    }
    
    return ticket;
  }
}

