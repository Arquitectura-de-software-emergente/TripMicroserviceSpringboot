package com.tripmicroservice.tripmicroservice.interfaces.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {
    private int id;
    private int score;
    private int touristId;
    private int tripId;
    private Date creationDate;

}
