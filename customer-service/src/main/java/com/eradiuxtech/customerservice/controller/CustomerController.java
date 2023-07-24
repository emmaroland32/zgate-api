package com.eradiuxtech.customerservice.controller;

import com.eradiuxtech.customerservice.dto.request.ChangeStatusRequest;
import com.eradiuxtech.customerservice.dto.request.CreateCustomerRequest;
import com.eradiuxtech.customerservice.dto.response.CustomerResponseDto;
import com.eradiuxtech.customerservice.service.CustomerService;
import com.eradiuxtech.customerservice.util.Status;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;


@RestController
@RequestMapping("/api/customer-service/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final CustomerService customerService;



    @GetMapping("/health")
    public String index(Principal principal) {
        return principal.getName();
    }

    @GetMapping()
    public ResponseEntity<Map<String, Object>> listWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort){
        LOGGER.info("CountryController | listWithPagination | started");

        try {
            Map<String, Object> response = customerService.listWithPagination(page, size, sort);
            LOGGER.info("CountryController | listWithPagination | Success");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.info("CountryController | listWithPagination | Error");

            throw new BadRequestException(e.getMessage());
        }
    }



    @PostMapping("/create-customer")
    public ResponseEntity<String> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest){

        LOGGER.info("CustomerController | createCustomer | started");
      String customer =  customerService.createCustomer(createCustomerRequest);
        return new ResponseEntity<String>(customer, HttpStatus.OK);
    }

    @PutMapping("/{id}/approval-workflow")
    public ResponseEntity<String> approvalWorkFlow(@Valid @RequestBody ChangeStatusRequest changeStatusRequest, @RequestParam Status type,  @PathVariable Long id){
        LOGGER.info("CustomerController | approvalWorkFlow | started");
      String response = customerService.approvalWorkflow(id, type, changeStatusRequest);
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable Long id){
        LOGGER.info("CustomerController | getCustomer | started");
        CustomerResponseDto response = customerService.findCustomer(id);
        LOGGER.info("CustomerController | getCustomer | success");
        return new ResponseEntity<CustomerResponseDto>(response, HttpStatus.OK);
    }
}
