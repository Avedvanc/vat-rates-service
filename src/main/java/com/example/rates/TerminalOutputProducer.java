package com.example.rates;

import static java.util.Comparator.comparing;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.rates.beans.CountryInfoBean;
import com.example.rates.services.CountryRateService;
import lombok.RequiredArgsConstructor;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class TerminalOutputProducer implements CommandLineRunner {

    private final CountryRateService service;

    @Override
    public void run(String... args) {
        final List<CountryInfoBean> countries = service.list();

        System.out.println("Countries with highest standard rate:");
        countries
                .stream()
                .sorted(comparing(CountryInfoBean::getStandardRate).reversed())
                .limit(3)
                .forEach(TerminalOutputProducer::handsomeCountryPrint);

        System.out.println("Countries with lowest standard rate:");
        countries
                .stream()
                .sorted(comparing(CountryInfoBean::getStandardRate))
                .limit(3)
                .forEach(TerminalOutputProducer::handsomeCountryPrint);
    }

    private static void handsomeCountryPrint(CountryInfoBean country) {
        System.out.println(String.format("Country: %s, standard rate: %s", country.getName(), country.getStandardRate()));
    }

}
