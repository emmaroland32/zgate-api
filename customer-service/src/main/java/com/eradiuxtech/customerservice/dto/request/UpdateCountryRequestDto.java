package com.eradiuxtech.customerservice.dto.request;


import lombok.Data;

@Data
public class UpdateCountryRequestDto {
    protected String name;
    protected String code;
    protected String phoneCode;
    protected String description;
}
