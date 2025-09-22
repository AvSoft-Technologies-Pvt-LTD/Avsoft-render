package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.IpdMedicalRecordRequestDTO;
import com.avsofthealthcare.dto.IpdMedicalRecordResponseDTO;
import com.avsofthealthcare.entity.HospitalList;
import com.avsofthealthcare.entity.IpdMedicalRecord;
import com.avsofthealthcare.entity.master.MedicalConditions;
import com.avsofthealthcare.repository.HospitalListRepository;
import com.avsofthealthcare.repository.IpdMedicalRecordRepository;
import com.avsofthealthcare.repository.master.MedicalConditionsRepository;
import com.avsofthealthcare.service.IpdMedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IpdMedicalRecordServiceImpl implements IpdMedicalRecordService {

    private final IpdMedicalRecordRepository ipdMedicalRecordRepository;
    private final HospitalListRepository hospitalRepository;
    private final MedicalConditionsRepository medicalConditionRepository;

    @Override
    public IpdMedicalRecordResponseDTO createRecord(IpdMedicalRecordRequestDTO requestDTO) {
        HospitalList hospital = hospitalRepository.findById((long) requestDTO.getHospitalId().intValue())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        MedicalConditions condition = medicalConditionRepository.findById((long) requestDTO.getMedicalConditionId().intValue())
                .orElseThrow(() -> new RuntimeException("Medical Condition not found"));

        IpdMedicalRecord record = new IpdMedicalRecord();
        record.setUserId(requestDTO.getUserId());
        record.setHospital(hospital);
        record.setChiefComplaint(requestDTO.getChiefComplaint());
        record.setMedicalCondition(condition);
        record.setStatus(requestDTO.getStatus());
        record.setDateOfAdmission(requestDTO.getDateOfAdmission());
        record.setDateOfDischarge(requestDTO.getDateOfDischarge());

        ipdMedicalRecordRepository.save(record);

        return mapToDTO(record);
    }

    @Override
    public List<IpdMedicalRecordResponseDTO> getUserRecords(Long userId) {
        return ipdMedicalRecordRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRecord(Long recordId) {
        ipdMedicalRecordRepository.deleteById(recordId.intValue());
    }

    private IpdMedicalRecordResponseDTO mapToDTO(IpdMedicalRecord record) {
        IpdMedicalRecordResponseDTO dto = new IpdMedicalRecordResponseDTO();
        dto.setRecordId(record.getId());
        dto.setUserId(record.getUserId());
        dto.setHospitalName(record.getHospital().getHospitalName());
        dto.setMedicalConditionName(record.getMedicalCondition().getConditionName());
        dto.setChiefComplaint(record.getChiefComplaint());
        dto.setStatus(record.getStatus());
        dto.setDateOfAdmission(record.getDateOfAdmission());
        dto.setDateOfDischarge(record.getDateOfDischarge());
        return dto;
    }
}
