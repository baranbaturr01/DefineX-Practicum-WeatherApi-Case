package com.baranbatur.weatherapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class WeatherData {

    private Long dt;
    private String dt_txt;
    private Main main;
    private List<Weather> weather;
    private Wind wind;
    private Clouds clouds;
}
