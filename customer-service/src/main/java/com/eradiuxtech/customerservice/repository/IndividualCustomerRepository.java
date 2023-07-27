package com.eradiuxtech.customerservice.repository;

import com.eradiuxtech.customerservice.entity.Customer;
import com.eradiuxtech.customerservice.entity.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Long> {

    IndividualCustomer findByCustomer(Customer customer);
}
