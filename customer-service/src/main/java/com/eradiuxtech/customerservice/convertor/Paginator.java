package com.eradiuxtech.customerservice.convertor;

import com.eradiuxtech.customerservice.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paginator {


    public static Map<String, Object> paginateCustomer(Page<Customer> pageData){
        Map<String, Object> response = new HashMap<>();
        response.put("currentPage", pageData.getNumber());
        response.put("size", pageData.getSize());
        response.put("totalItems", pageData.getTotalElements());
        response.put("totalPages", pageData.getTotalPages());
return response;

    }
}
