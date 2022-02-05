package com.flightapp.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserFlightResponseDTO {
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date date;
	private int flightId;
	private String flightName;
	private String flightPrice;
	public UserFlightResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserFlightResponseDTO(Date date, int flightId,String flightName, String flightPrice) {
		super();
		this.date = date;
		this.flightId=flightId;
		this.flightName = flightName;
		this.flightPrice = flightPrice;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getFlightPrice() {
		return flightPrice;
	}
	public void setFlightPrice(String flightPrice) {
		this.flightPrice = flightPrice;
	}
	
	


}
