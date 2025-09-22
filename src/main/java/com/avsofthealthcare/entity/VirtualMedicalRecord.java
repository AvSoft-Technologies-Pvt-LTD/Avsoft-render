package com.avsofthealthcare.entity;

import com.avsofthealthcare.entity.master.MedicalConditions;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "virtual_medical_records")
@Data
public class VirtualMedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private HospitalList hospital;

    private String chiefComplaint;

    @ManyToOne
    @JoinColumn(name = "medical_condition_id")
    private MedicalConditions medicalCondition;

    private String status;

    private LocalDate dateOfConsultation;
}
