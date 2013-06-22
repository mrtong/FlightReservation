package com.foo.flight.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foo.flight.service.JpaServiceConfig;

@Configuration
public class ControllerConfig {
  // Auto Wire ServiceConfig so we can get the Service objects
  @Autowired
  private JpaServiceConfig serviceConfig;
  
  @Bean
  public AirportController getAirportController() {
    return new AirportController(serviceConfig.getAirportService());
  }
  
  @Bean
  public FlightsController getFlightController() {
    return new FlightsController(serviceConfig.getAirlineService(), serviceConfig.getAirportService());
  }
  
  @Bean
  public ReservationsController getReservationsController() {
    return new ReservationsController(serviceConfig.getReservationService(), serviceConfig.getAirlineService());
  }
  
  @Bean
  public RootController getRootController() {
    return new RootController();
  }
}

