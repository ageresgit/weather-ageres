package com.github.ageresgit.server.weather.mappers;

import com.github.ageresgit.common.model.WeatherData;
import com.github.ageresgit.server.weather.model.NumberFormatConfig;
import com.github.ageresgit.server.weather.model.YandexWeatherData;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Component
public class MapperYandexWeather2WeatherData implements Mapper2WeatherData<YandexWeatherData> {
    public WeatherData map(YandexWeatherData yandexWeatherData) {
        return new WeatherData(NumberFormatConfig.formatOneDigit.format(yandexWeatherData.getFact().getTemp()),
                NumberFormatConfig.formatToInt.format(yandexWeatherData.getFact().getHumidity()));
    }

    @Override
    public Class fromClass() {
        return YandexWeatherData.class;
    }
}
