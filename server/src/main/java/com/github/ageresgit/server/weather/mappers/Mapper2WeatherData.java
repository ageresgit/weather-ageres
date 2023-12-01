package com.github.ageresgit.server.weather.mappers;

import com.github.ageresgit.common.model.WeatherData;

public interface Mapper2WeatherData<T> {
    WeatherData map(T otherWeatherData);
    Class<?> fromClass();
}
