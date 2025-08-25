package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "side_effects")
@Data
public class SideEffect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;


    private String effect;

    // Getters and Setters
}
