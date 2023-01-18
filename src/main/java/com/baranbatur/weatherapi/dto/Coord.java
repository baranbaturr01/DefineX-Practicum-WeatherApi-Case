package com.baranbatur.weatherapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class Coord {
    private double lon;
    private double lat;


    @Override
    public String toString() {
        return "Coord{" + "lon=" + lon + ", lat=" + lat + '}';
    }
}
