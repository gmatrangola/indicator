package com.matrangola.indicator.service;

import com.matrangola.indicator.data.model.Indicator;
import com.matrangola.indicator.data.repository.CustomerRepository;
import com.matrangola.indicator.data.repository.IndicatorRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class IndicatorServiceTest {

    @TestConfiguration
    static class IndicatorServiceImplTestConfig {
        @Bean
        public IndicatorService indicatorService() {
            return new IndicatorServiceImpl();
        }
    }

    @Autowired
    private IndicatorService indicatorService;

    @MockBean
    private IndicatorRepository indicatorRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Before
    public void setup() {
        Indicator[] indicators = {
                mkIndicator("USA", "IP.FOO", 50.5),
                mkIndicator("GBR", "IP.FOO", 100.5),
                mkIndicator("ZZZ", "IP.FOO", 30.0)
        };

        Mockito.when(indicatorRepository.findAllByIndicatorCode("IP.FOO"))
                .thenReturn(Stream.of(indicators));

    }

    private Indicator mkIndicator(String countryCode, String indicatorCode, double year2017) {
        Indicator indicator = new Indicator();
        indicator.setCountryCode(countryCode);
        indicator.setIndicatorCode(indicatorCode);
        indicator.setYear2017(year2017);
        return indicator;
    }

    @Test
    public void worldwideAverage() {
        assertEquals(60.333,
                indicatorService.worldwideAverage("IP.FOO"), 0.001);
    }

    @Test
    public void aboveMin() {
    }

    @Test
    public void aboveAverage() {
    }
}