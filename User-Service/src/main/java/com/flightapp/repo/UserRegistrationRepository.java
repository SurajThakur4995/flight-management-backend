package com.flightapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightapp.model.UserRegistration;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration,Integer> {

}
