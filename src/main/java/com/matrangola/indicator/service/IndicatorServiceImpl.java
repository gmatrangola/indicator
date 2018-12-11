package com.matrangola.indicator.service;

import com.matrangola.indicator.data.model.Indicator;
import com.matrangola.indicator.data.repository.IndicatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class IndicatorServiceImpl implements IndicatorService{
    @Autowired
    IndicatorRepository indicatorRepository;

    public Double worldwideAverage(String code) {
        Stream<Indicator> allByIndicatorCode = indicatorRepository
                .findAllByIndicatorCode(code);
        OptionalDouble average = allByIndicatorCode
                .filter(indicator -> indicator.getYear2017() != null)
                .mapToDouble(Indicator::getYear2017).average();
        if (average.isPresent()) return average.getAsDouble();
        else return null;
    }

    public List<Indicator> aboveMin(String code, Double min) {
        return indicatorRepository
                .findAllByIndicatorCode(code)
                .filter(indicator -> indicator.getYear2017() != null
                        && indicator.getYear2017() > min)
                .collect(Collectors.toList());
    }

    public List<Indicator> aboveAverage(String code) {
        return aboveMin(code, worldwideAverage(code));
    }
}
