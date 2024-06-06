package com.tripmicroservice.tripmicroservice.controller;

import com.tripmicroservice.tripmicroservice.entities.Trip;
import com.tripmicroservice.tripmicroservice.http.TripResponse;
import com.tripmicroservice.tripmicroservice.service.impl.ITripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class TripController {

    private static final Logger logger = LoggerFactory.getLogger(TripController.class);

    @Autowired
    private ITripService tripService;

    @PostMapping("/trip")
    public ResponseEntity<Trip> createTrip(@RequestBody Trip trip) {
        logger.info("Received request to create trip: {}", trip);
        Trip createdTrip = tripService.createTrip(trip);
        logger.info("Created trip: {}", createdTrip);
        return new ResponseEntity<>(createdTrip, HttpStatus.CREATED);
    }

    @GetMapping("/trip")
    public ResponseEntity<List<TripResponse>> getAllTrip() {
        logger.info("Received request to get all trips");
        // aquu comienza pidiendo las trips y se va al trip response quem etraera all trp del service
        List<TripResponse> trips = tripService.getAllTrip();
        logger.info("Retrieved trips: {}", trips);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    @GetMapping("/trip/{id}")
    public ResponseEntity<TripResponse> getTripById(@PathVariable int id) {
        logger.info("Received request to get trip by id: {}", id);
        try {
            TripResponse tripResponse = tripService.getServiceByTripId(id);
            logger.info("Retrieved trip for id: {}", id);
            return new ResponseEntity<>(tripResponse, HttpStatus.OK);
        } catch (RuntimeException e) {
            logger.error("Trip not found for id: {}", id, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/trip/{id}")
    public ResponseEntity<Void> updateTrip(@PathVariable("id") int id, @RequestBody Trip trip) {
        logger.info("Received request to update trip with id: {}", id);
        trip.setId(id);
        tripService.updateTrip(trip);
        logger.info("Updated trip with id: {}", id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/trip/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable("id") int id) {
        logger.info("Received request to delete trip with id: {}", id);
        tripService.deleteTrip(id);
        logger.info("Deleted trip with id: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/trip/by-agency/{agencyId}")
    public ResponseEntity<List<Trip>> getTripsByAgencyId(@PathVariable int agencyId) {
        logger.info("Received request to get trips by agencyId: {}", agencyId);
        List<Trip> trips = tripService.getTripByAgencyId(agencyId);
        if (trips.isEmpty()) {
            logger.warn("No trips found for agencyId: {}", agencyId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        logger.info("Retrieved trips for agencyId: {}", agencyId);
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }


}
