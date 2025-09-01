package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.PatientRegisterRequest;
import com.avsofthealthcare.dto.PatientResponseDto;
import com.avsofthealthcare.entity.PatientDetails;
import com.avsofthealthcare.entity.Role;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.master.Gender;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.PatientMapper;
import com.avsofthealthcare.mapper.UserMapper;
import com.avsofthealthcare.repository.PatientDetailsRepository;
import com.avsofthealthcare.repository.RoleRepository;
import com.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.repository.master.GenderRepository;
import com.avsofthealthcare.service.PatientService;
import com.avsofthealthcare.util.FileStorageService;
import com.avsofthealthcare.validation.UserValidationService;
import jakarta.transaction.Transactional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired private UserRepository userRepository;
	@Autowired private PatientDetailsRepository patientRepository;
	@Autowired private FileStorageService fileStorageService;
	@Autowired private UserMapper userMapper;
	@Autowired private PatientMapper patientMapper;
	@Autowired private UserValidationService userValidationService;
	@Autowired private GenderRepository genderRepository;
	@Autowired private RoleRepository roleRepository;
	@Autowired private PasswordEncoder passwordEncoder;

	@Value("${app.file.upload-dir}")
	private String uploadDir;

	private static final String PATIENT_ROLE = "PATIENT";


	@Override
	@Transactional
	public String register(PatientRegisterRequest request, MultipartFile photo) {
		try {
			if (!request.getPassword().equals(request.getConfirmPassword())) {
				throw new IllegalArgumentException("Passwords do not match");
			}

			userValidationService.validateUniqueEmailAndPhone(request.getEmail(), request.getPhone());
			String encodedPassword = passwordEncoder.encode(request.getPassword());

			Role patientRole = roleRepository.findByName(PATIENT_ROLE)
					.orElseThrow(() -> new ResourceNotFoundException("Role 'PATIENT' not found"));

			User user = User.builder()
					.email(request.getEmail())
					.phone(request.getPhone())
					.password(encodedPassword)
					.confirmPassword(encodedPassword) // ✅ Storing encoded confirmPassword
					.roles(Set.of(patientRole))
					.build();

			User savedUser = userRepository.save(user);

			String photoPath = handlePhotoUpload(photo, request.getFirstName(), savedUser.getId(), 1);

			Gender gender = genderRepository.findById(request.getGenderId())
					.orElseThrow(() -> new ResourceNotFoundException("Gender not found"));

			PatientDetails patient = PatientMapper.toEntity(
					request,
					photoPath,
					gender,
					savedUser.getId(),
					patientRole.getName()
			);

			patientRepository.save(patient);
			return "Patient registered successfully";

		} catch (Exception ex) {

			throw new RuntimeException("Patient registration failed: " + ex.getMessage(), ex);
		}
	}


	@Override
	@Transactional
	public PatientResponseDto update(Long id, PatientRegisterRequest req, MultipartFile photo) {
		PatientDetails patient = patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

		User user = userRepository.findById(patient.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));

		userValidationService.validateUniqueEmailAndPhoneForUpdate(
				patient.getUserId(), req.getEmail(), req.getPhone()
		);

		user.setEmail(req.getEmail());
		user.setPhone(req.getPhone());

		if (req.getPassword() != null && !req.getPassword().isEmpty()) {
			if (!req.getPassword().equals(req.getConfirmPassword())) {
				throw new IllegalArgumentException("Passwords do not match");
			}
			String encodedPassword = passwordEncoder.encode(req.getPassword());
			user.setPassword(encodedPassword);
			user.setConfirmPassword(encodedPassword); // ✅ Store encoded confirmPassword again
		}


		userRepository.save(user);

		Gender gender = patient.getGender();
		if (req.getGenderId() != null) {
			gender = genderRepository.findById(req.getGenderId())
					.orElseThrow(() -> new ResourceNotFoundException("Gender not found"));
		}

		String photoPath = patient.getPhoto();
		if (photo != null && !photo.isEmpty()) {
			int version = extractPhotoVersion(photoPath) + 1;
			photoPath = handlePhotoUpload(photo, req.getFirstName(), patient.getUserId(), version);
		}

		PatientMapper.updateEntity(patient, req, photoPath, gender);
		PatientDetails updatedPatient = patientRepository.save(patient);
		return PatientMapper.toResponseDto(updatedPatient);
	}

	@Override
	public PatientResponseDto getById(Long id) {
		PatientDetails patient = patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
		return PatientMapper.toResponseDto(patient);
	}

	@Override
	public List<PatientResponseDto> getAllActive() {
		return patientRepository.findAllByIsDeletedFalse()
				.stream()
				.map(PatientMapper::toResponseDto)
				.toList();
	}

	@Override
	public void softDelete(Long id) {
		PatientDetails existing = patientRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + id));
		existing.setIsDeleted(true);
		patientRepository.save(existing);
	}

	// 🔧 Utility Methods

	private String handlePhotoUpload(MultipartFile photo, String firstName, Long userId, int version) {
		if (photo == null || photo.isEmpty()) return null;

		try {
			String baseName = firstName.replaceAll("\\s+", "_") + "_" + userId + "_v" + version;
			String extension = FilenameUtils.getExtension(photo.getOriginalFilename());
			String fileName = baseName + "." + extension;

			return fileStorageService.uploadSingleFileWithName(photo, "patients/photos", fileName);
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
