package com.eradiuxtech.customerservice.security;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.security.Principal;
import java.util.Optional;

public class AuditorAwareConfig implements AuditorAware<String> {
    @Autowired
    private HttpServletRequest request;

    @Override
    public Optional<String> getCurrentAuditor() {

        return Optional.of("siyanda");
    }


}