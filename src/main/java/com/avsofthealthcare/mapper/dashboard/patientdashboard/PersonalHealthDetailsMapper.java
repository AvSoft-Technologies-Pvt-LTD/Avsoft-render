package com.avsofthealthcare.mapper.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.PersonalHealthDetailsRequestDto;
import com.avsofthealthcare.dto.dashboard.patientdashboard.PersonalHealthDetailsResponseDto;
import com.avsofthealthcare.entity.dashboard.patientdashboard.PersonalHealthDetails;
import com.avsofthealthcare.entity.master.BloodGroup;

public class PersonalHealthDetailsMapper {

    public static PersonalHealthDetails toEntity(PersonalHealthDetailsRequestDto dto, BloodGroup bloodGroup) {
        return PersonalHealthDetails.builder()
                .patientId(dto.getPatientId())
                .height(dto.getHeight())
                .weight(dto.getWeight())
                .bloodGroup(bloodGroup)
                .surgeries(dto.getSurgeries())
                .allergies(dto.getAllergies())
                .isSmoker(dto.getIsSmoker())
                .yearsSmoking(dto.getYearsSmoking())
                .isAlcoholic(dto.getIsAlcoholic())
                .yearsAlcoholic(dto.getYearsAlcoholic())
                .isTobacco(dto.getIsTobacco())
                .yearsTobacco(dto.getYearsTobacco())
                .build();
    }

    public static PersonalHealthDetailsResponseDto toDto(PersonalHealthDetails entity) {
        PersonalHealthDetailsResponseDto dto = new PersonalHealthDetailsResponseDto();
        dto.setId(entity.getId());
        dto.setPatientId(entity.getPatientId());
        dto.setHeight(entity.getHeight());
        dto.setWeight(entity.getWeight());

        if (entity.getBloodGroup() != null) {
            dto.setBloodGroupId(entity.getBloodGroup().getId());
            dto.setBloodGroupName(entity.getBloodGroup().getBloodGroupName());
        }

        dto.setSurgeries(entity.getSurgeries());
        dto.setAllergies(entity.getAllergies());

        dto.setIsSmoker(entity.getIsSmoker());
        dto.setYearsSmoking(entity.getYearsSmoking());

        dto.setIsAlcoholic(entity.getIsAlcoholic());
        dto.setYearsAlcoholic(entity.getYearsAlcoholic());

        dto.setIsTobacco(entity.getIsTobacco());
        dto.setYearsTobacco(entity.getYearsTobacco());

        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedBy(entity.getUpdatedBy());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}