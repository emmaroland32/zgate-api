package com.eradiuxtech.customerservice.dto.request;

import com.eradiuxtech.customerservice.entity.Title;
import jakarta.validation.constraints.Email;
import lombok.Data;


@Data
public class CreateJointCustomerHolderRequestDto {
    private String firstName;

    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    private String description;

    private String middleName;

    private Title title;

    private Long percentage;

}
