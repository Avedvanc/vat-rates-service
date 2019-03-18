package com.example.rates


import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.util.SocketUtils
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@ActiveProfiles("test")
@SpringBootTest(classes = [Application])
@ContextConfiguration(initializers = WireMockInitializer)
class IntegrationSpecification extends Specification {

    public static final int WIRE_MOCK_PORT = SocketUtils.findAvailableTcpPort()

    @Rule
    WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.wireMockConfig().port(WIRE_MOCK_PORT), false)

    void getRequestReturnsResponse(String path, String json) {
        MappingBuilder mappingBuilder = WireMock.get(WireMock.urlPathEqualTo(path))
                .willReturn(
                WireMock.aResponse()
                        .withStatus(200)
                        .withHeader('Content-type', APPLICATION_JSON_VALUE)
                        .withBody(json))
        wireMockRule.stubFor(mappingBuilder)
    }

    static class WireMockInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues values = TestPropertyValues.of(
                    "wiremockPort=$WIRE_MOCK_PORT"
            )
            values.applyTo(applicationContext)
        }
    }

}
