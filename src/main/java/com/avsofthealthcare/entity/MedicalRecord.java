package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hospital;
    private String type; // OPD, IPD, Virtual
    private String chiefComplaint;
    private LocalDate dateOfVisit;
    private LocalDate dateOfAdmission;
    private LocalDate dateOfDischarge;
    private String status;

    public MedicalRecord() {}

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getHospital() {
        return hospital;
    }
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getChiefComplaint() {
        return chiefComplaint;
    }
    public void setChiefComplaint(String chiefComplaint) {
        this.chiefComplaint = chiefComplaint;
    }
    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }
    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }
    public LocalDate getDateOfAdmission() {
        return dateOfAdmission;
    }
    public void setDateOfAdmission(LocalDate dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }
    public LocalDate getDateOfDischarge() {
        return dateOfDischarge;
    }
    public void setDateOfDischarge(LocalDate dateOfDischarge) {
        this.dateOfDischarge = dateOfDischarge;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
