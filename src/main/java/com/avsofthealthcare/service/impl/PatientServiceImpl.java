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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
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

    @Override
    @Transactional
    public String register(PatientRegisterRequest request, MultipartFile photo) throws IOException {
        try {


            // Validate password match
            if (!request.getPassword().equals(request.getConfirmPassword())) {
                throw new IllegalArgumentException("Passwords do not match");
            }

            // Validate email & phone uniqueness
            userValidationService.validateUniqueEmailAndPhone(request.getEmail(), request.getPhone());
	        // 🔐 Encode password
	        String encodedPassword = passwordEncoder.encode(request.getPassword());

	        // 🔄 Get PATIENT Role dynamically from DB
	        Role patientRole = roleRepository.findByName("PATIENT")
			        .orElseThrow(() -> new ResourceNotFoundException("Role 'PATIENT' not found"));

	        Set<Role> roles = new HashSet<>();
	        roles.add(patientRole);

            // Create and save User
	        // 👤 Create User
	        User user = User.builder()
			        .email(request.getEmail())
			        .phone(request.getPhone())
			        .password(encodedPassword)
			        .roles(roles)
			        .build();

	        User savedUser = userRepository.save(user);

	        // Handle photo upload
	        String photoPath = null;
	        // Inside register method - before saving photo
	        if (photo != null && !photo.isEmpty()) {
		        Long newUserId = savedUser.getId(); // If needed, or after saving user
		        String baseName = request.getFirstName().replaceAll("\\s+", "_") + "_" + newUserId + "_v1";
		        String extension = FilenameUtils.getExtension(photo.getOriginalFilename());
		        String fileName = baseName + "." + extension;
		        photoPath = fileStorageService.uploadSingleFileWithName(photo, "patients/photos", fileName);
	        }

            // Get Gender entity
            Gender gender = genderRepository.findById(request.getGenderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Gender not found"));

            // Map and save PatientDetails
	        // 🏥 Create PatientDetails
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
            ex.printStackTrace();
            throw new IOException("Patient registration failed: " + ex.getMessage(), ex);
        }
    }





    @Override
    @Transactional
    public PatientResponseDto update(Long id, PatientRegisterRequest req, MultipartFile photo) {
        // Find existing patient
        PatientDetails patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        // 🔄 Get associated User
        User user = userRepository.findById(patient.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // ✅ Validate email and phone uniqueness (excluding current user)
        userValidationService.validateUniqueEmailAndPhoneForUpdate(
		        patient.getUserId(),
				req.getEmail(),
                req.getPhone()
        );

        // ✅ Update User info
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());

        if (req.getPassword() != null && !req.getPassword().isEmpty()) {
            if (!req.getPassword().equals(req.getConfirmPassword())) {
                throw new IllegalArgumentException("Passwords do not match");
            }
        }

        userRepository.save(user);

        // 🔄 Update gender if changed
        Gender gender = patient.getGender();
        if (req.getGenderId() != null) {
            gender = genderRepository.findById(req.getGenderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Gender not found"));
        }

	    // 🖼️ Handle photo update
	    String photoPath = patient.getPhoto();
	    if (photo != null && !photo.isEmpty()) {
		    try {
			    int version = 1;

			    if (photoPath != null && !photoPath.isEmpty()) {
				    String existingFileName = Paths.get(photoPath).getFileName().toString();
				    // Example existingFileName: John_5_v2.jpg
				    Pattern pattern = Pattern.compile("_v(\\d+)\\.");
				    Matcher matcher = pattern.matcher(existingFileName);
				    if (matcher.find()) {
					    version = Integer.parseInt(matcher.group(1)) + 1;
				    }
			    }

			    String baseName = req.getFirstName().replaceAll("\\s+", "_") + "_" + patient.getUserId() + "_v" + version;
			    String extension = FilenameUtils.getExtension(photo.getOriginalFilename());
			    String fileName = baseName + "." + extension;

			    photoPath = fileStorageService.uploadSingleFileWithName(photo, "patients/photos", fileName);

		    } catch (IOException e) {
			    throw new RuntimeException("Photo upload failed", e);
		    }
	    }

        // 🩺 Update patient details
        PatientMapper.updateEntity(patient, req, photoPath, gender);

        // Set updatedBy from authenticated user id; updatedAt set explicitly
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof com.avsofthealthcare.entity.User principal) {
            patient.setUpdatedBy(String.valueOf(principal.getId()));
        }
        patient.setUpdatedAt(LocalDateTime.now());


        // 💾 Save patient and return
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
        List<PatientDetails> patients = patientRepository.findAllByIsDeletedFalse();
        return patients.stream()
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
}
