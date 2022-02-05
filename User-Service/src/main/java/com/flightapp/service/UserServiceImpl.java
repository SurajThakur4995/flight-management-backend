package com.flightapp.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.flightapp.dto.FlightResponseDTO;
import com.flightapp.dto.SearchFlightDTO;
import com.flightapp.dto.SearchFlightResultDTO;
import com.flightapp.exception.FlightNotFoundException;
import com.flightapp.dto.FlightRegistrationDTO;
import com.flightapp.model.FlightRegistration;
import com.flightapp.model.SearchFlight;
import com.flightapp.model.UserRegistration;
import com.flightapp.repo.FlightRepository;
import com.flightapp.repo.UserRegistrationRepository;
import com.flightapp.repo.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	private  UserRepository userRepository;
	private  ModelMapper modelMapper;
	private  RestTemplate restTemplate;
	private UserRegistrationRepository userRegistrationRepository;
	private FlightRepository flightRepository;
	
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper,RestTemplate restTemplate
			,UserRegistrationRepository userRegistrationRepository,FlightRepository flightRepository) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
		this.restTemplate =restTemplate;
		this.userRegistrationRepository=userRegistrationRepository;
		this.flightRepository=flightRepository;
	}
	

	@Override
	public FlightResponseDTO bookFlight(FlightRegistrationDTO userDTO,int flightId) {
		ModelMapper modelMapper = new ModelMapper();
		userDTO.setPNR_Number(UUID.randomUUID().toString());
		userDTO.setFlightId(flightId);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		FlightRegistration flightRegistrationDTO =modelMapper.map(userDTO, FlightRegistration.class);
		
		SearchFlight getFlight =flightRepository.findByFlightId(flightId);
		getFlight.setEmailId(userDTO.getEmailId());
		getFlight.setpNR_Number(userDTO.getPNR_Number());
		flightRepository.save(getFlight);
//		PassengerDetails passenger=new PassengerDetails();
//		passenger.setFlightRegistration(flightRegistrationDTO);
		
//		for(PassengerDetails passenger: flightRegistrationDTO.getPassengers()) {
//			passenger.setFlightRegistration(flightRegistrationDTO);
//		}
		
		
		userRepository.save(flightRegistrationDTO);
		
		return  modelMapper.map(flightRegistrationDTO,FlightResponseDTO.class);
	}



	@Override
	public List<SearchFlightResultDTO> searchFlight(SearchFlightDTO searchFlightDTO) throws URISyntaxException {
		URI uri = new URI("http://localhost:9094/api/v1.0/admin/flight/airline");
		HttpEntity<SearchFlightDTO> searchFlight = new HttpEntity<>(searchFlightDTO);
		ResponseEntity<SearchFlightResultDTO[]> data= restTemplate.exchange(uri.toString(), HttpMethod.POST, searchFlight, SearchFlightResultDTO[].class);
		List<SearchFlightResultDTO> result =Arrays.asList(data.getBody());
		List<SearchFlight> SearchFlightList  = Arrays.asList(modelMapper.map(result, SearchFlight[].class));
		
		flightRepository.saveAll(SearchFlightList);
		
//		result.stream().filter(() ->SearchFlightResultDTO. ).forEach(System.out::println);
		
		return result;
	}



	@Override
	public FlightResponseDTO getFlightDetails(String pnr) {
		
		Optional<FlightRegistration> flightRegistrationDTO = userRepository.findByPNR_Number(pnr);
		
		if(flightRegistrationDTO.isPresent()) {
			return modelMapper.map(flightRegistrationDTO.get(), FlightResponseDTO.class);
		}else {
			
		throw new FlightNotFoundException("Flight with PNR number"+pnr+"is not available!");
		
		}
	}


	@Transactional
	@Override
	public void deleteFlightDetails(String pnr) {
		 
		userRepository.deleteByPNR_Number(pnr);
		
	}



	@Override
	public List<FlightResponseDTO> getFlightBookingHistory(String emailId) {
		List<FlightRegistration> flightDetails= userRepository.findAllByEmailID(emailId);
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		List<FlightResponseDTO> flightResponseDTOList  = Arrays.asList(modelMapper.map(flightDetails, FlightResponseDTO[].class));
		if(flightResponseDTOList!=null)
		{
			return flightResponseDTOList;
		}
		else
		{
			throw new FlightNotFoundException("Flight with email Id "+emailId+" is not available!");
		}
	}


	@Override
	public void userRegistration(UserRegistration userRegitration) {

		userRegistrationRepository.save(userRegitration);
	}


	@Override
	public List<SearchFlight> getFlightBookingByEmailId(String emailId) {
		
		 List<SearchFlight> searchFlightList=flightRepository.findAllByEmailId(emailId);
		 
		return searchFlightList;
	}


}
