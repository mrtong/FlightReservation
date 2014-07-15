package com.foo.flight.dao.jpa;


import org.springframework.data.jpa.repository.JpaRepository;

import com.foo.flight.model.Ticket;

public interface JpaTicketDaoImpl extends JpaRepository<Ticket, Long> {
	
	public void findByReservationName(String reservationName);

}

