package com.eradiuxtech.customerservice.repository;

import com.eradiuxtech.customerservice.entity.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer, Long> {
}
