package com.foo.flight.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foo.flight.model.Airport;

public interface JpaAirportDaoImpl extends JpaRepository<Airport, String>{
  
}

