package com.eradiuxtech.customerservice.dto.response;

import com.eradiuxtech.customerservice.entity.Customer;
import com.eradiuxtech.customerservice.entity.CustomerType;
import com.eradiuxtech.customerservice.entity.IndividualCustomer;
import com.eradiuxtech.customerservice.entity.core.Status;
import lombok.Data;


@Data
public class FindCustomerResponseDto {
  private IndividualCustomer individualCustomer;
}
