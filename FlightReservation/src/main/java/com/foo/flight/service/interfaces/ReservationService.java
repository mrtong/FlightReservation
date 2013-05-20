package com.foo.flight.service.interfaces;

import java.util.List;

import com.foo.flight.model.Reservation;
import com.foo.flight.model.Ticket;
import com.foo.flight.service.exceptions.NoSeatAvailableException;
import com.foo.flight.service.exceptions.NoSuchFlightException;
import com.foo.flight.service.exceptions.NoSuchReservationException;

public interface ReservationService {

	  Ticket bookFlight(Reservation reservation) throws NoSuchFlightException, NoSeatAvailableException;

	  Ticket getReservation(Long reservationId) throws NoSuchReservationException;

	  List<Ticket> getReservations();
	}
