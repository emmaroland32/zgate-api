package com.eradiuxtech.customerservice.dto.request;


import lombok.Data;

@Data
public class CreateCorporateRequestDto {

    private String name;

    private String username;

    private String email;
}
