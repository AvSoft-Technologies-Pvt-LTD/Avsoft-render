package com.avsofthealthcare.entity;

import com.avsofthealthcare.entity.master.MedicalConditions;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
@Data
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "hospital_id", nullable = false)
    private HospitalList hospital;

    @ManyToOne
    @JoinColumn(name = "medical_condition_id", nullable = false)
    private MedicalConditions medicalCondition;

    private String chiefComplaint;

    private String status;

    @Column(nullable = false)
    private String type; // "OPD", "IPD", "VIRTUAL"

    private LocalDate dateOfConsultation;
}
