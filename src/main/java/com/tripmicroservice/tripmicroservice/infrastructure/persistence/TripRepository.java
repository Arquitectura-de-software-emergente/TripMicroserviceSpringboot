package com.tripmicroservice.tripmicroservice.infrastructure.persistence;

import com.tripmicroservice.tripmicroservice.domain.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findByAgencyId(int agencyId);
}
