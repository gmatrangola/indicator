package com.matrangola.indicator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("microcustomer")
public interface CustomerClient {
    @RequestMapping(method = RequestMethod.GET, path = "/customers/logAccess/{username}")
    boolean logAccess(@PathVariable String username, @RequestParam String countryCode, @RequestParam String indexCode);
}
