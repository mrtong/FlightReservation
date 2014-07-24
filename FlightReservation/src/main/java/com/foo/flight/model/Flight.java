package com.foo.flight.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;
import org.springframework.context.annotation.Lazy;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@AutoProperty
@Entity
@Table(name = "FLIGHT")
@Lazy(value = false)
public class Flight implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement(name = "flightId")
	private Long id;

	@Column(name = "NUMBER")
	@XmlElement(name = "number")
	private String number;

	@Column(name = "DEPARTURE_TIME")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@XmlElement
	@XmlJavaTypeAdapter(com.foo.flight.model.DateTimeAdapter.class)
	private DateTime departureTime;

	@ManyToOne
	@JoinColumn(name = "FROM_AIRPORT_CODE", nullable = false)
	@XmlElement(name = "fromAirport")
	private Airport fromAirport;

	public Airport getFromAirport() {
		return fromAirport;
	}

	public void setFromAirport(Airport fromAirport) {
		this.fromAirport = fromAirport;
	}

	public Airport getToAirport() {
		return toAirport;
	}

	public void setToAirport(Airport toAirport) {
		this.toAirport = toAirport;
	}

	@Column(name = "ARRIVAL_TIME")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@XmlElement
	@XmlJavaTypeAdapter(DateTimeAdapter.class)
	private DateTime arrivalTime;

	@ManyToOne
	@JoinColumn(name = "TO_AIRPORT_CODE", nullable = false)
	@XmlElement(name = "toAirport")
	private Airport toAirport;

	@Column(name = "SEATS_AVAILABLE")
	private int seatsAvailable;

	@Column(name = "MILES")
	private int miles;

	public Flight() {
	}

	public Flight(Long id) {
		this.id = id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public DateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(DateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public DateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(DateTime departureTime) {
		this.departureTime = departureTime;
	}

	public int getMiles() {
		return miles;
	}

	public void setMiles(int miles) {
		this.miles = miles;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getSeatsAvailable() {
		return seatsAvailable;
	}

	public void setSeatsAvailable(int seatsAvailable) {
		this.seatsAvailable = seatsAvailable;
	}

	public void substractSeats(int count) {
		seatsAvailable -= count;
	}

	public boolean equals(Object o) {
		return Pojomatic.equals(this, o);
	}

	public int hashCode() {
		return Pojomatic.hashCode(this);
	}

	public String toString() {
		return Pojomatic.toString(this);
	}
}
