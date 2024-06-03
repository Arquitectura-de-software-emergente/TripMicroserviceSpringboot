package com.tripmicroservice.tripmicroservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServicesDto {
    private int Id;
    private String Name;
    private String Description;
    private Double Cost;
    private String Availability;
}
