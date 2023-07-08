package com.eradiuxtech.customerservice.dto.request;


import lombok.Data;

@Data
public class CreateStateRequestDto {

    protected String name;
    protected String code;
    protected String countryCode;
    protected String description;

}
