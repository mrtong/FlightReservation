package com.foo.flight.dao.interfaces;
import java.util.List;

import com.foo.flight.model.Ticket;

public interface TicketDao {  
  void save(Ticket ticket);
  
  List<Ticket> getTickets();
  
  Ticket getTicket(Long ticketId);
}

