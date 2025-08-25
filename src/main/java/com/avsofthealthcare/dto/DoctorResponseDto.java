package com.avsofthealthcare.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DoctorResponseDto {
	private Long id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String phone;
	private String aadhaar;

	private String gender;
	private int genderId;

	private LocalDate dob;

	private String email;
	private String registrationNumber;

	private String practiceType;
	private int practiceTypeId;

	private String specialization;
	private int specializationId;

	private String qualification;

	private String pincode;
	private String city;
	private String district;
	private String state;

	private boolean agreeDeclaration;
	private String photo;

	private Boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
