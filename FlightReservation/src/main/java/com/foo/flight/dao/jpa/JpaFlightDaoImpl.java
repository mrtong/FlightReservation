package com.foo.flight.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.foo.flight.model.Flight;

public interface JpaFlightDaoImpl extends JpaRepository<Flight, Long>,JpaSpecificationExecutor<Flight>{
	
}
