package com.avsofthealthcare.service.impl.master;

import com.avsofthealthcare.dto.master.SpecializationRequestDto;
import com.avsofthealthcare.dto.master.SpecializationResponseDto;
import com.avsofthealthcare.entity.master.PracticeType;
import com.avsofthealthcare.entity.master.Specialization;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.master.SpecializationMapper;
import com.avsofthealthcare.repository.master.PracticeTypeRepository;
import com.avsofthealthcare.repository.master.SpecializationRepository;
import com.avsofthealthcare.service.master.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecializationServiceImpl implements SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    @Autowired
    private PracticeTypeRepository practiceTypeRepository;



    @Override
    public Specialization create(SpecializationRequestDto dto) {
        PracticeType practiceType = practiceTypeRepository.findById(dto.getPracticeTypeId())
                .orElseThrow(() -> new RuntimeException("PracticeType not found with id: " + dto.getPracticeTypeId()));

        Specialization specialization = SpecializationMapper.toEntity(dto, practiceType);

        return specializationRepository.save(specialization);
    }


    @Override
    public List<Specialization> findAllByIsDeletedFalse() {
        return specializationRepository.findAllByIsDeletedFalse();
    }

    @Override
    public Specialization findById(Integer id) {
        return specializationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization not found with id: " + id));
    }

    @Override
    public Specialization findByIdAndIsDeletedFalse(Integer id) {
        return specializationRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specialization not found with id: " + id));
    }

    @Override
    public Specialization update(Integer id, SpecializationRequestDto dto) {
        Specialization specializationExisting = findByIdAndIsDeletedFalse(id);

        specializationExisting.setSpecializationName(dto.getSpecializationName());
        specializationExisting.setDescription(dto.getDescription());

        if (dto.getActive() != null) {
            specializationExisting.setActive(dto.getActive());
        }

        // Optional: allow changing practice type (only if needed)
        if (dto.getPracticeTypeId() != null &&
                !dto.getPracticeTypeId().equals(specializationExisting.getPracticeType().getId())) {

            PracticeType newPracticeType = practiceTypeRepository.findById(dto.getPracticeTypeId())
                    .orElseThrow(() -> new RuntimeException("PracticeType not found with id: " + dto.getPracticeTypeId()));
            specializationExisting.setPracticeType(newPracticeType);
        }

        // Manually set update fields
        specializationExisting.setUpdatedBy("admin"); // Replace with logged-in user
        specializationExisting.setUpdatedAt(LocalDateTime.now());

        return specializationRepository.save(specializationExisting);
    }


    @Override
    public void delete(Integer id) {
        Specialization existing = findById(id);
        existing.setIsDeleted(true);
        specializationRepository.save(existing);
    }

	@Override
	public List<SpecializationResponseDto> getSpecializationsByPracticeTypeId(Integer practiceTypeId) {
		List<Specialization> specializations = specializationRepository.findByPracticeTypeIdAndActiveTrueAndIsDeletedFalse(practiceTypeId);
		return specializations.stream()
				.map(SpecializationMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public List<Specialization> searchBySymptoms(String symptoms) {
		return specializationRepository.findBySymptomsContainingIgnoreCaseAndIsDeletedFalse(symptoms);
	}

}



