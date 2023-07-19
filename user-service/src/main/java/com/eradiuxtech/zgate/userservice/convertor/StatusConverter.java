package com.eradiuxtech.zgate.userservice.convertor;

import com.eradiuxtech.zgate.userservice.util.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        System.out.println(status.name());
        return status.name();
    }

    @Override
    public Status convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        System.out.println(code);


        return Stream.of(Status.values())
                     .filter(c -> c.name().equals(code))
                     .findFirst()
                     .orElseThrow(IllegalArgumentException::new);
    }
}
