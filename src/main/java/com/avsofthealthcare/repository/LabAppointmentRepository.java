package com.avsofthealthcare.repository;

import com.avsofthealthcare.entity.LabAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabAppointmentRepository extends JpaRepository<LabAppointment, Long> {
    List<LabAppointment> findByPatientId(String patientId);
}
