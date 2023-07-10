package com.eradiuxtech.customerservice.service.impl;


import com.eradiuxtech.customerservice.convertor.ApprovalMapper;
import com.eradiuxtech.customerservice.convertor.CustomerMapper;
import com.eradiuxtech.customerservice.convertor.Paginator;
import com.eradiuxtech.customerservice.dto.request.ChangeStatusRequest;
import com.eradiuxtech.customerservice.dto.request.CreateCustomerRequest;
import com.eradiuxtech.customerservice.dto.response.CustomerResponseDto;
import com.eradiuxtech.customerservice.dto.response.ListCustomerResponseDto;
import com.eradiuxtech.customerservice.entity.Customer;
import com.eradiuxtech.customerservice.entity.shared.KeycloakUser;
import com.eradiuxtech.customerservice.repository.CustomerRepository;
import com.eradiuxtech.customerservice.service.CustomerService;
import com.eradiuxtech.customerservice.util.Status;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final CustomerRepository customerRepository;

    private final WebClient webClient;

    private final ModelMapper modelMapper;

    private static final String USER_BASE_URL = "http://USER-SERVICE:9000/api/v1/users";


    public Map<String, Object> listWithPagination(int page, int size, String[] sort) {
        LOGGER.info("CountryController | listWithPagination | Started");

        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();


            if (size > 10) {
                size = 10;
            }

            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
            Page<Customer> pageCustomers = customerRepository.findAll(pagingSort);
            List<Customer> customers = pageCustomers.getContent();

            Map<String, Object> response =  Paginator.paginateCustomer(pageCustomers);
            response.put("data", customers);


            LOGGER.info("CountryController | listWithPagination | Success");
            return response;
        } catch (Exception e) {
            LOGGER.info("CountryController | listWithPagination | Error");
            throw new BadRequestException(e.getMessage());
        }

    }

    public String createCustomer(CreateCustomerRequest createCustomerRequest) {

        String prefix = "M";
        String suffix = "";

        long customerCount = customerRepository.count();

        try {
            Customer createCustomer = CustomerMapper.createCustomer(createCustomerRequest);
            createCustomer.setUcid(customerCount + 1);
            suffix = String.format("%08d", customerCount + 1);
            createCustomer.setLoginId(prefix + suffix);
            createCustomer.setStatus(Status.PENDING);
            customerRepository.save(createCustomer);

            LOGGER.info("CustomerServiceImpl | createCustomer success");
            return "Customer Created Successfully, Awaiting Review";
        } catch (Exception e) {
            LOGGER.info("CustomerServiceImpl | createCustomer error");
            throw new BadRequestException(e.getMessage());
        }
    }


    public CustomerResponseDto findCustomer(Long id) {
        LOGGER.info("CustomerServiceImpl | findCustomer | started");

        try {
            Optional<Customer> customer = this.customerRepository.findById(id);
            if (customer.isEmpty()) {
                throw new NotFoundException("Customer Not Found");
            }
            LOGGER.info("CustomerServiceImpl | findCustomer | success");
            return this.modelMapper.map(customer, CustomerResponseDto.class);

        } catch (Exception e) {
            LOGGER.info("CustomerServiceImpl | changeStatus | error");
            throw new BadRequestException(e.getMessage());
        }
    }


    public String approvalWorkflow(Long id, Status type, ChangeStatusRequest changeStatusRequest) {

        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isEmpty()) {
                throw new BadRequestException("Customer does not exist");
            }

            Customer _customer = new ApprovalMapper().customerWorkFlow(type, changeStatusRequest, customer.get());

            customerRepository.save(_customer);
            LOGGER.info("CustomerServiceImpl | changeStatus | success");
            return _customer.getStatus() + " Successfully";
        } catch (Exception e) {
            LOGGER.info("CustomerServiceImpl | changeStatus | error");
            throw new BadRequestException(e.getMessage());
        }


    }
}

