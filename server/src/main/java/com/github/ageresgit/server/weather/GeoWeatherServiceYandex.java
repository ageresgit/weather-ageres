package com.github.ageresgit.server.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ageresgit.common.model.WeatherData;
import com.github.ageresgit.server.geography.GeoPosition;
import com.github.ageresgit.server.weather.mappers.Mapper2WeatherData;
import com.github.ageresgit.server.weather.mappers.MapperYandexWeather2WeatherData;
import com.github.ageresgit.server.weather.model.YandexWeatherData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;


@Service
public class GeoWeatherServiceYandex implements GeoWeatherService{
    private final Logger log = LogManager.getLogger(GeoWeatherServiceYandex.class);
    private final String host;
    private final String token;
    private final RestTemplate restTemplate;
    private final Mapper2WeatherData<YandexWeatherData> mapper2WeatherData;

    public GeoWeatherServiceYandex(
            RestTemplate restTemplate,
            @Value("${weather.service.yandex.host}") String host,
            @Value("${weather.service.yandex.token}") String token,
            @Autowired Mapper2WeatherData<YandexWeatherData> mapperYandexWeather2WeatherData
    ) {
        this.host = host;
        this.token = token;
        this.mapper2WeatherData = mapperYandexWeather2WeatherData;
        this.restTemplate = restTemplate;
    }
    public MultiValueMap<String, String> initQueryParameters(GeoPosition geoPosition){
        MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<>();
        uriVariables.set("lang", "en_US");
        uriVariables.set("limit", "1");
        uriVariables.set("hours", "false");
        uriVariables.set("extra", "false");
        uriVariables.set("lat", geoPosition.lat());
        uriVariables.set("lon", geoPosition.lon());

        return uriVariables;
    }
    @Override
    public WeatherData getWeatherByLocation(GeoPosition geoPosition) {
        log.debug("Requesting {}, lat = {}, lon = {}", getWeatherService(), geoPosition.lat(), geoPosition.lon());
        URI uri = UriComponentsBuilder.fromUriString(host).queryParams(initQueryParameters(geoPosition)).build().toUri();
        log.debug("Final uri = {}", uri);
        var requestEntity = RequestEntity.get(uri).header("X-Yandex-API-Key", token).build();
        ResponseEntity<YandexWeatherData> responseEntity = restTemplate.exchange(requestEntity, YandexWeatherData.class);
        return mapper2WeatherData.map(Objects.requireNonNull(responseEntity.getBody()));
    }

    @Override
    public ExternalWeatherService getWeatherService() {
        return ExternalWeatherService.YANDEX;
    }

}
