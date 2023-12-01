package com.github.ageresgit.server.weather;

import com.github.ageresgit.common.model.WeatherData;
import com.github.ageresgit.server.geography.GeoPosition;

public interface GeoWeatherMultiService {
    WeatherData getWeather(GeoPosition geoPosition, ExternalWeatherService service);
}
