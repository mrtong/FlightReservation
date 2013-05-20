package com.foo.flight.service.interfaces;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public interface ServiceConfig {
  @Bean
  public AirportService getAirportService();
  
  @Bean
  public AirlineService getAirlineService();
  
  @Bean
  public ReservationService getReservationService();
}

