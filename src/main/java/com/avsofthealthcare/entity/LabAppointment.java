package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "lab_appointments")
@Getter
@Setter
public class LabAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appointmentId;
    private String patientId;
    private LocalDate appointmentDate;
    private String status;

    @ManyToMany
    @JoinTable(
            name = "appointment_tests",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "test_id")
    )
    private List<LabTest> tests;

    @ManyToMany
    @JoinTable(
            name = "appointment_scans",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "scan_id")
    )
    private List<LabScan> scans;
}
