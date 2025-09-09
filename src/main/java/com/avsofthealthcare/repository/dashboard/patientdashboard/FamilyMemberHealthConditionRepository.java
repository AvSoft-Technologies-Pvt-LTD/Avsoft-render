package com.avsofthealthcare.repository.dashboard.patientdashboard;

import com.avsofthealthcare.entity.dashboard.patientdashboard.FamilyMemberHealthCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyMemberHealthConditionRepository extends JpaRepository<FamilyMemberHealthCondition, Long> {

    // ✅ Custom delete to remove all health conditions of a member
    void deleteByFamilyMemberId(Long familyMemberId);
}
