package com.tripmicroservice.tripmicroservice.client;

import com.tripmicroservice.tripmicroservice.dto.ServicesDto;

import java.util.List;

public interface ServiceClient {
    List<ServicesDto> finAllServiceByTripId(int trip_id);

}
