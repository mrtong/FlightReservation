package com.foo.flight.model.support;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.foo.flight.model.Airport;
import com.foo.flight.model.Flight;

public class FlightBuilder extends EntityBuilder<Flight>{

	@Override
    void initProduct() {
        this.product = new Flight();
    }
	
	public FlightBuilder(Long id){
		this.product.setId(id);
	}
	public FlightBuilder toAirport(String code, String name, String city){
		Airport airport=new Airport(code, name, city);
		this.product.setToAirport(airport);
		return this;
		
	}
	
	public FlightBuilder flightBasicInfo(DateTime arrivalTime,DateTime departureTime,int miles,int seatsAvailable,String flightNumber){
		product.setMiles(miles);
		product.setArrivalTime(arrivalTime);
		product.setDepartureTime(departureTime);
		product.setSeatsAvailable(seatsAvailable);
		product.setNumber(flightNumber);
		return this;
	}
	
	public FlightBuilder fromAirport(String code, String name, String city){
		Airport airport=new Airport(code, name, city);
		this.product.setFromAirport(airport);
		return this;
		
	}


	@Override
	Flight assembleProduct() {
		return this.product;
	}

}
