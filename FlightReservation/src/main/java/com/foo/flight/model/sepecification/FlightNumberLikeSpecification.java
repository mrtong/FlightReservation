package com.foo.flight.model.sepecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import com.foo.flight.model.Flight;

public class FlightNumberLikeSpecification {
	public static Logger log = Logger.getLogger(FlightNumberLikeSpecification.class);
	
	private static Specification<Flight> spec=null;
	public static Specification<Flight> FlightNumberLike(final String flightNumber) {
		log.info("flightNumber in FlightNumberLike is "+flightNumber);
		if(spec==null){
			spec=new Specification<Flight>() {
				@Override
				public Predicate toPredicate(Root<Flight> FlightRoot,CriteriaQuery<?> query, CriteriaBuilder cb) {

					 return cb.like(cb.lower(FlightRoot.<String>get("number")),flightNumber);
				}
			};
		}
		return spec;
	}
}
