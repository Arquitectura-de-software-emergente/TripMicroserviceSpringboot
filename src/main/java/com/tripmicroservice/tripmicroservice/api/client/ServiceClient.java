package com.tripmicroservice.tripmicroservice.api.client;

import com.tripmicroservice.tripmicroservice.interfaces.dto.ServicesDto;

import java.util.List;

public interface ServiceClient {
    List<ServicesDto> finAllServiceByTripId(int trip_id);

}
