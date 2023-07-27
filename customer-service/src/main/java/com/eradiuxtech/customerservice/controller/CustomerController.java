package com.eradiuxtech.customerservice.controller;

import com.eradiuxtech.customerservice.dto.request.CreateCorporateRequestDto;
import com.eradiuxtech.customerservice.dto.request.CreateIndividualRequestDto;
import com.eradiuxtech.customerservice.dto.request.CreateJointRequestDto;
import com.eradiuxtech.customerservice.dto.request.CreateMinorRequestDto;
import com.eradiuxtech.customerservice.dto.response.FindCustomerResponseDto;
import com.eradiuxtech.customerservice.entity.Customer;
import com.eradiuxtech.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer-service/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final CustomerService customerService;

    @PostMapping("/individual")
    public ResponseEntity<Customer> createIndividualCustomer(@Valid @RequestBody CreateIndividualRequestDto createIndividualRequestDto){
        LOGGER.info("CustomerController | createCustomer | started");
        Customer customer =  customerService.createIndividual(createIndividualRequestDto);
        LOGGER.info("CustomerController | createCustomer | success");
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }


    @PostMapping("/corporate")
    public ResponseEntity<Customer> createCorporateCustomer(@Valid @RequestBody CreateCorporateRequestDto createCorporateRequestDto){
        LOGGER.info("CustomerController | createCustomer | started");
        Customer customer =  customerService.createCorporate(createCorporateRequestDto);
        LOGGER.info("CustomerController | createCustomer | success");
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @PostMapping("/joint")
    public ResponseEntity<Customer> createJointCustomer(@Valid @RequestBody CreateJointRequestDto createJointRequestDto){
        LOGGER.info("CustomerController | createCustomer | started");
        Customer customer =  customerService.createJoint(createJointRequestDto);
        LOGGER.info("CustomerController | createCustomer | success");
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @PostMapping("/minor")
    public ResponseEntity<Customer> createMinorCustomer(@Valid @RequestBody CreateMinorRequestDto createMinorRequestDto){
        LOGGER.info("CustomerController | createCustomer | started");
        Customer customer =  customerService.createMinor(createMinorRequestDto);
        LOGGER.info("CustomerController | createCustomer | success");
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<FindCustomerResponseDto> getCustomerById(@PathVariable Long id){
        LOGGER.info("CustomerController | getCustomerById | started");
FindCustomerResponseDto customer =  customerService.findById(id);

        LOGGER.info("CustomerController | getCustomerById | success");
        return new ResponseEntity<FindCustomerResponseDto>(customer, HttpStatus.OK);
    }


}