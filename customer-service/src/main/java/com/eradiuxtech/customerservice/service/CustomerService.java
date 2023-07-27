package com.eradiuxtech.customerservice.service;

import com.eradiuxtech.customerservice.dto.request.*;
import com.eradiuxtech.customerservice.dto.response.CustomerResponseDto;
import com.eradiuxtech.customerservice.dto.response.FindCustomerResponseDto;
import com.eradiuxtech.customerservice.entity.Customer;


public interface CustomerService {
    Customer createIndividual(CreateIndividualRequestDto customer);
    Customer createCorporate(CreateCorporateRequestDto customer);
    Customer createJoint(CreateJointRequestDto customer);
    Customer createMinor(CreateMinorRequestDto customer);

    FindCustomerResponseDto findById(Long id);

    CustomerResponseDto review(RequestDto dto);
    CustomerResponseDto forReview(RequestDto dto);

    CustomerResponseDto reject(RequestDto dto);
    CustomerResponseDto retrieve(RequestDto dto);

    CustomerResponseDto forApproval(RequestDto dto);

    CustomerResponseDto approve(RequestDto dto);
}
