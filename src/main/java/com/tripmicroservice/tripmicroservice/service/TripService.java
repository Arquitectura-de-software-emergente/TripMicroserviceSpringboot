package com.tripmicroservice.tripmicroservice.service;

import com.tripmicroservice.tripmicroservice.entities.Trip;
import com.tripmicroservice.tripmicroservice.http.TripResponse;

import java.util.List;

public interface TripService {
    public abstract Trip createTrip(Trip _trip);
    public abstract List<TripResponse> getAllTrip();
    public abstract void updateTrip(Trip _trip);
    public abstract void deleteTrip(int _id);
    public abstract Trip getTripById(int _id);

    //conexion
    List<Trip> getTripByAgencyId(int _agencyId);
     TripResponse getServiceByTripId(int _tripId);
}
