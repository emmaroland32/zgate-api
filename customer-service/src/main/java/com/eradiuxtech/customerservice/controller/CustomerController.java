package com.eradiuxtech.customerservice.controller;

import com.eradiuxtech.customerservice.dto.request.ChangeStatusRequest;
import com.eradiuxtech.customerservice.dto.request.CreateCustomerRequest;
import com.eradiuxtech.customerservice.service.CustomerService;
import com.eradiuxtech.customerservice.util.Status;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/customer-service/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final CustomerService customerService;


    @GetMapping("/health")
    public ResponseEntity<?> health(){
        LOGGER.info("CustomerController | health is started");
        return ResponseEntity.ok("Customer Service is up and running");
    }
    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest){

        LOGGER.info("CustomerController | createCustomer started");
      String customer =  customerService.createCustomer(createCustomerRequest);
        return new ResponseEntity<String>(customer, HttpStatus.OK);
    }

    @PostMapping("/{id}/review")
    public ResponseEntity<String> reviewCustomer(@Valid @RequestBody ChangeStatusRequest changeStatusRequest, @PathVariable Long id){
        LOGGER.info("CustomerController | reviewCustomer started");
      String response = customerService.changeStatus(id, Status.REVIEWED, changeStatusRequest);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<String> approveCustomer(@Valid @RequestBody ChangeStatusRequest changeStatusRequest, @PathVariable Long id){
        LOGGER.info("CustomerController | approveCustomer | started");
        String response = customerService.changeStatus(id, Status.APPROVED, changeStatusRequest);
        LOGGER.info("CustomerController | approveCustomer | success");
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<String> rejectCustomer(@Valid @RequestBody ChangeStatusRequest changeStatusRequest, @PathVariable Long id){
        LOGGER.info("CustomerController | rejectCustomer | started");
        String response = customerService.changeStatus(id, Status.REJECTED, changeStatusRequest);
        LOGGER.info("CustomerController | rejectCustomer | success");
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}
