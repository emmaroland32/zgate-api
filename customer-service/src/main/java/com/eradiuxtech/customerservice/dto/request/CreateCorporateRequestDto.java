package com.eradiuxtech.customerservice.dto.request;


import com.eradiuxtech.customerservice.entity.CompanyType;
import com.eradiuxtech.customerservice.entity.Country;
import com.eradiuxtech.customerservice.entity.Customer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCorporateRequestDto {

    @NotNull(message = "Company name cannot be null")
    private String companyName;

    private String companyShortName;

    @NotNull(message = "Company email cannot be null")
    private String companyEmail;

    private String companyRegistrationNumber;

    private Country countryOfIncorporation;

    private String companyWebsite;
}
