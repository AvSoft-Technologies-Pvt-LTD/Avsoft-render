package com.avsofthealthcare.mapper.dashboard.doctordashboard;

import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffResponseDTO;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;

public class StaffMapper {

	public static StaffResponseDTO toResponseDTO(StaffDetails staff) {
		return StaffResponseDTO.builder()
				.id(staff.getId())
				.fullName(staff.getFullName())
				.designationName(staff.getDesignation() != null ? staff.getDesignation().getName() : null)
				.emailId(staff.getEmailId())
				.specializationName(staff.getSpecialization() != null ? staff.getSpecialization().getSpecializationName() : null)
				.phoneNumber(staff.getPhoneNumber())
				.genderName(staff.getGender() != null ? staff.getGender().getGenderName() : null)
				.signaturePath(staff.getSignature())
				.photoPath(staff.getPhoto())
				.active(staff.getActive())
				.createdAt(staff.getCreatedAt())
				.updatedAt(staff.getUpdatedAt())
				.createdBy(staff.getCreatedBy())
				.updatedBy(staff.getUpdatedBy())
				.build();
	}
}
