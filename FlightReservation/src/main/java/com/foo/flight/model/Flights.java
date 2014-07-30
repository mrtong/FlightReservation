package com.foo.flight.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;
@JsonIgnoreProperties(value = { "flightCount" })
@AutoProperty
public class Flights implements java.io.Serializable {

	private static final long serialVersionUID = 8335864717095877786L;
	private List<Flight> flights;
	private int flightCount;
	public Flights() {
	}

	public Flights(List<Flight> flights) {
		this.flights = flights;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	public int getFlightCount() {
		flightCount=flights != null ? flights.size() : 0;
		return flightCount;
	}

	public String toString() {
		return Pojomatic.toString(this);
	}
}
