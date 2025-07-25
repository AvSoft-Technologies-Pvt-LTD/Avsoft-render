package com.avsofthealthcare.avsofthealthcare.mapper;

import com.avsofthealthcare.avsofthealthcare.dto.DoctorRegisterRequest;
import com.avsofthealthcare.avsofthealthcare.entity.DoctorDetails;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

    public DoctorDetails toEntity(DoctorRegisterRequest dto, Long userId, String photoPath, String documentsPaths) {
        DoctorDetails entity = new DoctorDetails();
        entity.setUserId(userId);
        entity.setUserrole("DOCTOR");

        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setAadhaar(dto.getAadhaar());
        entity.setGender(dto.getGender());
        entity.setDob(dto.getDob());
        entity.setEmail(dto.getEmail());
        entity.setPhoto(photoPath);
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setPracticeType(dto.getPracticeType());
        entity.setSpecialization(dto.getSpecialization());
        entity.setQualification(dto.getQualification());
        entity.setLocation(dto.getLocation());
        entity.setDocuments(documentsPaths);
        entity.setAgreeDeclaration(dto.isAgreeDeclaration());
        entity.setPassword(dto.getPassword());
        entity.setConfirmPassword(dto.getConfirmPassword());

        return entity;
    }
}
