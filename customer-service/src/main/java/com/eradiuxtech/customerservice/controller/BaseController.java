package com.eradiuxtech.customerservice.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController("BaseController")
@RequestMapping("/api/customer-service/")
@RequiredArgsConstructor
public class BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @GetMapping()
    public String index(Principal principal) {

        LOGGER.info("ServiceController | index | started");
        String principal1 = principal.getName();
        LOGGER.info("ServiceController | index | successful | {}", principal1);
        return principal1;
    }

}
