package com.example.rates.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.rates.beans.CountryInfoBean;
import com.example.rates.endpoints.JsonvatApi;
import lombok.RequiredArgsConstructor;

/**
 * Anti Corruption Layer
 */
@Service
@RequiredArgsConstructor
public class JsonvatService implements CountryRateService {

    private final JsonvatApi jsonvatApi;

    @Override
    public List<CountryInfoBean> list() {
        return JsonvatResponseToCountryInfoListTransformer.transform(jsonvatApi.getRates());
    }

}
