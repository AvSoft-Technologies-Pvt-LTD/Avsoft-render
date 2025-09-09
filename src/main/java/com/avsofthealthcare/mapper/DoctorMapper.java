package com.avsofthealthcare.mapper;

import com.avsofthealthcare.dto.DoctorRegisterRequest;
import com.avsofthealthcare.dto.DoctorResponseDto;
import com.avsofthealthcare.entity.DoctorDetails;
import com.avsofthealthcare.entity.master.Gender;
import com.avsofthealthcare.entity.master.PracticeType;
import com.avsofthealthcare.entity.master.Specialization;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {

	// Create new doctor
	public static DoctorDetails toEntity(
			DoctorRegisterRequest req,
			String photoPath,
			Gender gender,
			PracticeType practiceType,
			Specialization specialization,
			Long userId,
			String userrole
	) {
		return DoctorDetails.builder()
				.userId(userId)
				.userrole(userrole != null ? userrole : "DOCTOR")
				.firstName(req.getFirstName())
				.middleName(req.getMiddleName())
				.lastName(req.getLastName())
				.phone(req.getPhone())
				.aadhaar(req.getAadhaar())
				.gender(gender)
				.dob(req.getDob())
				.email(req.getEmail())
				.registrationNumber(req.getRegistrationNumber())
				.practiceType(practiceType)
				.specialization(specialization)
				.qualification(req.getQualification())
				.pincode(req.getPincode())
				.city(req.getCity())
				.district(req.getDistrict())
				.state(req.getState())
				.photo(photoPath)
				.password(req.getPassword())
				.confirmPassword(req.getConfirmPassword())
				.agreeDeclaration(req.isAgreeDeclaration())
				.active(true)
				.isDeleted(false)
				.build();
	}

	// Update existing doctor
	public static void updateEntity(
			DoctorDetails doctor,
			DoctorRegisterRequest req,
			String photoPath,
			Gender gender,
			PracticeType practiceType,
			Specialization specialization
	) {
		doctor.setFirstName(req.getFirstName());
		doctor.setMiddleName(req.getMiddleName());
		doctor.setLastName(req.getLastName());
		doctor.setPhone(req.getPhone());
		doctor.setAadhaar(req.getAadhaar());
		doctor.setGender(gender);
		doctor.setDob(req.getDob());
		doctor.setEmail(req.getEmail());
		doctor.setRegistrationNumber(req.getRegistrationNumber());
		doctor.setPracticeType(practiceType);
		doctor.setSpecialization(specialization);
		doctor.setQualification(req.getQualification());
		doctor.setPincode(req.getPincode());
		doctor.setCity(req.getCity());
		doctor.setDistrict(req.getDistrict());
		doctor.setState(req.getState());
		if (photoPath != null) {
			doctor.setPhoto(photoPath);
		}
		doctor.setPassword(req.getPassword());
		doctor.setConfirmPassword(req.getConfirmPassword());
		doctor.setAgreeDeclaration(req.isAgreeDeclaration());
	}

	// Convert to response DTO
	public static DoctorResponseDto toResponseDto(DoctorDetails entity) {
		DoctorResponseDto dto = new DoctorResponseDto();
		dto.setId(entity.getId());
		dto.setFirstName(entity.getFirstName());
		dto.setMiddleName(entity.getMiddleName());
		dto.setLastName(entity.getLastName());
		dto.setPhone(entity.getPhone());
		dto.setAadhaar(entity.getAadhaar());

		dto.setGender(entity.getGender() != null ? entity.getGender().getGenderName() : null);
		dto.setGenderId(entity.getGender() != null ? entity.getGender().getId() : 0);

		dto.setDob(entity.getDob());
		dto.setEmail(entity.getEmail());
		dto.setRegistrationNumber(entity.getRegistrationNumber());

		dto.setPracticeType(entity.getPracticeType() != null ? entity.getPracticeType().getPracticeName() : null);
		dto.setPracticeTypeId(entity.getPracticeType() != null ? entity.getPracticeType().getId() : 0);

		dto.setSpecialization(entity.getSpecialization() != null ? entity.getSpecialization().getSpecializationName() : null);
		dto.setSpecializationId(entity.getSpecialization() != null ? entity.getSpecialization().getId() : 0);

		dto.setQualification(entity.getQualification());
		dto.setPincode(entity.getPincode());
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
