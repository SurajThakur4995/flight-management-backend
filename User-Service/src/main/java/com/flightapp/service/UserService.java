package com.flightapp.service;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flightapp.dto.FlightResponseDTO;
import com.flightapp.dto.SearchFlightDTO;
import com.flightapp.dto.SearchFlightResultDTO;
import com.flightapp.model.SearchFlight;
import com.flightapp.model.UserRegistration;
import com.flightapp.dto.FlightRegistrationDTO;


@Service
public interface UserService {
	
	FlightResponseDTO bookFlight(FlightRegistrationDTO userDTO,int flightId);

	List<SearchFlightResultDTO> searchFlight(SearchFlightDTO searchFlightDTO) throws URISyntaxException;

	FlightResponseDTO getFlightDetails(String pnr);

	void deleteFlightDetails(String pnr);

	List<FlightResponseDTO> getFlightBookingHistory(String emailId);

	void userRegistration(UserRegistration userRegitration);

	List<SearchFlight> getFlightBookingByEmailId(String emailId);



}
