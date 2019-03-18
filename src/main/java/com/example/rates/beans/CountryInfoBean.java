package com.example.rates.beans;

import static com.example.rates.beans.RateType.STANDARD;

import java.math.BigDecimal;
import java.util.Map;

import lombok.Value;

@Value
public class CountryInfoBean {

    private final String name;
    private final String code;
    private final Map<RateType, BigDecimal> rates;

    public BigDecimal getStandardRate() {
        return rates.get(STANDARD);
    }

}
