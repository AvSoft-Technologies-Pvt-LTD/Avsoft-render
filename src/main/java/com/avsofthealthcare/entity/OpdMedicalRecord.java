package com.avsofthealthcare.entity;

import com.avsofthealthcare.entity.master.MedicalConditions;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "opd_medical_records")
@Data
public class OpdMedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private HospitalList hospital;

    private String chiefComplaint;

    @ManyToOne
    @JoinColumn(name = "medical_condition_id")
    private MedicalConditions medicalCondition;

    private String status;

    private LocalDate dateOfVisit;
}
