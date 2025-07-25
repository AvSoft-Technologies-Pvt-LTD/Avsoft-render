package com.avsofthealthcare.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "medicines")
@Data
public class Medicine {

    @Id
    private int id;

    private String name;
    private double price;

    @Column(name = "is_discontinued")
    private boolean isDiscontinued;

    @Column(name = "manufacturer_name")
    private String manufacturerName;

    private String type;

    @Column(name = "pack_size_label")
    private String packSizeLabel;

    @Column(name = "habit_forming")
    private String habitForming;

    // Bi-directional relationships
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Composition> compositions;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SideEffect> sideEffects;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Substitute> substitutes;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Use> uses;

    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChemicalTherapeuticInfo> chemicalTherapeuticInfos;
}
