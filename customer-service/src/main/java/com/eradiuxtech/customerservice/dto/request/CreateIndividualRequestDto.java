package com.eradiuxtech.customerservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class CreateIndividualRequestDto {
    private String firstName;

    private String lastName;

    private String username;

    @Email(message = "Email should be valid")
    private String email;

}
