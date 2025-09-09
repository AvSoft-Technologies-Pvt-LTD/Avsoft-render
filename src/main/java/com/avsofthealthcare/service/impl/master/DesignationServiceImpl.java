package com.avsofthealthcare.service.impl.master;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.avsofthealthcare.dto.master.DesignationRequestDTO;
import com.avsofthealthcare.dto.master.DesignationResponseDTO;
import com.avsofthealthcare.entity.User;
import com.avsofthealthcare.entity.master.Designation;
import com.avsofthealthcare.mapper.master.DesignationMapper;
import com.avsofthealthcare.repository.master.DesignationRepository;
import com.avsofthealthcare.service.master.DesignationService;

@Service
@RequiredArgsConstructor
@Transactional
public class DesignationServiceImpl implements DesignationService {

	private final DesignationRepository designationRepository;

	@Override
	public DesignationResponseDTO create(DesignationRequestDTO dto) {
		Designation designation = DesignationMapper.toEntity(dto);

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()
				&& authentication.getPrincipal() instanceof User principal) {
			designation.setCreatedBy(String.valueOf(principal.getId()));
		}
		designation.setCreatedAt(LocalDateTime.now());

		designation = designationRepository.save(designation);
		return DesignationMapper.toResponseDTO(designation);
	}

	@Override
	public DesignationResponseDTO update(Long id, DesignationRequestDTO dto) {
		Designation designation = designationRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Designation not found"));

		designation.setName(dto.getName());

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated()
				&& authentication.getPrincipal() instanceof User principal) {
			designation.setUpdatedBy(String.valueOf(principal.getId()));
		}
		designation.setUpdatedAt(LocalDateTime.now());

		designation = designationRepository.save(designation);
		return DesignationMapper.toResponseDTO(designation);
	}

	@Override
	public void delete(Long id) {
		designationRepository.deleteById(id);
	}

	@Override
	public DesignationResponseDTO getById(Long id) {
		return designationRepository.findById(id)
				.map(DesignationMapper::toResponseDTO)
				.orElseThrow(() -> new RuntimeException("Designation not found"));
	}

	@Override
	public List<DesignationResponseDTO> getAll() {
		return designationRepository.findAll()
				.stream()
				.map(DesignationMapper::toResponseDTO)
				.collect(Collectors.toList());
	}
}