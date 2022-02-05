package com.flightapp.exception;

public class FlightNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public FlightNotFoundException(String message) {
		super();
		this.message = message;
	}
	
}
