package com.foo.flight.web.controller;


import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foo.flight.service.interfaces.AirlineService;
import com.foo.flight.service.interfaces.AirportService;
import com.foo.flight.service.interfaces.ReservationService;
import com.foo.flight.service.interfaces.ServiceConfig;

@Configuration
public class MockServiceConfig implements ServiceConfig {
  @Bean
  @Override
  public AirlineService getAirlineService() {
    return mock(AirlineService.class);
  }
  
  @Bean
  @Override
  public ReservationService getReservationService() {
    return mock(ReservationService.class);
  }
  
  @Bean
  @Override
  public AirportService getAirportService() {
    return mock(AirportService.class);
  }
}
