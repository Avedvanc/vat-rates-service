package com.example.rates.services

import com.example.rates.dto.JsonvatResponseDto
import com.example.rates.dto.RatePeriodDto
import com.example.rates.dto.VatRateDto

import java.time.LocalDate

class JsonvatResponseFixture {

    static JsonvatResponseDto aSimpleExample() {
        return new JsonvatResponseDto("", "", [
                new VatRateDto("Spain", "ES", "ES", [
                        new RatePeriodDto(LocalDate.of(0000, 1, 1), [
                                "super_reduced": 4,
                                "reduced": 10,
                                "standard": 21
                        ] as HashMap<String, BigDecimal>)
                ]),
                new VatRateDto("Luxembourg", "LU", "LU", [
                        new RatePeriodDto(LocalDate.of(0000, 1, 1), [
                                "super_reduced": 3,
                                "reduced1": 6,
                                "reduced2": 12,
                                "standard": 15,
                                "parking": 12
                        ] as HashMap<String, BigDecimal>),
                        new RatePeriodDto(LocalDate.of(2015, 1, 1), [
                                "super_reduced": 3,
                                "reduced1": 8,
                                "reduced2": 14,
                                "standard": 17,
                                "parking": 12
                        ] as HashMap<String, BigDecimal>),
                        new RatePeriodDto(LocalDate.of(2016, 1, 1), [
                                "super_reduced": 3,
                                "reduced1": 8,
                                "standard": 17,
                                "parking": 13
                        ] as HashMap<String, BigDecimal>)
                ]),
                new VatRateDto("Netherlands", "NL", "NL", [
                        new RatePeriodDto(LocalDate.of(0000, 1, 1), [
                                "reduced": 6,
                                "standard": 21,
                        ] as HashMap<String, BigDecimal>),
                        new RatePeriodDto(LocalDate.of(2015, 1, 1), [
                                "reduced": 8,
                                "standard": 17,
                        ] as HashMap<String, BigDecimal>)
                ])
        ])
    }

}
