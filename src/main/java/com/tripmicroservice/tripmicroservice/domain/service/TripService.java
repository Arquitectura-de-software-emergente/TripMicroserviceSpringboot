package com.tripmicroservice.tripmicroservice.domain.service;

import com.tripmicroservice.tripmicroservice.domain.model.Trip;
import com.tripmicroservice.tripmicroservice.interfaces.dto.TripResponse;

import java.util.List;

public interface TripService {
    Trip createTrip(Trip trip);
    List<TripResponse> getAllTrip();
    void updateTrip(Trip trip);
    void deleteTrip(int id);
    Trip getTripById(int id);

    List<Trip> getTripByAgencyId(int agencyId);
    TripResponse getServiceByTripId(int tripId);
}
