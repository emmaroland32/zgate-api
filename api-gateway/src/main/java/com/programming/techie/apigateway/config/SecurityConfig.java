package com.programming.techie.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity
public class SecurityConfig {



    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {

        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange ->
                                           exchange.pathMatchers("/eureka/**").permitAll()
                                                   .pathMatchers("/actuator/**", "/","/logout.html").permitAll()
                                                   .pathMatchers("/webjars/**").permitAll()
                                                   .pathMatchers(HttpMethod.GET,"/swagger-ui.html", "/api-docs/swagger-config", "/customer-service/api-docs/**", "/api-docs/**").permitAll()
                                                   .anyExchange()
                                                   .authenticated());

        serverHttpSecurity.oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));
        serverHttpSecurity.oauth2Login(Customizer.withDefaults());
        return serverHttpSecurity.build();
    }

}