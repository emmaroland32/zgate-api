package com.eradiuxtech.customerservice.repository.write;

import com.eradiuxtech.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
