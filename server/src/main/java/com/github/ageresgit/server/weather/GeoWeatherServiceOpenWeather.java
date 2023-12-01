package com.github.ageresgit.server.weather;

import com.github.ageresgit.common.model.WeatherData;
import com.github.ageresgit.server.geography.GeoPosition;
import com.github.ageresgit.server.weather.mappers.Mapper2WeatherData;
import com.github.ageresgit.server.weather.model.OpenWeatherData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@Service
public class GeoWeatherServiceOpenWeather implements GeoWeatherService {

    private final Logger log = LogManager.getLogger(GeoWeatherServiceOpenWeather.class);
    private final String host;
    private final String token;
    private final RestTemplate restTemplate;
    private final Mapper2WeatherData<OpenWeatherData> mapper2WeatherData;

    GeoWeatherServiceOpenWeather(RestTemplate restTemplate,
                                 @Value("${weather.service.openweather.host}") String host,
                                 @Value("${weather.service.openweather.token}") String token,
                                 @Autowired Mapper2WeatherData<OpenWeatherData> mapperOpenWeather2WeatherData) {
        this.host = host;
        this.token = token;
        this.restTemplate = restTemplate;
        this.mapper2WeatherData = mapperOpenWeather2WeatherData;
    }

    @Override
    public ExternalWeatherService getWeatherService() {
        return ExternalWeatherService.OPENWEATHER;
    }

    public MultiValueMap<String, String> initQueryParameters(GeoPosition geoPosition) {
        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.set("appid", token);
        valueMap.set("lat", geoPosition.lat());
        valueMap.set("lon", geoPosition.lon());
        valueMap.set("units", "metric");
        valueMap.set("lang", "eng");

        return valueMap;
    }

    @Override
    public WeatherData getWeatherByLocation(GeoPosition geoPosition) {
        log.debug("Requesting {}, lat = {}, lon = {}", getWeatherService(), geoPosition.lat(), geoPosition.lon());
        URI uri = UriComponentsBuilder.fromUriString(host).queryParams(initQueryParameters(geoPosition)).build().toUri();
        log.debug("Final uri = {}", uri);
        var requestEntity = RequestEntity.get(uri).headers(HttpHeaders.EMPTY).build();
        ResponseEntity<OpenWeatherData> responseEntity = restTemplate.exchange(requestEntity, OpenWeatherData.class);
        return mapper2WeatherData.map(Objects.requireNonNull(responseEntity.getBody()));
    }
}