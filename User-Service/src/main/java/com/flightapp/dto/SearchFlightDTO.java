package com.flightapp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SearchFlightDTO {
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date date;
	private String fromPlace;
	private String toPlace;
	private boolean roundTrip;
	private boolean oneWayTrip;
	
	public SearchFlightDTO() {
	}
	public SearchFlightDTO(Date date, String fromPlace, String toPlace, boolean roundTrip, boolean oneWayTrip) {
		super();
		this.date = date;
		this.fromPlace = fromPlace;
		this.toPlace = toPlace;
		this.roundTrip = roundTrip;
		this.oneWayTrip = oneWayTrip;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFromPlace() {
		return fromPlace;
	}
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}
	public String getToPlace() {
		return toPlace;
	}
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	public boolean isRoundTrip() {
		return roundTrip;
	}
	public void setRoundTrip(boolean roundTrip) {
		this.roundTrip = roundTrip;
	}
	public boolean isOneWayTrip() {
		return oneWayTrip;
	}
	public void setOneWayTrip(boolean oneWayTrip) {
		this.oneWayTrip = oneWayTrip;
	}
	
	
	
	
}
