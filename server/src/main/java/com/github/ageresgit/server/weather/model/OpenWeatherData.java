package com.github.ageresgit.server.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@JsonIgnoreProperties
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpenWeatherData {
    OpenWeatherMainData main;
    OpenWeatherGeneral[] weather;
}
