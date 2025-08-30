package com.avsofthealthcare.repository.dashboard.patientdashboard;

import com.avsofthealthcare.entity.dashboard.patientdashboard.PatientVitals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientVitalsRepository extends JpaRepository<PatientVitals, Long> {
}
