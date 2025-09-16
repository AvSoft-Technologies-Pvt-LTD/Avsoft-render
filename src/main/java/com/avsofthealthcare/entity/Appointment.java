package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String consultationType;
    private String pincode;
    private String state;
    private String city;
    private String hospital;
    private String symptoms;
    private String specialties;
    private String doctorPanel;
    private Integer minFees;
    private Integer maxFees;
    private LocalDateTime createdAt;

    public Appointment() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsultationType() {
        return consultationType;
    }

    public void setConsultationType(String consultationType) {
        this.consultationType = consultationType;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getSpecialties() {
        return specialties;
    }

    public void setSpecialties(String specialties) {
        this.specialties = specialties;
    }

    public String getDoctorPanel() {
        return doctorPanel;
    }

    public void setDoctorPanel(String doctorPanel) {
        this.doctorPanel = doctorPanel;
    }

    public Integer getMinFees() {
        return minFees;
    }

    public void setMinFees(Integer minFees) {
        this.minFees = minFees;
    }

    public Integer getMaxFees() {
        return maxFees;
    }

    public void setMaxFees(Integer maxFees) {
        this.maxFees = maxFees;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
