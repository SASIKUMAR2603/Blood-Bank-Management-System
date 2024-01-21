package com.example.BloodBankManagement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BloodDonorServiceImpl implements BloodDonorService {
    @Autowired
    private BloodDonorRepository bloodDonorRepository;

    @Override
    public BloodDonor registerDonor(BloodDonor donor) {
        return bloodDonorRepository.save(donor);
    }

    @Override
    public Optional<BloodDonor> loginDonor(String email, String password) {
        return Optional.empty();
    }

    @Override
    public List<BloodDonor> searchDonors(String bloodGroup, String state, String district, String area) {
        return bloodDonorRepository.findByBloodGroupAndStateAndDistrictAndArea(bloodGroup, state, district, area);
    }

    @Override
    public List<BloodDonor> getAllDonors() {
        return bloodDonorRepository.findAll();
    }

    @Override
    public Optional<BloodDonor> getDonorById(Long id) {
        return bloodDonorRepository.findById(id);
    }

    @Override
    public void deleteDonor(Long id) {
        bloodDonorRepository.deleteById(id);
    }

    @Override
    public BloodDonor updateDonor(Long id, BloodDonor donor) {
        Optional<BloodDonor> existingDonor = bloodDonorRepository.findById(id);

        if (existingDonor.isPresent()) {
            BloodDonor updatedDonor = existingDonor.get();
            updatedDonor.setName(donor.getName());
            updatedDonor.setAge(donor.getAge());
            updatedDonor.setGender(donor.getGender());
            updatedDonor.setBloodGroup(donor.getBloodGroup());
            updatedDonor.setMobile(donor.getMobile());
            updatedDonor.setState(donor.getState());
            updatedDonor.setDistrict(donor.getDistrict());
            updatedDonor.setArea(donor.getArea());
            updatedDonor.setAvailability(donor.isAvailability());
            updatedDonor.setEmail(donor.getEmail());
            updatedDonor.setPassword(donor.getPassword());
            return bloodDonorRepository.save(updatedDonor);
        } else {
            throw new RuntimeException("Donor not found with id: " + id);
        }
    }
}
