package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.VirtualMedicalRecordRequestDTO;
import com.avsofthealthcare.dto.VirtualMedicalRecordResponseDTO;

import java.util.List;

public interface VirtualMedicalRecordService {
    VirtualMedicalRecordResponseDTO createRecord(VirtualMedicalRecordRequestDTO requestDTO);
    List<VirtualMedicalRecordResponseDTO> getUserRecords(Long userId);
    void deleteRecord(Long recordId);
}
