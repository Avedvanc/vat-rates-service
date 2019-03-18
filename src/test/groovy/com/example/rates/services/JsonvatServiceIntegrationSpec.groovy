package com.example.rates.services

import com.example.rates.IntegrationSpecification
import com.example.rates.beans.CountryInfoBean
import com.example.rates.beans.RateType
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Subject

class JsonvatServiceIntegrationSpec extends IntegrationSpecification {

    @Subject
    @Autowired
    JsonvatService service

    def "should process response"() {
        given:
            getRequestReturnsResponse("/", getFile("test-response.txt"))
        when:
            List<CountryInfoBean> countries = service.list()
        then:
            countries.size() == 28
            countries.stream().filter({c -> c.name == "Czech Republic"})
                    .findFirst()
                    .get().standardRate == 21
    }

    def "should ignore unknown fields"() {
        given:
            def response = """{"rates": [
                                {
                                  "new_country_info": "123",
                                  "name": "Malta",
                                  "code": "MT",
                                  "country_code": "MT",
                                  "periods": [
                                    {
                                      "effective_from": "0000-01-01",
                                      "rates": {
                                        "reduced1": 5,
                                        "reduced2": 7,
                                        "standard": 18,
                                        "parking": 18,
                                        "unknownName": 10
                                      }
                                    },
                                    {
                                      "effective_from": "2016-01-01",
                                      "rates": {
                                        "reduced1": 1,
                                        "reduced2": 2,
                                        "standard": 3,
                                        "unknownName": 4
                                      }
                                    }
                                  ]
                                }
                              ]}"""
            getRequestReturnsResponse("/", response)
        when:
            List<CountryInfoBean> countries = service.list()
        then:
            countries.size() == 1
            CountryInfoBean malta = countries.get(0)
            malta.name == "Malta"
            malta.code == "MT"
            malta.rates.get(RateType.REDUCED_LOW) == 1
            malta.rates.get(RateType.REDUCED) == 2
            malta.rates.get(RateType.STANDARD) == 3
            !malta.rates.containsKey(RateType.PARKING)
    }

    private String getFile(String fileName){
        String result = ""

        ClassLoader classLoader = getClass().getClassLoader()
        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName))
        } catch (IOException e) {
            e.printStackTrace()
        }

        return result
    }

}
