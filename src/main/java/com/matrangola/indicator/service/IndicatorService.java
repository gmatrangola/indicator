package com.matrangola.indicator.service;

import com.matrangola.indicator.data.model.Indicator;
import com.matrangola.indicator.validation.ResourceException;

import java.util.List;

public interface IndicatorService {
    Double worldwideAverage(String code);

    List<Indicator> aboveMin(String code, Double min);

    List<Indicator> aboveAverage(String code);

    Indicator getIndicator(String countryCode, String indexCode, String email) throws ResourceException;
}
