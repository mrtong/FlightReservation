package com.foo.flight.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foo.flight.dao.jpa.JpaAirportDaoImpl;
import com.foo.flight.model.Airport;
import com.foo.flight.service.exceptions.NoSuchAirportException;
import com.foo.flight.service.interfaces.AirportService;

@Service

public class AirportServiceImpl implements AirportService {
  @Resource
  private JpaAirportDaoImpl airportDao;



public AirportServiceImpl() {
    
  }

  @Override
  @Transactional(readOnly = true)
  public List<Airport> getAirports() {
	
	List<Airport> result=airportDao.findAll();
	System.out.println("yes I can get here:airportDao="+result.size());
    return result;
  }

  @Override
  @Transactional(readOnly = true)
  public Airport getAirport(String code) throws NoSuchAirportException {
    Airport airport = airportDao.findOne(code);
    if (airport != null) {
      return airport;
    }
    throw new NoSuchAirportException("Airport:" + code + " not found");
  }
}
