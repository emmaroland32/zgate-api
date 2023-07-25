package com.eradiuxtech.customerservice.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
public class CustomerResponseDto {
    protected String firstName;

    protected String lastName;

    protected String email;

    protected String username;
    protected Boolean isReviewed;
    protected Boolean isApproved;
}
