package com.avsofthealthcare.repository.dashboard.patientdashboard;

import com.avsofthealthcare.entity.dashboard.patientdashboard.FamilyMemberDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FamilyMemberDetailsRepository extends JpaRepository<FamilyMemberDetails, Long> {

    // ✅ Custom method to fetch all family members of a specific patient
    List<FamilyMemberDetails> findByPatientId(String patientId);
}
