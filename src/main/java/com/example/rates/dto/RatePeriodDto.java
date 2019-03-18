package com.example.rates.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class RatePeriodDto {

    private final LocalDate effectiveFrom;
    private final Map<String, BigDecimal> rates;

    @JsonCreator
    public RatePeriodDto(@JsonProperty("effective_from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate effectiveFrom,
                         @JsonProperty("rates") Map<String, BigDecimal> rates) {
        this.effectiveFrom = effectiveFrom;
        this.rates = rates;
    }
}
