package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.OpdMedicalRecordRequestDTO;
import com.avsofthealthcare.dto.OpdMedicalRecordResponseDTO;
import java.util.List;

public interface OpdMedicalRecordService {
    OpdMedicalRecordResponseDTO createRecord(OpdMedicalRecordRequestDTO requestDTO);
    List<OpdMedicalRecordResponseDTO> getUserRecords(Long userId);
    void deleteRecord(Long recordId);
}
