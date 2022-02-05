package com.flightapp.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flightapp.model.FlightDetails;

@Repository
public interface AdminRepository extends JpaRepository<FlightDetails,Long>{
	
	@Modifying
	@Query(nativeQuery=true,value="update flight_details f set f.blocked_flight='true' where f.airline_name=?1")
	public void blockAirline(String airline);
	
	@Query(nativeQuery=true,value="select "
			+ "* from flight_details f where "
			+ "  f.from_place =?1 and f.to_place=?2 and f.blocked_flight='false'")
	public List<FlightDetails> getFlightDetails( String fromPlace,String toPlace );
	
	@Modifying
	@Query(nativeQuery=true,value="update flight_details f set f.blocked_flight='false' where f.airline_name=?1")
	public void unblockAirline(String airline);

}
