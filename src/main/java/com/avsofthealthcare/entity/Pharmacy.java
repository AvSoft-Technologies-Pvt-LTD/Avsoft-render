package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "pharmacy")
public class Pharmacy {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String phone;
    private String hours;

    public Pharmacy() {}

    public Pharmacy(Long id, String name, String address, Double latitude, Double longitude,
                    String phone, String hours) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.hours = hours;
    }

}
