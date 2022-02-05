package com.flightapp.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.naming.directory.SearchResult;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.dto.FlightRequestDTO;
import com.flightapp.dto.FlightResponseDTO;
import com.flightapp.dto.SearchFlightDTO;
import com.flightapp.dto.SearchFlightResultDTO;
import com.flightapp.dto.FlightRegistrationDTO;
import com.flightapp.exception.UserNotFoundException;
import com.flightapp.model.SearchFlight;
import com.flightapp.model.UserRegistration;
import com.flightapp.service.UserService;


@RestController
@RequestMapping("/api/v1.0/user/flight")
@CrossOrigin("*")
public class UserController {
	
	private static final Logger log =LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	private final ModelMapper modelMapper;
	
	public UserController(UserService userService, ModelMapper modelMapper) {
		super();
		this.userService = userService;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping("/")
	public String getString() {
		return "user working";
	}
	
	@PostMapping("/booking/{flightid}")
	public ResponseEntity<FlightResponseDTO> flightRegistration(@RequestBody FlightRequestDTO
			flightRequestDTO, @PathVariable("flightid") int flightId){
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		FlightRegistrationDTO FlightRegistrationDTO =modelMapper.map(flightRequestDTO, FlightRegistrationDTO.class);
		
	FlightResponseDTO flight = userService.bookFlight(FlightRegistrationDTO,flightId);
	
	return  ResponseEntity.status(HttpStatus.CREATED).body(flight);
	}
	
	@PostMapping("/search")
	public ResponseEntity<List<SearchFlightResultDTO>> searchFlight (@RequestBody SearchFlightDTO 
			searchFlightDTO) throws URISyntaxException{
		
		List<SearchFlightResultDTO> searchFlightResultDTO = userService.searchFlight(searchFlightDTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(searchFlightResultDTO);
		
	}
	
    @GetMapping("/ticket/{pnr}")
	public ResponseEntity<FlightResponseDTO> getFlightDetails(@PathVariable("pnr") String pnr){
    	
    	FlightResponseDTO flightResponseDTO =userService.getFlightDetails(pnr);
    	
    	log.info("",flightResponseDTO);
    	if(flightResponseDTO == null) {
    		throw new UserNotFoundException("User with PNR Number--> "+pnr+" not found");
    	}
    	
    	return ResponseEntity.status(HttpStatus.OK).body(flightResponseDTO);
    }
    
    @DeleteMapping("/booking/cancel/{pnr}")
    public String deleteFlightDetails(@PathVariable("pnr") String pnr) {
    	
    	userService.deleteFlightDetails(pnr);
    	
    	return "Flight with PNR Number : "+ pnr+" is deleted";
    	
    }
    
    @GetMapping("/booking/history/{emailId}")
    public ResponseEntity<List<FlightResponseDTO>> getFlightBookingHistory(@PathVariable("emailId")
    String emailId){
    	
    	List<FlightResponseDTO> flightDetails = userService.getFlightBookingHistory(emailId);
    	
    	return  ResponseEntity.status(HttpStatus.OK).body(flightDetails);
    }
    
    @PostMapping("/register")
    public String userRegistration(@RequestBody UserRegistration userRegitration)
    {
    	userService.userRegistration(userRegitration);
    	return "User registered successfully";
    }
    
    
    @GetMapping("/booking/history1/{emailId}")
    public ResponseEntity<List<SearchFlight>> getFlightBookingByEmailId(@PathVariable("emailId")
    String emailId){
    	
    	List<SearchFlight> searchResult = userService.getFlightBookingByEmailId(emailId);
    	
    	return  ResponseEntity.status(HttpStatus.OK).body(searchResult);
    }
    

}
