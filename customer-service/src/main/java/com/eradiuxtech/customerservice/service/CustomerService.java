package com.eradiuxtech.customerservice.service.impl;

import com.eradiuxtech.customerservice.dto.request.CreateCustomerRequestDto;
import com.eradiuxtech.customerservice.dto.response.CustomerResponseDto;



public interface CustomerService {
    CustomerResponseDto create(CreateCustomerRequestDto customer);
}
