package com.eradiuxtech.customerservice.service;


import com.eradiuxtech.customerservice.dto.request.ChangeStatusRequest;
import com.eradiuxtech.customerservice.dto.request.CreateCustomerRequest;
import com.eradiuxtech.customerservice.dto.response.CustomerResponseDto;
import com.eradiuxtech.customerservice.util.Status;
import org.springframework.data.domain.Sort;

import java.util.Map;


public interface CustomerService {
 public String createCustomer(CreateCustomerRequest createCustomerRequestDto);

 public  Map<String, Object> listWithPagination(int page, int size, String[] sort);

 public CustomerResponseDto findCustomer(Long id);

 public String approvalWorkflow(Long id, Status type, ChangeStatusRequest changeStatusRequest);

}
