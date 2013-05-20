package com.foo.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foo.flight.dao.interfaces.DaoConfig;
import com.foo.flight.service.interfaces.AirlineService;
import com.foo.flight.service.interfaces.AirportService;
import com.foo.flight.service.interfaces.ReservationService;
import com.foo.flight.service.interfaces.ServiceConfig;

@Configuration
public class DefaultServiceConfig implements ServiceConfig {
  @Autowired
  private DaoConfig daoConfig;
  
  @Bean
  @Override
  public AirportService getAirportService() {
    return new AirportServiceImpl(daoConfig.getAirportDao());
  }
  
  @Bean
  @Override
  public AirlineService getAirlineService() {
    return new AirlineServiceImpl(daoConfig.getFlightDao());
  }
  
  @Bean
  @Override
  public ReservationService getReservationService() {
    return new ReservationServiceImpl(daoConfig.getFlightDao(), daoConfig.getTicketDao());
  }
}

