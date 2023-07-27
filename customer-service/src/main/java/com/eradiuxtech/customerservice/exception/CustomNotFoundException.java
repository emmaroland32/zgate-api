package com.eradiuxtech.customerservice.exception;

public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException(String name, Long id) {
        super(name + " with id: '" + id + "' not found");
    }
}