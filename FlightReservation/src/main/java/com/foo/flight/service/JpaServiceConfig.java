package com.foo.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foo.flight.dao.jpa.JpaDefaultDaoConfig;
import com.foo.flight.service.interfaces.AirlineService;
import com.foo.flight.service.interfaces.AirportService;
import com.foo.flight.service.interfaces.ReservationService;
import com.foo.flight.service.interfaces.ServiceConfig;

@Configuration
public class JpaServiceConfig implements ServiceConfig {
  @Autowired
  private JpaDefaultDaoConfig daoConfig;
  
  @Bean
  @Override
  public AirportService getAirportService() {
    return new AirportServiceImpl();
  }
  
  @Bean
  @Override
  public AirlineService getAirlineService() {
    return new AirlineServiceImpl();
  }
  
  @Bean
  @Override
  public ReservationService getReservationService() {
    return new ReservationServiceImpl();
  }
}

