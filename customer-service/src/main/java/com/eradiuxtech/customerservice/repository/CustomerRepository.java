package com.eradiuxtech.customerservice.repository;

import com.eradiuxtech.customerservice.dto.response.CustomerResponseDto;
import com.eradiuxtech.customerservice.entity.Customer;
import com.eradiuxtech.customerservice.entity.CustomerType;
import com.eradiuxtech.customerservice.entity.core.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT new com.eradiuxtech.customerservice.dto.response.CustomerResponseDto(c.id, c.customerType, c.status) FROM Customer c WHERE c.status = ?1")
    List<CustomerResponseDto> findByStatus(Status status);


    @Query("SELECT new com.eradiuxtech.customerservice.dto.response.CustomerResponseDto(c.id, c.customerType, c.status) FROM Customer c WHERE c.customerType = ?1")
    List<CustomerResponseDto> findByCustomerType(CustomerType customerType);
}
