package com.example.rates.services;

import static com.example.rates.beans.RateType.PARKING;
import static com.example.rates.beans.RateType.REDUCED;
import static com.example.rates.beans.RateType.REDUCED_LOW;
import static com.example.rates.beans.RateType.STANDARD;
import static com.example.rates.beans.RateType.SUPER_REDUCED;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.rates.beans.CountryInfoBean;
import com.example.rates.beans.RateType;
import com.example.rates.dto.JsonvatResponseDto;
import com.example.rates.dto.RatePeriodDto;
import com.example.rates.dto.VatRateDto;

public class JsonvatResponseToCountryInfoListTransformer {

    private static Map<String, RateType> nameToTypeMap;
    static {
        Map<String, RateType> typesMap = new HashMap<>();
        typesMap.put("standard", STANDARD);
        typesMap.put("super_reduced", SUPER_REDUCED);
        typesMap.put("reduced1", REDUCED_LOW);
        typesMap.put("reduced2", REDUCED);
        typesMap.put("reduced", REDUCED);
        typesMap.put("parking", PARKING);
        nameToTypeMap = Collections.unmodifiableMap(typesMap);
    }

    public static List<CountryInfoBean> transform(JsonvatResponseDto response) {
        return response.getRates().stream()
                .map(JsonvatResponseToCountryInfoListTransformer::transform)
                .collect(toList());
    }

    private static CountryInfoBean transform(VatRateDto rate) {
        return new CountryInfoBean(
                rate.getName(),
                rate.getCountryCode(),
                rate.getPeriods().stream()
                        .max(Comparator.comparing(RatePeriodDto::getEffectiveFrom))
                        .map(JsonvatResponseToCountryInfoListTransformer::transform)
                        .orElse(new HashMap<>()));
    }

    private static Map<RateType, BigDecimal> transform(RatePeriodDto ratePeriod) {
        return ratePeriod.getRates().entrySet().stream()
                .filter(e -> nameToTypeMap.containsKey(e.getKey()))
                .collect(toMap(e -> nameToTypeMap.get(e.getKey()), Map.Entry::getValue));
    }


}
