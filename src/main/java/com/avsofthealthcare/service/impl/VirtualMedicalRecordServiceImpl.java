package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.VirtualMedicalRecordRequestDTO;
import com.avsofthealthcare.dto.VirtualMedicalRecordResponseDTO;
import com.avsofthealthcare.entity.HospitalList;
import com.avsofthealthcare.entity.VirtualMedicalRecord;
import com.avsofthealthcare.entity.master.MedicalConditions;
import com.avsofthealthcare.repository.HospitalListRepository;
import com.avsofthealthcare.repository.VirtualMedicalRecordRepository;
import com.avsofthealthcare.repository.master.MedicalConditionsRepository;
import com.avsofthealthcare.service.VirtualMedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VirtualMedicalRecordServiceImpl implements VirtualMedicalRecordService {

    private final VirtualMedicalRecordRepository virtualMedicalRecordRepository;
    private final HospitalListRepository hospitalRepository;
    private final MedicalConditionsRepository medicalConditionRepository;

    @Override
    public VirtualMedicalRecordResponseDTO createRecord(VirtualMedicalRecordRequestDTO requestDTO) {
        HospitalList hospital = hospitalRepository.findById((long) requestDTO.getHospitalId().intValue())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        MedicalConditions condition = medicalConditionRepository.findById((long) requestDTO.getMedicalConditionId().intValue())
                .orElseThrow(() -> new RuntimeException("Medical Condition not found"));

        VirtualMedicalRecord record = new VirtualMedicalRecord();
        record.setUserId(requestDTO.getUserId());
        record.setHospital(hospital);
        record.setChiefComplaint(requestDTO.getChiefComplaint());
        record.setMedicalCondition(condition);
        record.setStatus(requestDTO.getStatus());
        record.setDateOfConsultation(requestDTO.getDateOfConsultation());

        virtualMedicalRecordRepository.save(record);

        return mapToDTO(record);
    }

    @Override
    public List<VirtualMedicalRecordResponseDTO> getUserRecords(Long userId) {
        return virtualMedicalRecordRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRecord(Long recordId) {
        virtualMedicalRecordRepository.deleteById(recordId.intValue());
    }

    private VirtualMedicalRecordResponseDTO mapToDTO(VirtualMedicalRecord record) {
        VirtualMedicalRecordResponseDTO dto = new VirtualMedicalRecordResponseDTO();
        dto.setRecordId(record.getId());
        dto.setUserId(record.getUserId());
        dto.setHospitalName(record.getHospital().getHospitalName());
        dto.setMedicalConditionName(record.getMedicalCondition().getConditionName());
        dto.setChiefComplaint(record.getChiefComplaint());
        dto.setStatus(record.getStatus());
        dto.setDateOfConsultation(record.getDateOfConsultation());
        return dto;
    }
}
