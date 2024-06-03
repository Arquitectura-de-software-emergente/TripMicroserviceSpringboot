package com.tripmicroservice.tripmicroservice.controller;

import com.tripmicroservice.tripmicroservice.entities.Trip;
import com.tripmicroservice.tripmicroservice.http.TripResponse;
import com.tripmicroservice.tripmicroservice.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class TripController {
    @Autowired
    private TripService _tripService;

    @PostMapping("/trip")
    public ResponseEntity<Trip> createTrip(@RequestBody Trip _guide){
        Trip createdGuide = _tripService.createTrip(_guide);
        return new ResponseEntity<>(createdGuide, HttpStatus.CREATED);
    }

    @GetMapping("/trip")
    public ResponseEntity<List<TripResponse>> getAllTrip(){
        List<TripResponse> trips = _tripService.getAllTrip();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }
    @PutMapping("/trip/{id}")
    public ResponseEntity<Void> updateTrip(@PathVariable("id") int id, @RequestBody Trip _guide){
        _guide.setId(id);
        _tripService.updateTrip(_guide);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/trip/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable("id") int id){
        _tripService.deleteTrip(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@GetMapping("/trip/{id}")
    //public ResponseEntity<Trip> getTripById(@PathVariable("id") int id){
    //    try {
    //        Trip guide = _guideService.getTripById(id);
    //        return new ResponseEntity<>(guide, HttpStatus.OK);
    //    } catch (RuntimeException e) {
    //        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //    }
    //}
    @GetMapping("/trip/by-agency/{agencyId}")
    public ResponseEntity<List<Trip>> getTripsByAgencyId(@PathVariable int agencyId) {
        List<Trip> guides = _tripService.getTripByAgencyId(agencyId);
        if (guides.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(guides, HttpStatus.OK);
    }

    @GetMapping("/trip/{id}")
    public ResponseEntity<TripResponse> getTripById(@PathVariable int id){
        try {
            TripResponse guide = _tripService.getServiceByTripId(id);
            return new ResponseEntity<>(guide, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
