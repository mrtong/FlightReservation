package com.foo.flight.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

public class Flights implements java.io.Serializable {

	private static final long serialVersionUID = 8335864717095877786L;
	private List<Flight> flights;

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
		return flights != null ? flights.size() : 0;
	}

	public String toString() {
		return Pojomatic.toString(this);
	}
}
