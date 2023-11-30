package com.github.ageresgit.server.restcontroller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.ageresgit.common.model.WeatherData;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestController
@AllArgsConstructor
public class CurrentWeatherController {
    private static final Logger log = LogManager.getLogger(CurrentWeatherController.class);
    @GetMapping("/weather/now")
    public WeatherData getCurrentWeatherByCityId(@RequestParam(value = "cityId", required = false) String cityId) {
        log.info("Got request, cityId = {}", cityId);
        return new WeatherData("+10", "75");
    }
}
