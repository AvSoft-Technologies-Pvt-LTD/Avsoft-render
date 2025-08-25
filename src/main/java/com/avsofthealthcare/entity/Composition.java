package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "composition")
@Data
public class Composition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "medicine_id", referencedColumnName = "id")
    private Medicine medicine;


    private String composition;
}
