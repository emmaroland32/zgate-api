package com.eradiuxtech.customerservice.service.impl;

import com.eradiuxtech.customerservice.dto.CustomerTypeEnum;
import com.eradiuxtech.customerservice.dto.request.*;
import com.eradiuxtech.customerservice.dto.response.CustomerResponseDto;
import com.eradiuxtech.customerservice.dto.response.FindCustomerResponseDto;
import com.eradiuxtech.customerservice.entity.Customer;
import com.eradiuxtech.customerservice.entity.CustomerType;
import com.eradiuxtech.customerservice.entity.IndividualCustomer;
import com.eradiuxtech.customerservice.entity.core.Status;
import com.eradiuxtech.customerservice.exception.CustomNotFoundException;
import com.eradiuxtech.customerservice.repository.CustomerRepository;
import com.eradiuxtech.customerservice.repository.CustomerTypeRepository;
import com.eradiuxtech.customerservice.repository.IndividualCustomerRepository;
import com.eradiuxtech.customerservice.service.CustomerService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final ModelMapper modelMapper;

    private final CustomerTypeRepository customerTypeRepository;
    private final CustomerRepository customerRepository;

    private final IndividualCustomerRepository individualCustomerRepository;

    public CustomerServiceImpl(ModelMapper modelMapper, CustomerTypeRepository customerTypeRepository, CustomerRepository customerRepository, IndividualCustomerRepository individualCustomer, IndividualCustomerRepository individualCustomerRepository) {
        this.modelMapper = modelMapper;
        this.customerTypeRepository = customerTypeRepository;
        this.customerRepository = customerRepository;
        this.individualCustomerRepository = individualCustomerRepository;
    }





    @Transactional
    @Override
    public Customer createIndividual(CreateIndividualRequestDto customer) {
        LOGGER.info("CustomerServiceImpl | createIndividual | Started");
        try {
            Optional<CustomerType> customerType = customerTypeRepository.findById(1L);
            if (customerType.isEmpty()) {
                throw new CustomNotFoundException("CustomerType", 1L);
            }

            long Ucid = customerRepository.count() + 1;

            Customer newCustomer = new Customer();
            newCustomer.setUcid(Ucid);
            newCustomer.setCustomerType(customerType.get());
            LOGGER.info("CustomerServiceImpl | createIndividual | customerTypeId | " + customerType.get().getId());

                //Create Individual Customer
                IndividualCustomer individualCustomer = new IndividualCustomer();
                individualCustomer.setCustomer(newCustomer);
                individualCustomer.setEmail(customer.getEmail());
                individualCustomer.setFirstName(customer.getFirstName());
                individualCustomer.setLastName(customer.getLastName());
                individualCustomer.setUsername(customer.getUsername());
                individualCustomerRepository.save(individualCustomer);

            Customer customerResponse = customerRepository.save(newCustomer);
            LOGGER.info("CustomerServiceImpl | createIndividual | Success");
            return customerResponse;
        } catch (Exception e) {
            LOGGER.error("CustomerServiceImpl | createIndividual | Exception : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Transactional
    @Override
    public Customer createCorporate(CreateCorporateRequestDto customer) {
        return null;
    }


    @Transactional
    @Override
    public Customer createJoint(CreateJointRequestDto customer) {
        return null;
    }


    @Transactional
    @Override
    public Customer createMinor(CreateMinorRequestDto customer) {
        return null;
    }


    @Transactional
    public FindCustomerResponseDto findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException("Customer", id));
        FindCustomerResponseDto findCustomer = new FindCustomerResponseDto();
        if(customer.getCustomerType().getType().equals(CustomerTypeEnum.individual.name())) {
            IndividualCustomer individualCustomer = individualCustomerRepository.findByCustomer(customer);
            findCustomer.setIndividualCustomer(individualCustomer);
        }
        return modelMapper.map(findCustomer, FindCustomerResponseDto.class);
    }

    public List<CustomerResponseDto> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerResponseDto.class)).toList();
    }


    public List<CustomerResponseDto> listByStatus(Status status) {
        List<CustomerResponseDto> customers = customerRepository.findByStatus(status);
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerResponseDto.class)).toList();
    }

    public List<CustomerResponseDto> listByCustomerType(Long customerTypeId) {
        CustomerType customerType = customerTypeRepository.findById(customerTypeId).orElseThrow(() -> new CustomNotFoundException("CustomerType", customerTypeId));
        List<CustomerResponseDto> customers = customerRepository.findByCustomerType(customerType);
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerResponseDto.class)).toList();
    }


    public List<CustomerResponseDto> listWithPagination(int pageNo, int pageSize) {
        List<Customer> customers = customerRepository.findAll(PageRequest.of(pageNo, pageSize)).toList();
        return customers.stream().map(customer -> modelMapper.map(customer, CustomerResponseDto.class)).toList();
    }



    // Approval Workflow
    @Override
    public CustomerResponseDto forReview(RequestDto dto) {
        Customer customer = customerRepository.findById(dto.getId()).orElseThrow(() -> new CustomNotFoundException("Customer", dto.getId()));
        customer.forReview();
        return modelMapper.map(customerRepository.save(customer), CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto review(RequestDto dto) {
        Customer customer = customerRepository.findById(dto.getId()).orElseThrow(() -> new CustomNotFoundException("Customer", dto.getId()));
        customer.review();
        return modelMapper.map(customerRepository.save(customer), CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto reject(RequestDto dto) {
        Customer customer = customerRepository.findById(dto.getId()).orElseThrow(() -> new CustomNotFoundException("Customer", dto.getId()));
        customer.reject();
        return modelMapper.map(customerRepository.save(customer), CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto retrieve(RequestDto dto) {
        Customer customer = customerRepository.findById(dto.getId()).orElseThrow(() -> new CustomNotFoundException("Customer", dto.getId()));
        customer.retrieveFromRejection();
        return modelMapper.map(customerRepository.save(customer), CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto forApproval(RequestDto dto) {
        Customer customer = customerRepository.findById(dto.getId()).orElseThrow(() -> new CustomNotFoundException("Customer", dto.getId()));
        customer.forApproval();
        return modelMapper.map(customerRepository.save(customer), CustomerResponseDto.class);
    }

    @Override
    public CustomerResponseDto approve(RequestDto dto) {
       Customer customer = customerRepository.findById(dto.getId()).orElseThrow(() -> new CustomNotFoundException("Customer", dto.getId()));
         customer.approve();
        return modelMapper.map(customerRepository.save(customer), CustomerResponseDto.class);
    }

}