package com.avsofthealthcare.mapper;

import com.avsofthealthcare.dto.PatientRegisterRequest;
import com.avsofthealthcare.dto.PatientResponseDto;
import com.avsofthealthcare.entity.PatientDetails;
import com.avsofthealthcare.entity.master.Gender;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

@Component
public class PatientMapper {


    // Create new patient
    public static PatientDetails toEntity(PatientRegisterRequest req, String photoPath, Gender gender, Long userId, String userrole) {



        return PatientDetails.builder()
                .userId(userId)
                .userrole("PATIENT")
                .firstName(req.getFirstName())
                .middleName(req.getMiddleName())
                .lastName(req.getLastName())
                .phone(req.getPhone())
                .aadhaar(req.getAadhaar())
                .gender(gender)
                .dob(req.getDob())
                .email(req.getEmail())
                .occupation(req.getOccupation())
                .pincode(req.getPinCode())
                .city(req.getCity())
                .district(req.getDistrict())
                .state(req.getState())
                .photo(photoPath)
                .password(req.getPassword())
                .confirmPassword(req.getConfirmPassword())
                .agreeDeclaration(req.isAgreeDeclaration())
                .active(true)                  // <-- Set this explicitly
                .isDeleted(false)
                .build();
    }

    // Update existing patient
    public static void updateEntity(PatientDetails patient, PatientRegisterRequest req, String photoPath, Gender gender) {


        patient.setFirstName(req.getFirstName());
        patient.setMiddleName(req.getMiddleName());
        patient.setLastName(req.getLastName());
        patient.setPhone(req.getPhone());
        patient.setAadhaar(req.getAadhaar());
        patient.setGender(gender);
        patient.setDob(req.getDob());
        patient.setEmail(req.getEmail());
        patient.setOccupation(req.getOccupation());
        patient.setPincode(req.getPinCode());
        patient.setCity(req.getCity());
        patient.setDistrict(req.getDistrict());
        patient.setState(req.getState());
        if (photoPath != null) {
            patient.setPhoto(photoPath);
        }
        patient.setPassword(req.getPassword());
        patient.setConfirmPassword(req.getConfirmPassword());
        patient.setAgreeDeclaration(req.isAgreeDeclaration());
    }

    public static PatientResponseDto toResponseDto(PatientDetails entity) {
        PatientResponseDto dto = new PatientResponseDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setMiddleName(entity.getMiddleName());
        dto.setLastName(entity.getLastName());
        dto.setPhone(entity.getPhone());
        dto.setAadhaar(entity.getAadhaar());

        dto.setGender(entity.getGender() != null ? entity.getGender().getGenderName() : null);
		dto.setGender_id(entity.getGender().getId());

        dto.setDob(entity.getDob());
        dto.setEmail(entity.getEmail());
        dto.setOccupation(entity.getOccupation());
        dto.setPinCode(entity.getPincode());
        dto.setCity(entity.getCity());
        dto.setDistrict(entity.getDistrict());
        dto.setState(entity.getState());
        dto.setAgreeDeclaration(entity.isAgreeDeclaration());
        dto.setPhoto(entity.getPhoto());
        dto.setActive(!entity.getIsDeleted());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }




}
