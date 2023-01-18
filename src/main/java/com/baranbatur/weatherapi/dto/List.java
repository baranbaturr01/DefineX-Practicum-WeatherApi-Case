package com.baranbatur.weatherapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service

public class List {
    private String dt_txt;
    private Main main;
}
