package com.eradiuxtech.customerservice.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
    @NotBlank(message = "First Name must not be empty")
    @NotNull(message = "First Name is required")
    protected String firstName;

    @NotBlank(message = "Last Name must not be empty")
    @NotNull(message = "Last Name is required")
    protected String lastName;

    @NotBlank(message = "Email must not be empty")
    @NotNull(message = "Email is required")
    @Email(message = "Provide a valid email")
    protected String email;

    @NotBlank(message = "Username must not be empty")
    @NotNull(message = "Username is required")
    @Length(min = 3, message = "Length must be more than 3 characters")
    protected String username;

}
