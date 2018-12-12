package com.matrangola.indicator.service;

import com.matrangola.indicator.data.model.Indicator;
import com.matrangola.indicator.data.repository.CustomerRepository;
import com.matrangola.indicator.data.repository.IndicatorRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class IndicatorServiceTest {


    @MockBean
    private IndicatorRepository indicatorRepository;

    @MockBean
    private CustomerRepository customerRepository;

    private IndicatorService indicatorService;

    @Before
    public void setup() {
        indicatorService = new IndicatorServiceImpl(indicatorRepository, customerRepository);
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
        List<Indicator> above = indicatorService.aboveMin("IP.FOO", 32.2);
        assertNotNull(above);
        assertEquals(2, above.size());

    }

    @Test
    public void aboveAverage() {
        List<Indicator> aboveAverage = indicatorService.aboveAverage("IP.FOO");
    }
}