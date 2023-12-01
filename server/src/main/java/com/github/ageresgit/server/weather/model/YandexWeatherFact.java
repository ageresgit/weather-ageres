package com.github.ageresgit.server.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class YandexWeatherFact {
    double temp;
    double humidity;
    double pressure_mm;
    String condition;
}
