package com.avsofthealthcare.service.impl.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.PersonalHealthDetailsRequestDto;
import com.avsofthealthcare.dto.dashboard.patientdashboard.PersonalHealthDetailsResponseDto;
import com.avsofthealthcare.entity.dashboard.patientdashboard.PersonalHealthDetails;
import com.avsofthealthcare.entity.master.BloodGroup;
import com.avsofthealthcare.exception.ResourceNotFoundException;
import com.avsofthealthcare.mapper.dashboard.patientdashboard.PersonalHealthDetailsMapper;
import com.avsofthealthcare.repository.dashboard.patientdashboard.PersonalHealthDetailsRepository;
import com.avsofthealthcare.repository.master.BloodGroupRepository;
import com.avsofthealthcare.service.dashboard.patientdashboard.PersonalHealthDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalHealthDetailsServiceImpl implements PersonalHealthDetailsService {

    @Autowired
    private PersonalHealthDetailsRepository personalHealthDetailsRepository;

    @Autowired
    private BloodGroupRepository bloodGroupRepository;

    @Override
    public PersonalHealthDetailsResponseDto create(PersonalHealthDetailsRequestDto dto) {
        // Optional: check if record already exists for this patientId
        if (personalHealthDetailsRepository.existsByPatientId(dto.getPatientId())) {
            throw new IllegalStateException("Personal health record already exists for patientId: " + dto.getPatientId());
        }

        BloodGroup bloodGroup = bloodGroupRepository.findById(dto.getBloodGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("Blood Group not found with id: " + dto.getBloodGroupId()));

        PersonalHealthDetails entity = PersonalHealthDetailsMapper.toEntity(dto, bloodGroup);
        return PersonalHealthDetailsMapper.toDto(personalHealthDetailsRepository.save(entity));
    }

//    @Override
//    public List<PersonalHealthDetailsResponseDto> findAllByPatientId(String patientId) {
//        return personalHealthDetailsRepository.findAllByPatientId(patientId)
//                .stream()
//                .map(PersonalHealthDetailsMapper::toDto)
//                .collect(Collectors.toList());
//    }


    @Override
    public PersonalHealthDetailsResponseDto findByPatientId(String patientId) {
        PersonalHealthDetails entity = personalHealthDetailsRepository.findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Personal Health Details not found for patientId: " + patientId));
        return PersonalHealthDetailsMapper.toDto(entity);
    }

    @Override
    public PersonalHealthDetailsResponseDto updateByPatientId(String patientId, PersonalHealthDetailsRequestDto dto) {
        PersonalHealthDetails existing = personalHealthDetailsRepository.findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Personal Health Details not found for patientId: " + patientId));

        BloodGroup bloodGroup = bloodGroupRepository.findById(dto.getBloodGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("Blood Group not found with id: " + dto.getBloodGroupId()));

        // Update fields
        existing.setHeight(dto.getHeight());
        existing.setWeight(dto.getWeight());
        existing.setBloodGroup(bloodGroup);
        existing.setSurgeries(dto.getSurgeries());
        existing.setAllergies(dto.getAllergies());
        existing.setIsSmoker(dto.getIsSmoker());
        existing.setYearsSmoking(dto.getYearsSmoking());
        existing.setIsAlcoholic(dto.getIsAlcoholic());
        existing.setYearsAlcoholic(dto.getYearsAlcoholic());
        existing.setIsTobacco(dto.getIsTobacco());
        existing.setYearsTobacco(dto.getYearsTobacco());

        // Audit (can be made dynamic)
        existing.setUpdatedBy("admin");
        existing.setUpdatedAt(LocalDateTime.now());

        return PersonalHealthDetailsMapper.toDto(personalHealthDetailsRepository.save(existing));
    }

    @Override
    public void delete(String patientId) {
        PersonalHealthDetails existing = personalHealthDetailsRepository.findByPatientId(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Personal Health Details not found for patientId: " + patientId));
        personalHealthDetailsRepository.delete(existing);
    }
}