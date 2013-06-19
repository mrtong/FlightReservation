package com.foo.flight.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foo.flight.dao.interfaces.AirportDao;
import com.foo.flight.model.Airport;
import com.foo.flight.service.exceptions.NoSuchAirportException;
import com.foo.flight.service.interfaces.AirportService;

@Service
@Transactional(readOnly = true)
public class AirportServiceImpl implements AirportService {
  @Autowired
  private AirportDao airportDao;

  public AirportDao getAirportDao() {
	return airportDao;
}

public void setAirportDao(AirportDao airportDao) {
	this.airportDao = airportDao;
}

  @Override
  @Transactional(readOnly = true)
  public List<Airport> getAirports() {
    return airportDao.getAirports();
  }

  @Override
  public Airport getAirport(String code) throws NoSuchAirportException {
    Airport airport = airportDao.getAirport(code);
    if (airport != null) {
      return airport;
    }
    throw new NoSuchAirportException("Airport:" + code + " not found");
  }
}
