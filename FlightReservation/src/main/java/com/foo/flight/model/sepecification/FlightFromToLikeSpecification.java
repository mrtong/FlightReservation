package com.foo.flight.model.sepecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import com.foo.flight.model.Flight;

public class FlightFromToLikeSpecification {
	public static Logger log = Logger.getLogger(FlightFromToLikeSpecification.class);
	
	private static Specification<Flight> fromToLikeSpec=null;
	public static Specification<Flight> FromToLike(final String FromAirPortCode, final String ToAirPortCode) {
		if(fromToLikeSpec==null){
			fromToLikeSpec=new Specification<Flight>() {
				@Override
				public Predicate toPredicate(Root<Flight> FlightRoot,CriteriaQuery<?> query, CriteriaBuilder cb) {
					log.info("FromAirPortCode in toPredicate is "+FromAirPortCode);
					log.info("ToAirPortCode in toPredicate is "+ToAirPortCode);
					return cb.and(cb.like(FlightRoot.<String> get("fromAirport").<String>get("code"),FromAirPortCode), 
							cb.like(FlightRoot.<String> get("toAirport").<String>get("code"),ToAirPortCode));

				}
			};
		}
		return fromToLikeSpec;
	}
}
