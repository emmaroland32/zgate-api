package com.eradiuxtech.customerservice;

import org.springframework.boot.SpringApplication;

public class TestApplication {
    public static void main(String[] args) {
        SpringApplication
                .from(CustomerServiceApplication::main)
                .with(ContainersConfig.class)
                .run(args);
    }
}