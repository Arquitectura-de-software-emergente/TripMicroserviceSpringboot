package com.tripmicroservice.tripmicroservice.api.client;

import com.tripmicroservice.tripmicroservice.interfaces.dto.ReservationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ReservationClient {

    private static final Logger logger = LoggerFactory.getLogger(ReservationClient.class);
    private final WebClient webClient;
    private final ConcurrentHashMap<Integer, List<ReservationDto>> cache = new ConcurrentHashMap<>();

    @Autowired
    public ReservationClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083/api/v1").build();
    }

    public List<ReservationDto> getReservationsByTripId(int tripId) {
        if (cache.containsKey(tripId)) {
            return cache.get(tripId);
        }
        try {
            logger.info("Fetching reservations for trip id: {}", tripId);
            List<ReservationDto> reservations = webClient.get()
                    .uri("/reservations/by-trip/{tripId}", tripId)
                    .retrieve()
                    .bodyToFlux(ReservationDto.class)
                    .collectList()
                    .block();
            cache.put(tripId, reservations);
            return reservations;
        } catch (WebClientResponseException e) {
            logger.error("Error fetching reservations for trip id: {}", tripId, e);
            return null;
        } catch (Exception e) {
            logger.error("Error fetching reservations for trip id: {}", tripId, e);
            return null;
        }
    }
}
