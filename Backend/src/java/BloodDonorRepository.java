package com.example.BloodBankManagement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BloodDonorRepository extends JpaRepository<BloodDonor, Long> {
	@Query("SELECT bd FROM BloodDonor bd WHERE bd.email = :email AND bd.password = :password")
	Optional<BloodDonor> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
    List<BloodDonor> findByBloodGroupAndStateAndDistrictAndArea(String bloodGroup, String state, String district, String area);
	@SuppressWarnings("unchecked")
	BloodDonor save(BloodDonor donor);
  
}

