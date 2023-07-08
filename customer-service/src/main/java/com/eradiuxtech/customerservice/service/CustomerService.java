package com.eradiuxtech.customerservice.service;


import com.eradiuxtech.customerservice.dto.request.ChangeStatusRequest;
import com.eradiuxtech.customerservice.dto.request.CreateCustomerRequest;
import com.eradiuxtech.customerservice.dto.response.CustomerResponseDto;
import com.eradiuxtech.customerservice.util.Status;


public interface CustomerService {
 public String createCustomer(CreateCustomerRequest createCustomerRequestDto);


 public CustomerResponseDto findCustomer(Long id);

 public String changeStatus(Long id, Status type, ChangeStatusRequest changeStatusRequest);

}
