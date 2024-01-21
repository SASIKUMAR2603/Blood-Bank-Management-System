package com.example.BloodBankManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BloodBankManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodBankManagementApplication.class, args);
		System.out.println("Blood Bank is opened");
	}

}
