package com.foo.flight.service;


import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
//public class MockDaoConfig implements DaoConfig {
public class MockDaoConfig{
//  @Bean
//  public FlightDao getFlightDao() {
//    return Mockito.mock(FlightDao.class);
//  }
//
//  @Bean
//  public TicketDao getTicketDao() {
//    return Mockito.mock(TicketDao.class);
//  }
//
//  @Bean
//  public AirportDao getAirportDao() {
//    return Mockito.mock(AirportDao.class);
//  }
  
  @Bean
  public PlatformTransactionManager getPlatformTransactionManager() {
    return Mockito.mock(PlatformTransactionManager.class);
  }
}
