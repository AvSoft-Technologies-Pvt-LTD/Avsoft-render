package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.MedicalRecordRequestDTO;
import com.avsofthealthcare.dto.MedicalRecordResponseDTO;

import java.util.List;

public interface MedicalRecordService {
    MedicalRecordResponseDTO createRecord(MedicalRecordRequestDTO dto);
    List<MedicalRecordResponseDTO> getUserRecords(Long userId, String type);
    void deleteRecord(Long recordId);
}
