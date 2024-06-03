package com.tripmicroservice.tripmicroservice.client;

import com.tripmicroservice.tripmicroservice.dto.ServicesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class ServiceClientImpl implements ServiceClient{
    private final WebClient webClient;

    @Autowired
    public ServiceClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:5279/api").build();
        System.out.println("webClient: " + webClient);
    }

    @Override
    public List<ServicesDto> finAllServiceByTripId(int tripId) {
        try {

            System.out.println("try: " + tripId);
            return webClient.get()
                    .uri("/service/service/{tripId}", tripId)
                    .retrieve()
                    .bodyToFlux(ServicesDto.class)
                    .collectList()
                    .block();

        } catch (WebClientResponseException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}




