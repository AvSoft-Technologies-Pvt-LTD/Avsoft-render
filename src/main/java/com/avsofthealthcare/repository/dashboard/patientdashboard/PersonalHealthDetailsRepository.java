package com.avsofthealthcare.repository.dashboard.patientdashboard;

import com.avsofthealthcare.entity.dashboard.patientdashboard.PersonalHealthDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonalHealthDetailsRepository extends JpaRepository<PersonalHealthDetails, Long> {
    Optional<PersonalHealthDetails> findByPatientId(String patientId);

    boolean existsByPatientId(String patientId);

    void deleteByPatientId(String patientId);

//    List<PersonalHealthDetails> findAllByPatientId(String patientId);
}

