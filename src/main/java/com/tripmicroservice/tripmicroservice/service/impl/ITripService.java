package com.tripmicroservice.tripmicroservice.service.impl;

import com.tripmicroservice.tripmicroservice.client.RatingClient;
import com.tripmicroservice.tripmicroservice.client.ReservationClient;
import com.tripmicroservice.tripmicroservice.client.ServiceClient;
import com.tripmicroservice.tripmicroservice.dto.RatingDto;
import com.tripmicroservice.tripmicroservice.dto.ReservationDto;
import com.tripmicroservice.tripmicroservice.dto.ServicesDto;
import com.tripmicroservice.tripmicroservice.entities.Trip;
import com.tripmicroservice.tripmicroservice.http.TripResponse;
import com.tripmicroservice.tripmicroservice.repository.TripRepository;
import com.tripmicroservice.tripmicroservice.service.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ITripService implements TripService {

    private static final Logger logger = LoggerFactory.getLogger(ITripService.class);

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ServiceClient serviceClient;

    @Autowired
    private ReservationClient reservationClient;
    @Autowired
    private RatingClient ratingClient;
    @Override
    public Trip createTrip(Trip trip) {
        logger.info("Creating trip: {}", trip);
        return tripRepository.save(trip);
    }

    public List<Trip> getAll() {
        logger.info("Retrieving all trips");
        return tripRepository.findAll();
    }

    @Override
    public List<TripResponse> getAllTrip() {
        List<Trip> trips = getAll();
        List<TripResponse> responses = new ArrayList<>();
        for (Trip trip : trips) {
            logger.info("Processing trip: {}", trip);
            // pasamos por qui y me trae el trip correccto
            // ye ntramso al ITripService
            List<ReservationDto> reservations = reservationClient.getReservationsByTripId(trip.getId());

            List<ServicesDto> servicesDtos = serviceClient.finAllServiceByTripId(trip.getId());
            List<RatingDto> ratingsDtos =  ratingClient.getRatingsByTripId(trip.getId());
            TripResponse response = TripResponse.builder()
                    .Id(trip.getId())
                    .Title(trip.getTitle())
                    .Description(trip.getDescription())
                    .Duration(trip.getDuration())
                    .Difficulty(trip.getDifficulty())
                    .servicesDtoList(servicesDtos)
                    .reservations(reservations)
                    .ratings(ratingsDtos)
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Override
    public void updateTrip(Trip trip) {
        logger.info("Updating trip: {}", trip);
        tripRepository.save(trip);
    }

    @Override
    public void deleteTrip(int id) {
        logger.info("Deleting trip with id: {}", id);
        tripRepository.deleteById(id);
    }

    @Override
    public Trip getTripById(int id) {
        logger.info("Getting trip by id: {}", id);
        return tripRepository.findById(id).orElseThrow(() -> {
            logger.error("Trip not found with id: {}", id);
            return new RuntimeException("Trip not found");
        });
    }

    @Override
    public List<Trip> getTripByAgencyId(int agencyId) {
        logger.info("Getting trips by agency id: {}", agencyId);
        return tripRepository.findByAgencyId(agencyId);
    }

    @Override
    public TripResponse getServiceByTripId(int tripId) {
        logger.info("Getting services and reservations for trip id: {}", tripId);
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> {
            logger.error("Trip not found with id: {}", tripId);
            return new RuntimeException("Trip not found");
        });
        List<ServicesDto> services = serviceClient.finAllServiceByTripId(tripId);
        List<ReservationDto> reservations = reservationClient.getReservationsByTripId(tripId);
        List<RatingDto> ratings = ratingClient.getRatingsByTripId(tripId);

        if (reservations == null) {
            logger.warn("Reservations not found for trip id: {}", tripId);
            reservations = new ArrayList<>();
        }
        return TripResponse.builder()
                .Id(trip.getId())
                .Title(trip.getTitle())
                .Description(trip.getDescription())
                .Duration(trip.getDuration())
                .Difficulty(trip.getDifficulty())
                .servicesDtoList(services)
                .reservations(reservations)
                .ratings(ratings)
                .build();
    }
}
