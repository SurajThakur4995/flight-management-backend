package com.flightapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.flightapp.dto.ResponseFlightDetailsDTO;
import com.flightapp.dto.UserFlightRequestDTO;
import com.flightapp.dto.UserFlightResponseDTO;
import com.flightapp.model.FlightDetails;

@Service
public interface AdminService {

	ResponseFlightDetailsDTO addFlightDetails(FlightDetails flightDetailsDTO);

	void blockedFlight(String airline);

	List<UserFlightResponseDTO> getFlightDetails(UserFlightRequestDTO userServiceRequestDTO);

	void unblockedFlight(String airline);

	List<FlightDetails> getAllFlights();
	
	

}
