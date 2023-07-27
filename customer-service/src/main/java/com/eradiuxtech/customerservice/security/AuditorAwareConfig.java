package com.eradiuxtech.customerservice.security;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public class AuditorAwareConfig implements AuditorAware<String> {

    @Autowired
    private IAuthenticationFacade authenticationFacade;

    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = authenticationFacade.getAuthentication();

        if(!authentication.isAuthenticated()){
           throw new RuntimeException("User not authenticated");
        }else{
            return Optional.of(authentication.getName());
        }

    }


}