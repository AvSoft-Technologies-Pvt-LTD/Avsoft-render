package com.avsofthealthcare.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chemical_therapeutic_info")
@Data
public class ChemicalTherapeuticInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    @Column(name = "chemical_class")
    private String chemicalClass;

    @Column(name = "therapeutic_class")
    private String therapeuticClass;

    @Column(name = "action_class")
    private String actionClass;
}
