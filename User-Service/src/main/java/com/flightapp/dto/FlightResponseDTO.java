package com.flightapp.dto;

import java.util.List;

import com.flightapp.model.PassengerDetails;

public class FlightResponseDTO {
	
	private String pNR_Number;
	private int flightId;
	private String name;
	private String emailId;
	private List<PassengerDetails> passengers;
	private int noOfPassenger;
	private String typeOfMeal;
	
	public String getPNR_Number() {
		return pNR_Number;
	}
	public void setPNR_Number(String pNR_Number) {
		this.pNR_Number = pNR_Number;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public List<PassengerDetails> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerDetails> passengers) {
		this.passengers = passengers;
	}
	public int getNoOfPassenger() {
		return noOfPassenger;
	}
	public void setNoOfPassenger(int noOfPassenger) {
		this.noOfPassenger = noOfPassenger;
	}
	public String getTypeOfMeal() {
		return typeOfMeal;
	}
	public void setTypeOfMeal(String typeOfMeal) {
		this.typeOfMeal = typeOfMeal;
	}
	
	
	

}
