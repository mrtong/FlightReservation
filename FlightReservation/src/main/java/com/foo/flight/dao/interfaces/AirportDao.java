package com.foo.flight.dao.interfaces;

import java.util.List;

import com.foo.flight.model.Airport;


public interface AirportDao {
  Airport getAirport(String code);

  void save(Airport airport);

  List<Airport> getAirports();
}