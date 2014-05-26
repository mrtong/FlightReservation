package com.foo.flight.model.support;

import org.joda.time.LocalDate;

import com.foo.flight.model.Flight;
import com.foo.flight.model.Ticket;



public class TicketBuilder extends EntityBuilder<Ticket>{

	@Override
	void initProduct() {
		this.product = new Ticket();		
	}
	public TicketBuilder(Long ticketId){
		this.product.setId(ticketId);
	}
	public TicketBuilder ticketBuilder(String reservationName,LocalDate issueDate,int numberOfSeats){
		product.setReservationName(reservationName);
		product.setIssueDate(issueDate);
		product.setNumberOfSeats(numberOfSeats);
		return this;
	}
	
	public TicketBuilder flight(Long flightId){
		Flight f=new Flight(flightId);
		product.setFlight(f);
		return this;
	}
	@Override
	Ticket assembleProduct() {
		return this.product;
	}

}
