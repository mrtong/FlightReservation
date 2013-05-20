package com.foo.flight.dao;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.joda.time.DateTime;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.foo.flight.dao.interfaces.AirportDao;
import com.foo.flight.dao.interfaces.DaoConfig;
import com.foo.flight.dao.interfaces.FlightDao;
import com.foo.flight.dao.interfaces.TicketDao;
import com.foo.flight.model.Airport;
import com.foo.flight.model.Flight;
import com.foo.flight.model.Ticket;


@Configuration
@EnableTransactionManagement
public class DefaultDaoConfig implements DaoConfig, TransactionManagementConfigurer, InitializingBean {
  
  @Bean
  @Override
  public FlightDao getFlightDao() {
    return new FlightDaoImpl(getSessionFactory());
  }

  @Bean
  @Override
  public TicketDao getTicketDao() {
    return new TicketDaoImpl(getSessionFactory());
  }
  
  @Bean
  @Override
  public AirportDao getAirportDao() {
    return new AirportDaoImpl(getSessionFactory());
  }
  
  @Bean
  public SessionFactory getSessionFactory() {
    return new AnnotationConfiguration().addAnnotatedClass(Ticket.class)
      .addAnnotatedClass(Flight.class).addAnnotatedClass(Airport.class)
      .configure().buildSessionFactory();
  }

  @Override
  public PlatformTransactionManager annotationDrivenTransactionManager() {
    return new HibernateTransactionManager(getSessionFactory());
  }
  
  @Override
  public void afterPropertiesSet() throws Exception {
    // Initializes the Database
    Airport syd = new Airport("SYD", "Sydney International", "Sydney");
    getAirportDao().save(syd);
    Airport mel = new Airport("MEL", "Melbourn International", "Melbourn");
    getAirportDao().save(mel);
    Airport hk = new Airport("HK", "Hong Kong International", "HK");
    getAirportDao().save(hk);

    getFlightDao().save(createFlight("HK 234", syd, hk, 8, 1800, 120));
    getFlightDao().save(createFlight("LH 123", hk, syd, 8, 1800, 120));
    getFlightDao().save(createFlight("AA 222", mel, hk, 4, 590, 200));
    getFlightDao().save(createFlight("AA 112", syd, hk, 8, 1800, 120));
    getFlightDao().save(createFlight("AA 231", syd, mel, 8, 2200, 120));
    getFlightDao().save(createFlight("BB 112", mel, hk, 4, 590, 120));
    getFlightDao().save(createFlight("AA 232", mel, syd, 8, 2200, 120));
    getFlightDao().save(createFlight("CC 237", hk, syd, 8, 1800, 120));
  }
  
  private Flight createFlight(String number, Airport departure, Airport arrival, int flyinghrs,
    int miles, int seatsAvail) {
    Flight f = new Flight();
    f.setFrom(departure);
    f.setTo(arrival);

    DateTime departureTime = new DateTime(new Date()).plusDays(1);
    DateTime arrivalTime = departureTime.plusHours(flyinghrs);
    f.setDepartureTime(departureTime);
    f.setArrivalTime(arrivalTime);
    f.setNumber(number);
    f.setSeatsAvailable(seatsAvail);
    f.setMiles(miles);

    return f;
  }
}

