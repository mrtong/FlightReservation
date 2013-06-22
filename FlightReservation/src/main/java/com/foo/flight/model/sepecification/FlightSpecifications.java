package com.foo.flight.model.sepecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.foo.flight.model.Flight;

public class FlightSpecifications {
	public static Specification<Flight> FromToLike(final String FromAirPortCode, final String ToAirPortCode) {

		return new Specification<Flight>() {
			@Override
			public Predicate toPredicate(Root<Flight> FlightRoot,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.and(cb.like(
						cb.lower(FlightRoot.<String> get("fromAirport.code")),
						FromAirPortCode), cb.like(
						cb.lower(FlightRoot.<String> get("toAirport.code")),
						ToAirPortCode));
				// return cb.like(cb.lower(FlightRoot.<String>get("from.code")),
				// FromAirPortCode);
			}
		};
	}
	public static Specification<Flight> FlightNumberLike(final String flightNumber) {

		return new Specification<Flight>() {
			@Override
			public Predicate toPredicate(Root<Flight> FlightRoot,CriteriaQuery<?> query, CriteriaBuilder cb) {

				 return cb.like(cb.lower(FlightRoot.<String>get("number")),flightNumber);
			}
		};
	}
}
