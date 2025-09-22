package com.avsofthealthcare.mapper;

import com.avsofthealthcare.dto.VitalDetailsResponseDTO;
import com.avsofthealthcare.entity.VitalDetails;

public class VitalDetailsMapper {

    public static VitalDetailsResponseDTO toResponseDTO(VitalDetails entity) {
        VitalDetailsResponseDTO dto = new VitalDetailsResponseDTO();
        dto.setId(entity.getId());
        dto.setHeartRate(entity.getHeartRate());
        dto.setTemperature(entity.getTemperature());
        dto.setBloodSugar(entity.getBloodSugar());
        dto.setBloodPressure(entity.getBloodPressure());
        dto.setRespiratoryRate(entity.getRespiratoryRate());
        dto.setSpO2(entity.getSpO2());
        dto.setSteps(entity.getSteps());
        dto.setPatientId(entity.getPatient().getId());
        return dto;
    }
}
