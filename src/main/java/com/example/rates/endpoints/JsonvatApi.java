package com.example.rates.endpoints;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.rates.dto.JsonvatResponseDto;

@FeignClient(name = "jsonvat", url = "${provider.url}")
public interface JsonvatApi {

    @GetMapping
    JsonvatResponseDto getRates();

}
