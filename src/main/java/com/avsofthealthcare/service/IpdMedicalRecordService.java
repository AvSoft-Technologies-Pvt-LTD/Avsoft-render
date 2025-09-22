package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.IpdMedicalRecordRequestDTO;
import com.avsofthealthcare.dto.IpdMedicalRecordResponseDTO;

import java.util.List;

public interface IpdMedicalRecordService {
    IpdMedicalRecordResponseDTO createRecord(IpdMedicalRecordRequestDTO requestDTO);
    List<IpdMedicalRecordResponseDTO> getUserRecords(Long userId);
    void deleteRecord(Long recordId);
}
