package com.tripmicroservice.tripmicroservice.service;

import com.tripmicroservice.tripmicroservice.entities.Trip;
import com.tripmicroservice.tripmicroservice.http.TripResponse;

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
