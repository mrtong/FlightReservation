package com.foo.flight.service.exceptions;


public class NoSuchReservationException extends Exception {

  private static final long serialVersionUID = 1L;

  public NoSuchReservationException(String message) {
    super(message);
  }
}
