package com.avsofthealthcare.service.dashboard.patientdashboard;

import com.avsofthealthcare.dto.dashboard.patientdashboard.FamilyMemberRequestDTO;
import com.avsofthealthcare.dto.dashboard.patientdashboard.FamilyMemberResponseDTO;

import java.util.List;

public interface FamilyMemberService {

    // ✅ Create new family member with health conditions
    FamilyMemberResponseDTO create(FamilyMemberRequestDTO requestDTO);

    // ✅ Get all family members by patient ID
    List<FamilyMemberResponseDTO> getByPatientId(String patientId);

    // ✅ Get one member by ID
    FamilyMemberResponseDTO getById(Long id);

    // ✅ Update family member
    FamilyMemberResponseDTO update(Long id, FamilyMemberRequestDTO requestDTO);

    // ✅ Delete member and their health conditions
    void delete(Long id);
}
