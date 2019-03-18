package com.example.rates.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class VatRateDto {

    private final String name;
    private final String code;
    private final String countryCode;
    private final List<RatePeriodDto> periods;

    @JsonCreator
    public VatRateDto(@JsonProperty("name") String name,
                      @JsonProperty("code") String code,
                      @JsonProperty("country_code") String countryCode,
                      @JsonProperty("periods") List<RatePeriodDto> periods) {
        this.name = name;
        this.code = code;
        this.countryCode = countryCode;
        this.periods = periods;
    }

}
