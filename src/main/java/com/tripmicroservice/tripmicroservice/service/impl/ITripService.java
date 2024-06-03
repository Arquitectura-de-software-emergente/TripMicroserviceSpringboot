package com.tripmicroservice.tripmicroservice.service.impl;

import com.tripmicroservice.tripmicroservice.client.ServiceClient;
import com.tripmicroservice.tripmicroservice.dto.ServicesDto;
import com.tripmicroservice.tripmicroservice.entities.Trip;
import com.tripmicroservice.tripmicroservice.http.TripResponse;
import com.tripmicroservice.tripmicroservice.repository.TripRepository;
import com.tripmicroservice.tripmicroservice.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ITripService implements TripService {

    @Autowired
    private TripRepository _tripRepository;
    @Autowired
    private ServiceClient _serviceClient;
    @Override
    public Trip createTrip(Trip _trip) {
        return _tripRepository.save(_trip);
    }

    public List<Trip> getAll(){
        return (List<Trip>) _tripRepository.findAll();
    }
    @Override
    public List<TripResponse> getAllTrip() {
      List<Trip> trips = getAll();
      List<TripResponse>  responses =  new ArrayList<>();
      for (Trip trip : trips){
          List<ServicesDto> servicesDtos = _serviceClient.finAllServiceByTripId(trip.getId());
            TripResponse response = TripResponse.builder()
                    .Id(trip.getId())
                    .Title(trip.getTitle())
                    .Description(trip.getDescription())
                    .Duration(trip.getDuration())
                    .Difficulty(trip.getDifficulty())
                    .servicesDtoList(servicesDtos)
                    .build();
          responses.add(response);
      }
      return responses;
    }

    @Override
    public void updateTrip(Trip _trip) {
        _tripRepository.save(_trip);
    }

    @Override
    public void deleteTrip(int _id) {
        _tripRepository.deleteById(_id);
    }

    @Override
    public Trip getTripById(int _id) {
        if(_tripRepository.findById(_id).isPresent()){
            return _tripRepository.findById(_id).get();
        }else{
            throw new RuntimeException("Trip not found");
        }
    }
    @Override
    public List<Trip> getTripByAgencyId(int _agencyId) {
        return _tripRepository.findByAgencyId(_agencyId);
    }

    @Override
    public TripResponse getServiceByTripId(int _tripId) {
        Trip trip = _tripRepository.findById(_tripId).orElse(new Trip());
        List<ServicesDto> services = _serviceClient.finAllServiceByTripId(_tripId);

        return TripResponse.builder()
                .Id(trip.getId())
                .Title(trip.getTitle())
                .Description(trip.getDescription())
                .Duration(trip.getDuration())
                .Difficulty(trip.getDifficulty())
                .servicesDtoList(services)
                .build();
    }
}
