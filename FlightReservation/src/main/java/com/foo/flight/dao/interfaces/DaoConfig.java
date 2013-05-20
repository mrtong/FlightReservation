package com.foo.flight.dao.interfaces;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public interface DaoConfig {
  @Bean
  FlightDao getFlightDao();

  @Bean
  TicketDao getTicketDao();
  
  @Bean
  AirportDao getAirportDao();
}

