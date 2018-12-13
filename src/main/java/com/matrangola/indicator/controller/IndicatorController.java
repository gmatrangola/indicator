package com.matrangola.indicator.controller;

import com.matrangola.indicator.aop.Profile;
import com.matrangola.indicator.aop.WatchDog;
import com.matrangola.indicator.data.model.Indicator;
import com.matrangola.indicator.service.IndicatorService;
import com.matrangola.indicator.validation.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/indicators")
public class IndicatorController {

    @Autowired
    IndicatorService indicatorService;

    /**
     * http://server/indicators/average/indicatorCode=IP.FOO
     */
    @Profile
    @RequestMapping(path ="/average")
    Double average(@RequestParam String indicatorCode) {
        return indicatorService.worldwideAverage(indicatorCode);
    }

    @Profile
    @GetMapping("above")
    public List<Indicator> aboveMin(@RequestParam String code, @RequestParam double min) {
        return indicatorService.aboveMin(code, min);
    }

    @Profile
    @GetMapping("aboveAvg")
    public List<Indicator> aboveAvg(@RequestParam String code) {
        return indicatorService.aboveAverage(code);
    }

    @Profile
    @WatchDog(timeout = 20)
    @GetMapping("/{countryCode}/{indexCode}/idx")
    public Indicator getIndicator(@PathVariable String countryCode,
                                  @PathVariable String indexCode, Principal principal) throws ResourceException {
        return indicatorService.getIndicator(countryCode, indexCode, principal.getName());
    }
}
