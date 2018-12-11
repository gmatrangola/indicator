package com.matrangola.indicator.controller;

import com.matrangola.indicator.data.model.Indicator;
import com.matrangola.indicator.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/indicators")
public class IndicatorController {

    @Autowired
    IndicatorService indicatorService;

    /**
     * http://server/indicators/average/indicatorCode=IP.FOO
     */
    @RequestMapping(path ="/average")
    Double average(@RequestParam String indicatorCode) {
        return indicatorService.worldwideAverage(indicatorCode);
    }

    @GetMapping("above")
    public List<Indicator> aboveMin(@RequestParam String code, @RequestParam double min) {
        return indicatorService.aboveMin(code, min);
    }

    @GetMapping("aboveAvg")
    public List<Indicator> aboveAvg(@RequestParam String code) {
        return indicatorService.aboveAverage(code);
    }

}
