package com.matrangola.indicator.service;

import com.matrangola.indicator.data.model.Indicator;
import com.matrangola.indicator.data.repository.IndicatorRepository;
import org.assertj.core.util.Lists;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class IndicatorServiceTest {

    private Indicator indicator;

    @MockBean
    IndicatorRepository indicatorRepository;

    @TestConfiguration
    static class IndicatorServiceImplTestConfig {
        @Bean
        public IndicatorService indicatorService() {
            return new IndicatorServiceImpl();
        }
    }

    @Autowired
    IndicatorService indicatorService;

    @Before
    public void setup() {
        indicator = new Indicator();
        indicator.setCountryCode("USA");
        indicator.setIndicatorCode("IP.FOO");
        indicator.setYear2017(123.4);

        Mockito.when(indicatorRepository.findAllByIndicatorCode("IP.FOO"))
                .thenReturn(Stream.of(indicator));

    }

    @Test
    public void worldwideAverage() {
        Assert.assertEquals(123.4, (double) indicatorService.worldwideAverage("IP.FOO"));
    }

    @Test
    public void aboveMin() {
    }

    @Test
    public void aboveAverage() {
    }
}