package com.github.ageresgit.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherServerApplication {
    private final static Logger log = LogManager.getLogger(WeatherServerApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(WeatherServerApplication.class);
        log.info("WeatherServerApplication start");
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
