package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.MedicalRecordRequestDTO;
import com.avsofthealthcare.dto.MedicalRecordResponseDTO;
import com.avsofthealthcare.entity.HospitalList;
import com.avsofthealthcare.entity.MedicalRecord;
import com.avsofthealthcare.entity.master.MedicalConditions;
import com.avsofthealthcare.repository.HospitalListRepository;
import com.avsofthealthcare.repository.MedicalRecordRepository;
import com.avsofthealthcare.repository.master.MedicalConditionsRepository;
import com.avsofthealthcare.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final HospitalListRepository hospitalRepository;
    private final MedicalConditionsRepository medicalConditionRepository;

    @Override
    public MedicalRecordResponseDTO createRecord(MedicalRecordRequestDTO dto) {
        HospitalList hospital = hospitalRepository.findById(dto.getHospitalId())
                .orElseThrow(() -> new RuntimeException("Hospital not found"));

        MedicalConditions condition = medicalConditionRepository.findById((long) dto.getMedicalConditionId().intValue())
                .orElseThrow(() -> new RuntimeException("Medical condition not found"));

        MedicalRecord record = new MedicalRecord();
        record.setUserId(dto.getUserId());
        record.setHospital(hospital);
        record.setMedicalCondition(condition);
        record.setChiefComplaint(dto.getChiefComplaint());
        record.setStatus(dto.getStatus());
        record.setType(dto.getType());
        record.setDateOfConsultation(dto.getDateOfConsultation());

        medicalRecordRepository.save(record);

        return mapToDTO(record);
    }

    @Override
    public List<MedicalRecordResponseDTO> getUserRecords(Long userId, String type) {
        List<MedicalRecord> records;
        if (type != null) {
            records = medicalRecordRepository.findByUserIdAndType(userId, type);
        } else {
            records = medicalRecordRepository.findByUserId(userId);
        }
        return records.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteRecord(Long recordId) {
        medicalRecordRepository.deleteById(recordId);
    }

    private MedicalRecordResponseDTO mapToDTO(MedicalRecord record) {
        MedicalRecordResponseDTO dto = new MedicalRecordResponseDTO();
        dto.setRecordId(record.getId());
        dto.setUserId(record.getUserId());
        dto.setHospitalName(record.getHospital().getHospitalName());
        dto.setMedicalConditionName(record.getMedicalCondition().getConditionName());
        dto.setChiefComplaint(record.getChiefComplaint());
        dto.setStatus(record.getStatus());
        dto.setType(record.getType());
        dto.setDateOfConsultation(record.getDateOfConsultation());
        return dto;
    }
}
