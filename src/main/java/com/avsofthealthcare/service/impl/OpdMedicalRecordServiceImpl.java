package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.OpdMedicalRecordRequestDTO;
import com.avsofthealthcare.dto.OpdMedicalRecordResponseDTO;
import com.avsofthealthcare.entity.HospitalList;
import com.avsofthealthcare.entity.master.MedicalConditions;
import com.avsofthealthcare.entity.OpdMedicalRecord;
import com.avsofthealthcare.repository.HospitalListRepository;
import com.avsofthealthcare.repository.master.MedicalConditionsRepository;
import com.avsofthealthcare.repository.OpdMedicalRecordRepository;
import com.avsofthealthcare.service.OpdMedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpdMedicalRecordServiceImpl implements OpdMedicalRecordService {

    private final OpdMedicalRecordRepository opdMedicalRecordRepository;
    private final HospitalListRepository hospitalRepository;
    private final MedicalConditionsRepository medicalConditionRepository;

    @Override
    public OpdMedicalRecordResponseDTO createRecord(OpdMedicalRecordRequestDTO requestDTO) {
        HospitalList hospital = hospitalRepository.findById(requestDTO.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        MedicalConditions condition = medicalConditionRepository.findById(requestDTO.getMedicalConditionId())
                .orElseThrow(() -> new RuntimeException("Medical Condition not found"));

        OpdMedicalRecord record = new OpdMedicalRecord();
        record.setUserId(requestDTO.getUserId());
        record.setHospital(hospital);
        record.setChiefComplaint(requestDTO.getChiefComplaint());
        record.setMedicalCondition(condition);
        record.setStatus(requestDTO.getStatus());
        record.setDateOfVisit(requestDTO.getDateOfVisit());

        opdMedicalRecordRepository.save(record);

        return mapToDTO(record);
    }

    @Override
    public List<OpdMedicalRecordResponseDTO> getUserRecords(Long userId) {
        return opdMedicalRecordRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRecord(Long recordId) {
        opdMedicalRecordRepository.deleteById(recordId);
    }

    private OpdMedicalRecordResponseDTO mapToDTO(OpdMedicalRecord record) {
        OpdMedicalRecordResponseDTO dto = new OpdMedicalRecordResponseDTO();
        dto.setRecordId(record.getId());
        dto.setUserId(record.getUserId());
        dto.setHospitalName(record.getHospital().getHospitalName());
        dto.setChiefComplaint(record.getChiefComplaint());
        dto.setMedicalConditionName(record.getMedicalCondition().getConditionName());
        dto.setStatus(record.getStatus());
        dto.setDateOfVisit(record.getDateOfVisit());
        return dto;
    }
}
