package com.github.ageresgit.server.weather.model;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class NumberFormatConfig {
    public final static DecimalFormat formatToInt = new DecimalFormat("####");
    public final static DecimalFormat formatOneDigit = new DecimalFormat("####.#");
}
