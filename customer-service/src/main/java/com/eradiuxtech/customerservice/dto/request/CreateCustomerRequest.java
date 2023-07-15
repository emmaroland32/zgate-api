package com.eradiuxtech.customerservice.dto.request;


import com.eradiuxtech.customerservice.entity.CustomerType;
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

    @NotNull(message = "Customer Type is required")
    @NotBlank(message = "Customer Type must not be empty")
    CustomerType customerType;

}
