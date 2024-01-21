package com.example.BloodBankManagement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blood-donors")
public class BloodDonorController {
    @Autowired
    private BloodDonorService bloodDonorService;

    @PostMapping("/register")
    public BloodDonor registerDonor(@RequestBody BloodDonor donor) {
        return bloodDonorService.registerDonor(donor);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginDonor(@RequestParam String email, @RequestParam String password) {
        Optional<Object> donor = Optional.ofNullable(bloodDonorService.loginDonor(email, password));
        return donor.map(value -> new ResponseEntity<>("Login successful", HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("Invalid email or password", HttpStatus.UNAUTHORIZED));
    }

    @GetMapping("/search")
    public List<BloodDonor> searchDonors(
            @RequestParam String bloodGroup,
            @RequestParam String state,
            @RequestParam String district,
            @RequestParam String area
    ) {
        return bloodDonorService.searchDonors(bloodGroup, state, district, area);
    }

    @GetMapping("/all")
    public List<BloodDonor> getAllDonors() {
        return bloodDonorService.getAllDonors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BloodDonor> getDonorById(@PathVariable Long id) {
        Optional<BloodDonor> donor = bloodDonorService.getDonorById(id);
        return donor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BloodDonor> updateDonor(@PathVariable Long id, @RequestBody BloodDonor donor) {
        ResponseEntity<BloodDonor> updatedDonor = ((BloodDonorController) bloodDonorService).updateDonor(id, donor);
		return updatedDonor;
       
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDonor(@PathVariable Long id) {
        bloodDonorService.deleteDonor(id);
        return new ResponseEntity<>("Donor deleted successfully", HttpStatus.OK);
    }
}
