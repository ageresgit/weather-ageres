package com.github.ageresgit.server.weather.mappers;

import com.github.ageresgit.common.model.WeatherData;
import com.github.ageresgit.server.weather.model.NumberFormatConfig;
import com.github.ageresgit.server.weather.model.OpenWeatherData;
import org.springframework.stereotype.Component;

@Component
public class MapperOpenWeather2WeatherData implements Mapper2WeatherData<OpenWeatherData>{

    @Override
    public WeatherData map(OpenWeatherData otherWeatherData) {
        return new WeatherData(NumberFormatConfig.formatOneDigit.format(otherWeatherData.getMain().getTemp()),
                NumberFormatConfig.formatToInt.format(otherWeatherData.getMain().getHumidity()));
    }

    @Override
    public Class fromClass() {
        return OpenWeatherData.class;
    }
}
