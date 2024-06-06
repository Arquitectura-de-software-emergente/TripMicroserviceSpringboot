package com.tripmicroservice.tripmicroservice.client;

import com.tripmicroservice.tripmicroservice.dto.RatingDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RatingClient {

    private static final Logger logger = LoggerFactory.getLogger(RatingClient.class);
    private final WebClient webClient;
    private final ConcurrentHashMap<Integer, List<RatingDto>> cache = new ConcurrentHashMap<>();

    @Autowired
    public RatingClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8084/api/v1").build();
    }

    public List<RatingDto> getRatingsByTripId(int tripId) {

        try {
            logger.info("Fetching ratings for trip id: {}", tripId);
            List<RatingDto> ratings = webClient.get()
                    .uri("/ratings/by-trip/{tripId}", tripId)
                    .retrieve()
                    .bodyToFlux(RatingDto.class)
                    .collectList()
                    .block();
            cache.put(tripId, ratings);
            return ratings;
        } catch (WebClientResponseException e) {
            logger.error("Error fetching ratings for trip id: {}", tripId, e);
            return null;
        } catch (Exception e) {
            logger.error("Error fetching ratings for trip id: {}", tripId, e);
            return null;
        }
    }
}
