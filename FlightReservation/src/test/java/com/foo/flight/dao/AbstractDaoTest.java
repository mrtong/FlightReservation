package com.foo.flight.dao;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.foo.flight.dao.jpa.JpaAirportDaoImpl;
import com.foo.flight.model.Airport;
import com.foo.flight.model.Flight;

public abstract class AbstractDaoTest {
	  @Autowired
	  protected JpaAirportDaoImpl airportDao;
	  protected Airport syd;
	  protected Airport hk;
	  
	  @Before
	  public void before() {
		syd = new Airport("SYD", "Sydney International", "Sydney");
	    airportDao.save(syd);
	    hk = new Airport("HK", "Hong Kong International", "HK");
	    airportDao.save(hk);
	    
	    Flight f = new Flight();
	    f.setFromAirport(syd);
	    f.setToAirport(hk);
	  }
	}
