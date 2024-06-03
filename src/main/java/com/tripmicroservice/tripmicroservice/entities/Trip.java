package com.tripmicroservice.tripmicroservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name = "agency_id",nullable = false)
    private int agencyId;
    @Column(name = "title",nullable = false)
    private String Title;
    @Column(name = "description",nullable = false)
    private String Description;
    @Column(name = "duration",nullable = false)
    private int Duration;
    @Column(name = "difficulty",nullable = false)
    private String Difficulty;

}