package com.eradiuxtech.customerservice.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String name, Long id) {
        super(name + " with id: '" + id + "' not found");
    }
}