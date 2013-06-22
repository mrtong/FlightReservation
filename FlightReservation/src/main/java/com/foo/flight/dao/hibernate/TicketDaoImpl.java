package com.foo.flight.dao.hibernate;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.foo.flight.dao.interfaces.TicketDao;
import com.foo.flight.model.Ticket;

@Repository
public class TicketDaoImpl extends HibernateDaoSupport implements TicketDao {

  public TicketDaoImpl(SessionFactory sessionFactory) {
    super.setSessionFactory(sessionFactory);
  }

  @Override
  public void save(Ticket ticket) {
    getHibernateTemplate().save(ticket);
  }

  @Override
  public List<Ticket> getTickets() {
    return getHibernateTemplate().loadAll(Ticket.class);
  }

  @Override
  public Ticket getTicket(Long ticketId) {
    return getHibernateTemplate().get(Ticket.class, ticketId);
  }
}

