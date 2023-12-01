package com.github.ageresgit.server.weather;

import com.github.ageresgit.common.model.WeatherData;
import com.github.ageresgit.server.geography.GeoPosition;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class GeoWeatherMultiServiceDefault implements GeoWeatherMultiService{
    private final Map<ExternalWeatherService, GeoWeatherService> serviceMap;

    public GeoWeatherMultiServiceDefault(@Autowired List<GeoWeatherService> serviceList) {
        serviceMap = serviceList.stream().collect(Collectors.toMap(GeoWeatherService::getWeatherService, Function.identity()));
    }

    @Override
    public WeatherData getWeather(GeoPosition geoPosition, ExternalWeatherService service) {
        @NonNull GeoWeatherService geoWeatherService = serviceMap.get(service);
        return geoWeatherService.getWeatherByLocation(geoPosition);
    }
}
