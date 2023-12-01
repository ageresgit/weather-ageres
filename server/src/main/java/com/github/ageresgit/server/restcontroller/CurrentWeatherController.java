package com.github.ageresgit.server.restcontroller;

import com.github.ageresgit.server.geography.GeoPositionDataBase;
import com.github.ageresgit.server.weather.ExternalWeatherService;
import com.github.ageresgit.server.weather.GeoWeatherMultiService;
import com.github.ageresgit.server.weather.GeoWeatherMultiServiceDefault;
import com.github.ageresgit.server.weather.GeoWeatherService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.ageresgit.common.model.WeatherData;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Locale;

@RestController
public class CurrentWeatherController {
    private static final Logger log = LogManager.getLogger(CurrentWeatherController.class);
    GeoWeatherMultiService weatherMultiService;

    public CurrentWeatherController(GeoWeatherMultiService weatherMultiService) {
        this.weatherMultiService = weatherMultiService;
    }

    @GetMapping("/weather/now")
    public WeatherData getCurrentWeather(@RequestParam(value = "lat") String lat,
                                          @RequestParam(value = "lon") String lon,
                                          @RequestParam(value = "service") String serviceName
                                          )
    {
        return weatherMultiService.getWeather(new GeoPositionDataBase(lat, lon), ExternalWeatherService.valueOf(serviceName.toUpperCase(Locale.ROOT)));
    }
}
