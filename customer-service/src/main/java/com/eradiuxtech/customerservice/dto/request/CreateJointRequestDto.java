package com.eradiuxtech.customerservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;


@Data
public class CreateJointRequestDto {

    @NotNull(message = "Joint name cannot be null")
    private String jointName;

    private String description;

    @NotNull(message = "Joint holders cannot be null")
   private List<CreateJointCustomerHolderRequestDto> jointHolders;

}
