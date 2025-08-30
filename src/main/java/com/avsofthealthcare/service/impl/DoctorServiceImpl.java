package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.DoctorRegisterRequest;
import com.avsofthealthcare.dto.DoctorResponseDto;
import com.avsofthealthcare.entity.DoctorDetails;
import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.master.Gender;
import com.avsofthealthcare.entity.master.PracticeType;
import com.avsofthealthcare.entity.master.Specialization;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.DoctorMapper;
import com.avsofthealthcare.repository.DoctorDetailsRepository;
import com.avsofthealthcare.repository.RoleRepository;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.repository.master.GenderRepository;
import com.avsofthealthcare.repository.master.PracticeTypeRepository;
import com.avsofthealthcare.repository.master.SpecializationRepository;
import com.avsofthealthcare.service.DoctorService;
import com.avsofthealthcare.util.FileStorageService;
import com.avsofthealthcare.validation.UserValidationService;
import jakarta.transaction.Transactional;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired private UserRepository userRepository;
	@Autowired private DoctorDetailsRepository doctorRepository;
	@Autowired private FileStorageService fileStorageService;
	@Autowired private DoctorMapper doctorMapper;
	@Autowired private UserValidationService userValidationService;

	@Autowired private GenderRepository genderRepository;
	@Autowired private PracticeTypeRepository practiceTypeRepository;
	@Autowired private SpecializationRepository specializationRepository;
	@Autowired private RoleRepository roleRepository;
	@Autowired private PasswordEncoder passwordEncoder;

	@Value("${app.file.upload-dir}")
	private String uploadDir;

	private static final String DOCTOR_ROLE = "DOCTOR";

	@Override
	@Transactional
	public String register(DoctorRegisterRequest request, MultipartFile photo) {
		try {
			if (!request.getPassword().equals(request.getConfirmPassword())) {
				throw new IllegalArgumentException("Passwords do not match");
			}

			userValidationService.validateUniqueEmailAndPhone(request.getEmail(), request.getPhone());

			String encodedPassword = passwordEncoder.encode(request.getPassword());

			Role doctorRole = roleRepository.findByName(DOCTOR_ROLE)
					.orElseThrow(() -> new ResourceNotFoundException("Role 'DOCTOR' not found"));

			User user = User.builder()
					.email(request.getEmail())
					.phone(request.getPhone())
					.password(encodedPassword)
					.confirmPassword(encodedPassword)
					.roles(Set.of(doctorRole))
					.build();

			User savedUser = userRepository.save(user);

			String photoPath = handlePhotoUpload(photo, request.getFirstName(), savedUser.getId(), 1);

			Gender gender = genderRepository.findById(request.getGenderId())
					.orElseThrow(() -> new ResourceNotFoundException("Gender not found"));
			PracticeType practiceType = practiceTypeRepository.findById(request.getPracticeTypeId())
					.orElseThrow(() -> new ResourceNotFoundException("PracticeType not found"));
			Specialization specialization = specializationRepository.findById(request.getSpecializationId())
					.orElseThrow(() -> new ResourceNotFoundException("Specialization not found"));

			DoctorDetails doctor = DoctorMapper.toEntity(
					request,
					photoPath,
					gender,
					practiceType,
					specialization,
					savedUser.getId(),
					doctorRole.getName()
			);

			doctorRepository.save(doctor);
			return "Doctor registered successfully";

		} catch (Exception ex) {
			throw new RuntimeException("Doctor registration failed: " + ex.getMessage(), ex);
		}
	}

	@Override
	@Transactional
	public DoctorResponseDto update(Long id, DoctorRegisterRequest req, MultipartFile photo) {
		DoctorDetails doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

		User user = userRepository.findById(doctor.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		userValidationService.validateUniqueEmailAndPhoneForUpdate(
				doctor.getUserId(), req.getEmail(), req.getPhone()
		);

		user.setEmail(req.getEmail());
		user.setPhone(req.getPhone());

		if (req.getPassword() != null && !req.getPassword().isEmpty()) {
			if (!req.getPassword().equals(req.getConfirmPassword())) {
				throw new IllegalArgumentException("Passwords do not match");
			}
			String encodedPassword = passwordEncoder.encode(req.getPassword());
			user.setPassword(encodedPassword);
			user.setConfirmPassword(encodedPassword);
		}

		userRepository.save(user);

		Gender gender = doctor.getGender();
		if (req.getGenderId() != null) {
			gender = genderRepository.findById(req.getGenderId())
					.orElseThrow(() -> new ResourceNotFoundException("Gender not found"));
		}

		PracticeType practiceType = practiceTypeRepository.findById(req.getPracticeTypeId())
				.orElseThrow(() -> new ResourceNotFoundException("PracticeType not found"));
		Specialization specialization = specializationRepository.findById(req.getSpecializationId())
				.orElseThrow(() -> new ResourceNotFoundException("Specialization not found"));

		String photoPath = doctor.getPhoto();
		if (photo != null && !photo.isEmpty()) {
			int version = extractPhotoVersion(photoPath) + 1;
			photoPath = handlePhotoUpload(photo, req.getFirstName(), doctor.getUserId(), version);
		}

		DoctorMapper.updateEntity(doctor, req, photoPath, gender, practiceType, specialization);
		DoctorDetails updated = doctorRepository.save(doctor);

		return DoctorMapper.toResponseDto(updated);
	}

	@Override
	public DoctorResponseDto getById(Long id) {
		DoctorDetails doctor = doctorRepository.findByIdAndIsDeletedFalse(id)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
		return DoctorMapper.toResponseDto(doctor);
	}

	@Override
	public List<DoctorResponseDto> getAllActive() {
		return doctorRepository.findAllByIsDeletedFalse()
				.stream()
				.map(DoctorMapper::toResponseDto)
				.toList();
	}

	@Override
	public void softDelete(Long id) {
		DoctorDetails existing = doctorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + id));
		existing.setIsDeleted(true);
		doctorRepository.save(existing);
	}

	// 🔧 Utility Methods

	private String handlePhotoUpload(MultipartFile photo, String firstName, Long userId, int version) {
		if (photo == null || photo.isEmpty()) return null;

		try {
			String baseName = firstName.replaceAll("\\s+", "_") + "_" + userId + "_v" + version;
			String extension = FilenameUtils.getExtension(photo.getOriginalFilename());
			String fileName = baseName + "." + extension;

			return fileStorageService.uploadSingleFileWithName(photo, "doctors/photos", fileName);
		} catch (IOException e) {
			throw new RuntimeException("Photo upload failed", e);
		}
	}

	private int extractPhotoVersion(String photoPath) {
		if (photoPath == null || photoPath.isEmpty()) return 1;
		String fileName = Paths.get(photoPath).getFileName().toString();
		Pattern pattern = Pattern.compile("_v(\\d+)\\.");
		Matcher matcher = pattern.matcher(fileName);
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1));
		}
		return 1;
	}
}
