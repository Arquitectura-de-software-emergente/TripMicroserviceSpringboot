package com.tripmicroservice.tripmicroservice.http;

import com.tripmicroservice.tripmicroservice.dto.ServicesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Provider;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripResponse {
    private int Id;
    private String Title;
    private String Description;
    private int Duration;
    private String Difficulty;

    List<ServicesDto> servicesDtoList;
}
