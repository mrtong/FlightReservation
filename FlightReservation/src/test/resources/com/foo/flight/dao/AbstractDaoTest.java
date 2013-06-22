package com.foo.flight.dao;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.foo.flight.dao.interfaces.AirportDao;
import com.foo.flight.model.Airport;
import com.foo.flight.model.Flight;

public abstract class AbstractDaoTest {
	  @Autowired
	  protected AirportDao airportDao;
	  protected Airport lax;
	  protected Airport jfk;
	  
	  @Before
	  public void before() {
	    lax = new Airport("LAX", "Los Angeles International", "Los Angeles");
	    airportDao.save(lax);
	    jfk = new Airport("JFK", "JFK International", "New York");
	    airportDao.save(jfk);
	    
	    Flight f = new Flight();
	    f.setFromAirport(lax);
	    f.setToAirport(jfk);
	  }
	}
