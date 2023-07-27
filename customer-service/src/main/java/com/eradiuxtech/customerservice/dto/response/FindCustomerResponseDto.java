package com.eradiuxtech.customerservice.dto.response;

import com.eradiuxtech.customerservice.entity.CustomerType;
import com.eradiuxtech.customerservice.entity.core.Status;
import lombok.Data;


@Data
public class CustomerResponseDto {
    private Long ucid;
  private CustomerType customerType;
  private Status status;
}
