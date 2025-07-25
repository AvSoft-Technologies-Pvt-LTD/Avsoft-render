package com.avsofthealthcare.avsofthealthcare.mapper;

import com.avsofthealthcare.avsofthealthcare.dto.PatientRegisterRequest;
import com.avsofthealthcare.avsofthealthcare.entity.PatientDetails;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public PatientDetails toEntity(PatientRegisterRequest dto, Long userId, String photoPath) {
        PatientDetails entity = new PatientDetails();
        entity.setUserId(userId);
        entity.setUserrole("PATIENT");

        entity.setFirstName(dto.getFirstName());
        entity.setMiddleName(dto.getMiddleName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
        entity.setAadhaar(dto.getAadhaar());
        entity.setGender(dto.getGender());
        entity.setDob(dto.getDob());
        entity.setEmail(dto.getEmail());
        entity.setOccupation(dto.getOccupation());
        entity.setTemporaryAddress(dto.getTemporaryAddress());
        entity.setPermanentAddress(dto.getPermanentAddress());
        entity.setSameAsPermanent(dto.isSameAsPermanent());
        entity.setAgreeDeclaration(dto.isAgreDeclaration());
        entity.setPhoto(photoPath);
        entity.setPassword(dto.getPassword());
        entity.setConfirmPassword(dto.getConfirmPassword());

        return entity;
    }
}