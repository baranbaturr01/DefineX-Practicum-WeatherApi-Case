package com.baranbatur.weatherapi.service;

import com.baranbatur.weatherapi.dto.Forecast;
import com.baranbatur.weatherapi.dto.Main;
import com.baranbatur.weatherapi.dto.WeatherData;
import com.baranbatur.weatherapi.exception.WeatherException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${openweathermap.api.key}")
    private String apiKey;

    private final String weatherApiUrl = "http://api.openweathermap.org/data/2.5/forecast";

    public List<WeatherData> getWeatherDailyData(String city, String country, String uri) {
        String url = weatherApiUrl + uri + apiKey + "&units=metric";
        System.out.println(url);
        try {
            Forecast forecast = restTemplate.getForObject(url, Forecast.class, city, country);
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);

            List<WeatherData> dailyForecast = new ArrayList<>();
            for (WeatherData data : forecast.getList()) {
                LocalDateTime forecastDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(data.getDt()), ZoneId.of("UTC"));
                if (forecastDate.toLocalDate().equals(tomorrow)) {
                    dailyForecast.add(data);
                }
            }
            return dailyForecast;

        } catch (RestClientException e) {
            throw new WeatherException("Error while getting weather data", e);
        }
    }

    public List<WeatherData> getWeatherWeeklyData(String city, String country, String uri) {
        String url = weatherApiUrl + uri + apiKey + "&units=metric";
        try {
            Forecast forecast = restTemplate.getForObject(url, Forecast.class, city, country);
            LocalDate today = LocalDate.now();
            LocalDate endOfWeek = today.plusDays(7);

            List<WeatherData> weeklyForecast = new ArrayList<>();
            for (WeatherData data : forecast.getList()) {
                // convert the forecast timestamp to LocalDateTime
                LocalDateTime forecastDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(data.getDt()), ZoneId.of("UTC"));
                // check if the forecast date is within the next 7 days
                if (forecastDate.toLocalDate().isAfter(today) && forecastDate.toLocalDate().isBefore(endOfWeek)) {
                    weeklyForecast.add(data);
                }
            }
            return weeklyForecast;

        } catch (RestClientException e) {
            throw new WeatherException("Error while getting weekly weather data", e);
        } catch (WeatherException e) {
            throw new WeatherException("Error while getting weekly weather data", e);
        }
    }


    public List<WeatherData> getWeatherMonthlyData(String city, String country, String uri) {
        String url = "http://api.openweathermap.org/data/2.5/forecast" + uri + apiKey + "&units=metric";
        try {
            Forecast forecast = restTemplate.getForObject(url, Forecast.class, city, country);
            LocalDate today = LocalDate.now();
            LocalDate endOfmonth = today.plusDays(30);

            List<WeatherData> montlyForecast = new ArrayList<>();
            for (WeatherData data : forecast.getList()) {
                // convert the forecast timestamp to LocalDateTime
                LocalDateTime forecastDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(data.getDt()), ZoneId.of("UTC"));
                // check if the forecast date is within the next 7 days
                if (forecastDate.toLocalDate().isAfter(today) && forecastDate.toLocalDate().isBefore(endOfmonth)) {
                    montlyForecast.add(data);
                }
            }
            return montlyForecast;
        } catch (RestClientException e) {
            throw new WeatherException("Error while getting monthly weather data", e);
        }
    }
}
