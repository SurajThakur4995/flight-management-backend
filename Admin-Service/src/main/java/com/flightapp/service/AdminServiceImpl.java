package com.flightapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.flightapp.dto.ResponseFlightDetailsDTO;
import com.flightapp.dto.UserFlightRequestDTO;
import com.flightapp.dto.UserFlightResponseDTO;
import com.flightapp.exception.FlightNotFoundException;
import com.flightapp.model.FlightDetails;
import com.flightapp.repo.AdminRepository;


@Service
public class AdminServiceImpl implements AdminService {
	
	private final AdminRepository adminRepository;
	private final ModelMapper modelMapper;

	public AdminServiceImpl(AdminRepository adminRepository,ModelMapper modelMapper) {
		super();
		this.adminRepository = adminRepository;
		this.modelMapper= modelMapper;
	}

	@Override
	public ResponseFlightDetailsDTO addFlightDetails(FlightDetails flightDetailsDTO) {
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		adminRepository.save(flightDetailsDTO);
		ResponseFlightDetailsDTO responseFlightDetailsDTO = modelMapper.map(flightDetailsDTO, ResponseFlightDetailsDTO.class);
		
		return responseFlightDetailsDTO;
	
}

	@Override
	@Transactional
	public void blockedFlight(String airline) {
		adminRepository.blockAirline(airline);
		
	}

	@Override
	public List<UserFlightResponseDTO> getFlightDetails(UserFlightRequestDTO UserFlightRequestDTO) {
		
		List<UserFlightResponseDTO> list = new ArrayList<>();
		List<FlightDetails> listOfFlights=	adminRepository.getFlightDetails(
				UserFlightRequestDTO.getFromPlace(),UserFlightRequestDTO.getToPlace()	);
		System.out.println("Inside Getflightdetails");
		System.out.println("result:"+listOfFlights);
		if(listOfFlights!=null)
		{
			for(FlightDetails flightDetails:listOfFlights)
			{
				UserFlightResponseDTO UserFlightResponseDTO = new UserFlightResponseDTO();
				System.out.println(flightDetails.getFlightNo());
				UserFlightResponseDTO.setDate(flightDetails.getStartDate());
				UserFlightResponseDTO.setFlightId(flightDetails.getFlightNo());
				UserFlightResponseDTO.setFlightName(flightDetails.getAirline());
				UserFlightResponseDTO.setFlightPrice(String.valueOf(flightDetails.getCost()));
				list.add(UserFlightResponseDTO);
			}
			return list;
		}
		else {
			throw new FlightNotFoundException("Sorry!..No fligts are available for this request.");
		}
		
		
	}

	@Override
	@Transactional
	public void unblockedFlight(String airline) {
		adminRepository.unblockAirline(airline);
		
	}

	@Override
	public List<FlightDetails> getAllFlights() {
		List<FlightDetails> FlightDetailsDTO=adminRepository.findAll();
//		List<ResponseFlightDetailsDTO> responseFlightDetailsDTO=new ArrayList<>();
//		 responseFlightDetailsDTO =  (List<ResponseFlightDetailsDTO>) modelMapper.map(FlightDetailsDTO, ResponseFlightDetailsDTO.class);
		return FlightDetailsDTO;
		
	}	

}
