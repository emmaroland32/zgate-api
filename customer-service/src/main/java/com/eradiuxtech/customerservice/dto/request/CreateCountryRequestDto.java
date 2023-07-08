package com.eradiuxtech.customerservice.dto.request;


import lombok.Data;

@Data
public class CreateCountryRequestDto {

    protected String name;
    protected String code;
    protected String phoneCode;
    protected String description;

}
