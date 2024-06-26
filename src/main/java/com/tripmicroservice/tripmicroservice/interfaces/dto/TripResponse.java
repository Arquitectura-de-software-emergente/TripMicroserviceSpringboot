package com.tripmicroservice.tripmicroservice.interfaces.dto;

import com.tripmicroservice.tripmicroservice.interfaces.dto.RatingDto;
import com.tripmicroservice.tripmicroservice.interfaces.dto.ReservationDto;
import com.tripmicroservice.tripmicroservice.interfaces.dto.ServicesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String fechaRegistro;
    private double precio;
    private String photo1;
    private String photo2;
    private String photo3;
    private String photo4;
    private String photo5;


    List<ServicesDto> servicesList;
    private List<ReservationDto> reservations; // Incluir la lista de reservas
    private List<RatingDto> ratings; // Incluir la lista de reservas

}