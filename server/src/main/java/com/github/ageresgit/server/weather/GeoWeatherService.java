package com.github.ageresgit.server.weather;

import com.github.ageresgit.common.model.WeatherData;
import com.github.ageresgit.server.geography.GeoPosition;

public interface GeoWeatherService {
    WeatherData getWeatherByLocation(GeoPosition geoPosition);
    ExternalWeatherService getWeatherService();
}
