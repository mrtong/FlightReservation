package com.foo.flight.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.AutoProperty;

@XmlRootElement
@AutoProperty
@Entity
@Table(name = "AIRPORT")
@Embeddable 
public class Airport {

  @Id
  @Column(name = "CODE")
  @XmlElement
  private String code;

  @Column(name = "NAME")
  @XmlElement
  private String name;

  @Column(name = "CITY")
  @XmlElement
  private String city;

  public Airport() {}

  public Airport(String code, String name, String city) {
    this.code = code;
    this.name = name;
    this.city = city;
  }

  public String getCity() {
    return city;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public boolean equals(Object other) {
    return Pojomatic.equals(this, other);
  }

  public int hashCode() {
    return Pojomatic.hashCode(this);
  }

  @Override
  public String toString() {
    return Pojomatic.toString(this);
  }
}

