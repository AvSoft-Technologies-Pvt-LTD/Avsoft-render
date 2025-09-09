package com.avsofthealthcare.mapper.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.PatientVitalsRequestDTO;
import com.avsofthealthcare.dto.dashboard.patientdashboard.PatientVitalsResponseDTO;
import com.avsofthealthcare.entity.dashboard.patientdashboard.PatientVitals;

public class PatientVitalsMapper {

	/**
	 * Convert RequestDTO to Entity (for create).
	 */
	public static PatientVitals toEntity(PatientVitalsRequestDTO dto) {
		if (dto == null) {
			return null;
		}

		return PatientVitals.builder()
				.patientId(dto.getPatientId())
				.heartRate(dto.getHeartRate())
				.temperature(dto.getTemperature())
				.bloodSugar(dto.getBloodSugar())
				.bloodPressure(dto.getBloodPressure())
				.respiratoryRate(dto.getRespiratoryRate())
				.spo2(dto.getSpo2())
				.steps(dto.getSteps())
				.build();
	}

	/**
	 * Update existing Entity with values from RequestDTO.
	 */
	public static void updateEntity(PatientVitals entity, PatientVitalsRequestDTO dto) {
		if (entity == null || dto == null) {
			return;
		}

		entity.setPatientId(dto.getPatientId());
		entity.setHeartRate(dto.getHeartRate());
		entity.setTemperature(dto.getTemperature());
		entity.setBloodSugar(dto.getBloodSugar());
		entity.setBloodPressure(dto.getBloodPressure());
		entity.setRespiratoryRate(dto.getRespiratoryRate());
		entity.setSpo2(dto.getSpo2());
		entity.setSteps(dto.getSteps());
	}

	/**
	 * Convert Entity to ResponseDTO.
	 */
	public static PatientVitalsResponseDTO toResponseDTO(PatientVitals entity) {
		if (entity == null) {
			return null;
		}

		PatientVitalsResponseDTO dto = new PatientVitalsResponseDTO();
		dto.setId(entity.getId());
		dto.setPatientId(entity.getPatientId());
		dto.setHeartRate(entity.getHeartRate());
		dto.setTemperature(entity.getTemperature());
		dto.setBloodSugar(entity.getBloodSugar());
		dto.setBloodPressure(entity.getBloodPressure());
		dto.setRespiratoryRate(entity.getRespiratoryRate());
		dto.setSpo2(entity.getSpo2());
		dto.setSteps(entity.getSteps());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setUpdatedBy(entity.getUpdatedBy());
		dto.setUpdatedAt(entity.getUpdatedAt());

		return dto;
	}
}
