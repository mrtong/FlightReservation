package com.foo.flight.service;


import static org.mockito.Mockito.mock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.foo.flight.service.interfaces.AirlineService;
import com.foo.flight.service.interfaces.AirportService;
import com.foo.flight.service.interfaces.ReservationService;
import com.foo.flight.service.interfaces.ServiceConfig;

@Configuration
//I think this class can be removed.
public class MockServiceConfig{

  @Bean(name="airlineService")
  public AirlineService getAirlineService() {
    //return mock(AirlineService.class);
	  return new AirlineServiceImpl();
  }
  @Bean
  public ReservationService getReservationService() {
    return mock(ReservationService.class);
  }
  
  @Bean
  public AirportService getAirportService() {
    return mock(AirportService.class);
  }
}
