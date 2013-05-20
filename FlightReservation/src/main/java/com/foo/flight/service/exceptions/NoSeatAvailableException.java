package com.foo.flight.service.exceptions;

public class NoSeatAvailableException extends Exception {
	  private static final long serialVersionUID = 1L;

	  public NoSeatAvailableException(String message) {
	    super(message);
	  }
	}

