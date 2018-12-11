package com.matrangola.indicator.controller;

import com.matrangola.indicator.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
