package com.tripmicroservice.tripmicroservice.repository;

import com.tripmicroservice.tripmicroservice.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findByAgencyId(int agencyId);
}
