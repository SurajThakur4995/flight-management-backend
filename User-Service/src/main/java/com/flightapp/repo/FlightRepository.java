package com.flightapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.model.SearchFlight;

public interface FlightRepository extends JpaRepository<SearchFlight, Integer> {

	
//	@Query
//	public List<FlightRegistration> findAllByEmailID(String emailId);
	
	public SearchFlight findByFlightId(int flightId);
	
	public List<SearchFlight> findAllByEmailId(String emailId);
}
