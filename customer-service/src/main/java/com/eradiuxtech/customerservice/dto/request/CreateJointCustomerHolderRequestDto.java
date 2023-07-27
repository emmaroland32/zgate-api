package com.eradiuxtech.customerservice.dto.request;

import com.eradiuxtech.customerservice.entity.Title;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateJointCustomerHolderRequestDto {

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    private String description;

    private String middleName;

    private Title title;

    @NotNull(message = "Percentage cannot be null")
    private Long percentage;

}
