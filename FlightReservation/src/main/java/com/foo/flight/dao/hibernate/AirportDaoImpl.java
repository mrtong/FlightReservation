package com.foo.flight.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.foo.flight.dao.interfaces.AirportDao;
import com.foo.flight.model.Airport;

@Repository
public class AirportDaoImpl extends HibernateDaoSupport implements AirportDao {
  public AirportDaoImpl(SessionFactory sessionFactory) {
    super.setSessionFactory(sessionFactory);
  }

  @Override
  public List<Airport> getAirports() {
    return  getHibernateTemplate().loadAll(Airport.class);
  }

  @Override
  public void save(Airport airport) {
    getHibernateTemplate().saveOrUpdate(airport);
  }

  @Override
  public Airport getAirport(final String code) {
//	HibernateCallback allows you to access the current transactionally bound session in order to do perform 
//	  more complex hibernate functions. Most of the time the simple methods on HibernateTemplate are sufficient, 
//	  but sometimes you need to go down to the Session.
    return getHibernateTemplate().execute(new HibernateCallback<Airport>() {

      @Override
      public Airport doInHibernate(Session session) throws HibernateException, SQLException {
        Criteria c = session.createCriteria(Airport.class);
        c.add(Restrictions.eq("code", code.toUpperCase()));
        
        @SuppressWarnings("unchecked")
        List<Airport> airports = (List<Airport>) c.list();
        return airports.size() > 0 ? airports.get(0) : null;
      }
    });
  }
}

