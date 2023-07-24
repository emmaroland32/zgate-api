package com.programming.techie.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) throws Exception {

        serverHttpSecurity
                .csrf(crsf -> crsf.disable())
                .authorizeExchange(exchange ->
                                           exchange.pathMatchers("/eureka/**").permitAll()
                                                   .pathMatchers("/webjars/**").permitAll()
                                                   .pathMatchers(HttpMethod.GET,"/swagger-ui.html").permitAll()
                                                   .pathMatchers(HttpMethod.GET,"/api-docs/swagger-config").permitAll()
                                                   .pathMatchers(HttpMethod.GET,"/customer-service/api-docs/**").permitAll()
                                                   .pathMatchers(HttpMethod.GET,"/api-docs/**").permitAll()
                                                   .anyExchange()
                                                   .authenticated());


                serverHttpSecurity.oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));
                return serverHttpSecurity.build();
    }
}