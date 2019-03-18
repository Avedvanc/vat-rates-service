package com.example.rates.services

import com.example.rates.beans.CountryInfoBean
import com.example.rates.beans.RateType
import com.example.rates.dto.JsonvatResponseDto
import spock.lang.Specification

import static com.example.rates.beans.RateType.PARKING
import static com.example.rates.beans.RateType.REDUCED
import static com.example.rates.beans.RateType.REDUCED_LOW
import static com.example.rates.beans.RateType.STANDARD
import static com.example.rates.beans.RateType.SUPER_REDUCED

class JsonvatResponseToCountryInfoListTransformerSpec extends Specification {

    def "should transform to country" () {
        given:
            JsonvatResponseDto response = JsonvatResponseFixture.aSimpleExample()
        when:
            List<CountryInfoBean> countries = JsonvatResponseToCountryInfoListTransformer.transform(response)
        then:
            countries.size() == 3
            countries == expected()
    }

    private static List<CountryInfoBean> expected() {
        Map<RateType, BigDecimal> spainRates = new HashMap<>()
        spainRates.put(SUPER_REDUCED, 4)
        spainRates.put(REDUCED, 10)
        spainRates.put(STANDARD, 21)
        Map<RateType, BigDecimal> luxembourgRates = new HashMap<>()
        luxembourgRates.put(SUPER_REDUCED, 3)
        luxembourgRates.put(REDUCED_LOW, 8)
        luxembourgRates.put(STANDARD, 17)
        luxembourgRates.put(PARKING, 13)
        Map<RateType, BigDecimal> netherlandsRates = new HashMap<>()
        netherlandsRates.put(REDUCED, 8)
        netherlandsRates.put(STANDARD, 17)
        return [
                new CountryInfoBean("Spain", "ES", spainRates),
                new CountryInfoBean("Luxembourg", "LU", luxembourgRates),
                new CountryInfoBean("Netherlands", "NL", netherlandsRates)
        ]
    }

}
