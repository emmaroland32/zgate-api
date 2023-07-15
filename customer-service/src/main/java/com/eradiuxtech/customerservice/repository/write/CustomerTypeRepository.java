package com.eradiuxtech.customerservice.repository.write;


import com.eradiuxtech.customerservice.entity.CustomerType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerTypeRepository extends JpaRepository<CustomerType, Long> {

}