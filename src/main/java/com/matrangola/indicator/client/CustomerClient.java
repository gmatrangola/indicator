package com.matrangola.indicator.client;

import com.matrangola.indicator.config.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "microcustomer", configuration = FeignClientConfiguration.class)
public interface CustomerClient {
    @RequestMapping(method = RequestMethod.GET, path = "/customers/logAccess/{username}")
    boolean logAccess(
            @PathVariable("username") String username,
            @RequestParam("countryCode") String countryCode,
            @RequestParam("indexCode") String indexCode);
}
