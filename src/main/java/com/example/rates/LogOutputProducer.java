package com.example.rates;

import static java.lang.String.format;
import static java.util.Comparator.comparing;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.rates.beans.CountryInfoBean;
import com.example.rates.services.CountryRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Profile("dev")
@RequiredArgsConstructor
public class LogOutputProducer implements CommandLineRunner {

    private final CountryRateService service;

    @Override
    public void run(String... args) {
        final List<CountryInfoBean> countries = service.list();

        log.debug("Countries with highest standard rate:");
        countries
                .stream()
                .sorted(comparing(CountryInfoBean::getStandardRate).reversed())
                .limit(3)
                .forEach(LogOutputProducer::debugCountry);

        log.debug("Countries with lowest standard rate:");
        countries
                .stream()
                .sorted(comparing(CountryInfoBean::getStandardRate))
                .limit(3)
                .forEach(LogOutputProducer::debugCountry);
    }

    private static void debugCountry(CountryInfoBean country) {
        log.debug(format("Country: %s, standard rate: %s", country.getName(), country.getStandardRate()));
    }

}
