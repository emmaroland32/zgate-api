package com.eradiuxtech.customerservice.convertor;


import com.eradiuxtech.customerservice.dto.request.CreateCustomerRequest;
import com.eradiuxtech.customerservice.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public static Customer createCustomer(CreateCustomerRequest createCustomerRequest){
        Customer customer =  new Customer();
        customer.setCustomerType(createCustomerRequest.getCustomerType());
        return customer;
    }
}
