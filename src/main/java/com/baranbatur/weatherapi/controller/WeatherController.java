package com.baranbatur.weatherapi.controller;

import com.baranbatur.weatherapi.dto.WeatherData;
import com.baranbatur.weatherapi.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    WeatherService weatherService;

    public WeatherController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/getDailyWeather")
    public List<WeatherData> getDailyWeather(@RequestParam String city, @RequestParam String country) {

        String url = "?q=" + city + "," + country + "&appid=";
        return weatherService.getWeatherDailyData(city, country, url);
    }

    @GetMapping("/getWeeklyWeather")
    public List<WeatherData> getWeeklyWeather(@RequestParam String city, @RequestParam String country) {

        String url = "?q=" + city + "," + country + "&appid=";
        return weatherService.getWeatherWeeklyData(city, country, url);
    }

    @GetMapping("/getMonthlyWeather")
    public List<WeatherData> getMonthlyWeather(@RequestParam String city, @RequestParam String country) {

        String url = "?q=" + city + "," + country + "&appid=";
        return weatherService.getWeatherMonthlyData(city, country, url);

    }

}
