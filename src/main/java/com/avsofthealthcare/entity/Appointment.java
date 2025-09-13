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


}
