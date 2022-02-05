package com.flightapp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name ="Flight_Registration_Details")
public class FlightRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="PNR_Number",unique=true)
	private String pNR_Number;
	@Column(name="Flight_Id",nullable=true)
	private int flightId;
	@Column(name="Name")
	private String name;
	@Column(name="EmailID")
	private String emailID;
//	@OneToMany(cascade =CascadeType.ALL,mappedBy="flightRegistration",fetch = FetchType.EAGER)
	@OneToMany(cascade =CascadeType.ALL,fetch= FetchType.LAZY)
	private List<PassengerDetails> passengers;
	@Column(name="No_Of_Passenger")
	private int noOfPassenger;
	@Column(name="Type_Of_Meal")
	private String typeOfMeal;
	
	
	
	public FlightRegistration() {
		super();
	}
	
	
	public FlightRegistration(int id, String pNR_Number, int flightId, String name, String emailID,
			List<PassengerDetails> passengers, int noOfPassenger, boolean meal, String typeOfMeal) {
		super();
		this.id = id;
		this.pNR_Number = pNR_Number;
		this.flightId = flightId;
		this.name = name;
		this.emailID = emailID;
		this.passengers = passengers;
		this.noOfPassenger = noOfPassenger;
		this.typeOfMeal = typeOfMeal;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPNR_Number() {
		return pNR_Number;
	}
	public void setPNR_Number(String pNR_Number) {
		this.pNR_Number = pNR_Number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public int getNoOfPassenger() {
		return noOfPassenger;
	}
	public void setNoOfPassenger(int noOfPassenger) {
		this.noOfPassenger = noOfPassenger;
	}
	
	public int getFlightId() {
		return flightId;
	}
	public String getTypeOfMeal() {
		return typeOfMeal;
	}


	public void setTypeOfMeal(String typeOfMeal) {
		this.typeOfMeal = typeOfMeal;
	}


	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public List<PassengerDetails> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PassengerDetails> passengers) {
		this.passengers = passengers;
	}


	
	
	
	
	
	
	

}
