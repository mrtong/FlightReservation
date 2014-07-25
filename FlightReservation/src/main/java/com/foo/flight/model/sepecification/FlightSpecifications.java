package com.foo.flight.model.sepecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import com.foo.flight.model.Flight;

public class FlightSpecifications {
	public static Logger log = Logger.getLogger(FlightSpecifications.class);
	public static Specification<Flight> FromToLike(final String FromAirPortCode, final String ToAirPortCode) {

		return new Specification<Flight>() {
			@Override
			public Predicate toPredicate(Root<Flight> FlightRoot,CriteriaQuery<?> query, CriteriaBuilder cb) {
				log.info("FromAirPortCode in toPredicate is "+FromAirPortCode);
				log.info("ToAirPortCode in toPredicate is "+ToAirPortCode);
				return cb.and(cb.like(FlightRoot.<String> get("fromAirport").<String>get("code"),FromAirPortCode), 
						cb.like(FlightRoot.<String> get("toAirport").<String>get("code"),ToAirPortCode));

			}
		};
	}
	public static Specification<Flight> FlightNumberLike(final String flightNumber) {
		log.info("flightNumber in FlightNumberLike is "+flightNumber);
		return new Specification<Flight>() {
			@Override
			public Predicate toPredicate(Root<Flight> FlightRoot,CriteriaQuery<?> query, CriteriaBuilder cb) {

				 return cb.like(cb.lower(FlightRoot.<String>get("number")),flightNumber);
			}
		};
	}
}
