package com.flightapp.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightapp.dto.RequestFlightDetailsDTO;
import com.flightapp.dto.ResponseFlightDetailsDTO;
import com.flightapp.dto.UserFlightRequestDTO;
import com.flightapp.dto.UserFlightResponseDTO;
import com.flightapp.model.AuthenticationRequest;
import com.flightapp.model.AuthenticationResponse;
import com.flightapp.model.FlightDetails;
import com.flightapp.service.AdminService;
import com.flightapp.service.MyUserDetailsService;
import com.flightapp.util.JwtUtil;


@RestController
@RequestMapping("/api/v1.0/admin/flight")
@CrossOrigin("*")
public class AdminController {
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	
	
	
	
	private final AdminService adminService;
	private final ModelMapper modelMapper;
	
	public AdminController(AdminService adminService, ModelMapper modelMapper) {
		this.adminService = adminService;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping("/")
	public String getString() {
		
		return "admin working";
	}
	
	//Adding inventory
	
	@PostMapping("/airline/inventory/add")
	public ResponseEntity<ResponseFlightDetailsDTO> addFlightSchedule(@RequestBody RequestFlightDetailsDTO
			requestFlightDetailsDTO){
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
//		 ObjectMapper mapper = new ObjectMapper();
//		 mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		
		FlightDetails flightDetailsDTO = modelMapper.map(requestFlightDetailsDTO, FlightDetails.class);
		
		ResponseFlightDetailsDTO responseFlightDetailsDTO = adminService.addFlightDetails(flightDetailsDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFlightDetailsDTO);
	}
	
	//to block a airline
	@GetMapping("/airline/inventory/block/{airline}")
	public String blockedAirline(@PathVariable("airline") String airline) {
		
		adminService.blockedFlight(airline);
		return "Flight has blocked";
	}
	
	//to unblock airline
	@GetMapping("/airline/inventory/unblock/{airline}")
	public String unblockedAirline(@PathVariable("airline") String airline) {
		
		adminService.unblockedFlight(airline);
		return "Flight has unblocked";
	}
	
	
	@PostMapping("/airline")
	public ResponseEntity<List<UserFlightResponseDTO>> getFlightDetails(@RequestBody UserFlightRequestDTO 
			userServiceRequestDTO){
		
	List<UserFlightResponseDTO> UserFlightResponseDTO = adminService.getFlightDetails(userServiceRequestDTO);
		return ResponseEntity.status(HttpStatus.OK).body(UserFlightResponseDTO);
	}
	
	//getting details of all the flights
	@GetMapping("/getAirline")
	public ResponseEntity<List<FlightDetails>> getAllFlights(){
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
//		FlightDetailsDTO flightDetailsDTO = modelMapper.map(requestFlightDetailsDTO, FlightDetailsDTO.class);
		
		List<FlightDetails> responseFlightDetailsDTO = adminService.getAllFlights();
		return ResponseEntity.status(HttpStatus.CREATED).body(responseFlightDetailsDTO);
	}
	
	

}
