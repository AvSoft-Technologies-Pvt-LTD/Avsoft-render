package com.avsofthealthcare.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class DoctorRegisterRequest {

	private String firstName;
	private String middleName;
	private String lastName;
	private String phone;
	private String email;
	private String aadhaar;

	private Integer genderId;         // FK → Gender
	private LocalDate dob;

	private String registrationNumber;
	private Integer practiceTypeId;   // FK → PracticeType
	private Integer specializationId; // FK → Specialization
	private String qualification;

	private String pincode;
	private String city;
	private String district;
	private String state;

	private String password;
	private String confirmPassword;
	private boolean agreeDeclaration;

	private MultipartFile photo;
}
