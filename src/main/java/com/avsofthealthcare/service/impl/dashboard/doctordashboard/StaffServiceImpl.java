package com.avsofthealthcare.service.impl.dashboard.doctordashboard;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffRequestDTO;
import com.avsofthealthcare.dto.dashboard.doctordashboard.StaffResponseDTO;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.dashboard.doctordashboard.StaffDetails;
import com.avsofthealthcare.entity.master.Designation;
import com.avsofthealthcare.entity.master.Gender;
import com.avsofthealthcare.entity.master.Specialization;
import com.avsofthealthcare.mapper.dashboard.doctordashboard.StaffMapper;
import com.avsofthealthcare.repository.dashboard.doctordashboard.StaffRepository;
import com.avsofthealthcare.repository.master.DesignationRepository;
import com.avsofthealthcare.repository.master.GenderRepository;
import com.avsofthealthcare.repository.master.SpecializationRepository;
import com.avsofthealthcare.service.dashboard.doctordashboard.StaffService;
import com.avsofthealthcare.util.FileStorageService;

@Service
@RequiredArgsConstructor
@Transactional
public class StaffServiceImpl implements StaffService {

	private final StaffRepository staffRepository;
	private final DesignationRepository designationRepository;
	private final SpecializationRepository specializationRepository;
	private final GenderRepository genderRepository;
	@Autowired
	private FileStorageService fileStorageService;

	@Value("${app.file.upload-dir}")
	private String uploadDir;

	private String handleStaffPhotoUpload(MultipartFile photo, String fullName, Long staffId, int version) {
		if (photo == null || photo.isEmpty()) return null;

		try {
			// Replace spaces with underscores for cleaner filenames
			String baseName = fullName.replaceAll("\\s+", "_") + "_" + staffId + "_v" + version;

			// Extract extension safely (e.g., jpg, png)
			String extension = FilenameUtils.getExtension(photo.getOriginalFilename());
			String fileName = baseName + "." + extension;

			// Use your file storage service to store in `staff/photos`
			return fileStorageService.uploadSingleFileWithName(photo, "staff/photos", fileName);
		} catch (IOException e) {
			throw new RuntimeException("Staff photo upload failed", e);
		}
	}


	@Override
	public StaffResponseDTO create(StaffRequestDTO dto) {
		Designation designation = designationRepository.findById(dto.getDesignationId())
				.orElseThrow(() -> new RuntimeException("Designation not found"));
		Specialization specialization = specializationRepository.findById(dto.getSpecializationId())
				.orElseThrow(() -> new RuntimeException("Specialization not found"));
		Gender gender = genderRepository.findById(dto.getGenderId())
				.orElseThrow(() -> new RuntimeException("Gender not found"));

		StaffDetails staff = StaffDetails.builder()
				.fullName(dto.getFullName())
				.designation(designation)
				.emailId(dto.getEmailId())
				.specialization(specialization)
				.phoneNumber(dto.getPhoneNumber())
				.password(dto.getPassword())
				.gender(gender)
				.signature(dto.getSignature())
				.active(true)
				.isDeleted(false)
				.build();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof User principal) {
			staff.setCreatedBy(String.valueOf(principal.getId()));
		}
		staff.setCreatedAt(LocalDateTime.now());

		staff = staffRepository.save(staff);

		// Now upload photo with unique name after staffId is generated
		String photoPath = handleStaffPhotoUpload(dto.getPhoto(), staff.getFullName(), staff.getId(), 1);
		staff.setPhoto(photoPath);

		staff = staffRepository.save(staff);
		return StaffMapper.toResponseDTO(staff);
	}


	@Override
	public StaffResponseDTO update(Long id, StaffRequestDTO dto) {
		StaffDetails staff = staffRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Staff not found"));

		staff.setFullName(dto.getFullName());
		staff.setEmailId(dto.getEmailId());
		staff.setPhoneNumber(dto.getPhoneNumber());
		staff.setPassword(dto.getPassword());

		if (dto.getDesignationId() != null) {
			staff.setDesignation(designationRepository.findById(dto.getDesignationId())
					.orElseThrow(() -> new RuntimeException("Designation not found")));
		}
		if (dto.getSpecializationId() != null) {
			staff.setSpecialization(specializationRepository.findById(dto.getSpecializationId())
					.orElseThrow(() -> new RuntimeException("Specialization not found")));
		}
		if (dto.getGenderId() != null) {
			staff.setGender(genderRepository.findById(dto.getGenderId())
					.orElseThrow(() -> new RuntimeException("Gender not found")));
		}

		staff.setSignature(dto.getSignature());

		// New photo upload (with incremented version number)
		if (dto.getPhoto() != null && !dto.getPhoto().isEmpty()) {
			String photoPath = handleStaffPhotoUpload(dto.getPhoto(), staff.getFullName(), staff.getId(), 2); // 🔄 Increment version
			staff.setPhoto(photoPath);
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof User principal) {
			staff.setUpdatedBy(String.valueOf(principal.getId()));
		}
		staff.setUpdatedAt(LocalDateTime.now());

		staff = staffRepository.save(staff);
		return StaffMapper.toResponseDTO(staff);
	}


	@Override
	public void delete(Long id) {
		staffRepository.deleteById(id);
	}

	@Override
	public StaffResponseDTO getById(Long id) {
		return staffRepository.findById(id)
				.map(StaffMapper::toResponseDTO)
				.orElseThrow(() -> new RuntimeException("Staff not found"));
	}

	@Override
	public List<StaffResponseDTO> getAll() {
		return staffRepository.findAll()
				.stream()
				.map(StaffMapper::toResponseDTO)
				.collect(Collectors.toList());
	}
}
