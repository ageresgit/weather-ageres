package com.github.ageresgit.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherData {
    String temperature;
    String humidity;
}
