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
public class ReservationDto {
    private int id;
    private int tripId;
    private int touristId;
    private Date reservationDate;
    private Date startDate;
    private String status;
}
