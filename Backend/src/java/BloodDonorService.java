package com.example.BloodBankManagement;

import java.util.List;
import java.util.Optional;

public interface BloodDonorService {
    BloodDonor registerDonor(BloodDonor donor);
    Optional<BloodDonor> loginDonor(String email, String password);
    List<BloodDonor> searchDonors(String bloodGroup, String state, String district, String area);
    List<BloodDonor> getAllDonors();
    Optional<BloodDonor> getDonorById(Long id);
    void deleteDonor(Long id);
    BloodDonor updateDonor(Long id, BloodDonor donor);
    
    
	
}
