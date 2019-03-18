package com.example.rates.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class JsonvatResponseDto {

    private final String details;
    private final String version;
    private final List<VatRateDto> rates;

    @JsonCreator
    public JsonvatResponseDto(@JsonProperty("details") String details,
                              @JsonProperty("version") String version,
                              @JsonProperty("rates") List<VatRateDto> rates) {
        this.details = details;
        this.version = version;
        this.rates = rates;
    }
}
