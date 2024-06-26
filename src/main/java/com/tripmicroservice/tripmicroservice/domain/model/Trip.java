package com.tripmicroservice.tripmicroservice.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "agency_id", nullable = false)
    private int agencyId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "difficulty", nullable = false)
    private String difficulty;


    @Column(name = "lugar")
    private String lugar;


    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fecha_registro")
    private String fechaRegistro;

    @Column(name = "precio")
    private double precio;

    @Column(name = "photo1")
    private String photo1;

    @Column(name = "photo2")
    private String photo2;

    @Column(name = "photo3")
    private String photo3;

    @Column(name = "photo4")
    private String photo4;

    @Column(name = "photo5")
    private String photo5;

;
}
