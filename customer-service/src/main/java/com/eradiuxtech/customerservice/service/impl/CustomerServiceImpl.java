package com.eradiuxtech.customerservice.service.impl;

import com.eradiuxtech.customerservice.dto.CustomerTypeEnum;
import com.eradiuxtech.customerservice.dto.request.*;
import com.eradiuxtech.customerservice.dto.response.CustomerResponseDto;
import com.eradiuxtech.customerservice.dto.response.FindCustomerResponseDto;
import com.eradiuxtech.customerservice.entity.*;
import com.eradiuxtech.customerservice.entity.core.Status;
import com.eradiuxtech.customerservice.exception.CustomNotFoundException;
import com.eradiuxtech.customerservice.repository.*;
import com.eradiuxtech.customerservice.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final ModelMapper modelMapper;
    private final CustomerTypeRepository customerTypeRepository;
    private final CustomerRepository customerRepository;
    private final IndividualCustomerRepository individualCustomerRepository;
    private final CorporateCustomerRepository corporateCustomerRepository;
    private final JointCustomerRepository jointCustomerRepository;
    private final JointCustomerHolderRepository jointCustomerHolderRepository;

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

        LOGGER.info("CustomerServiceImpl | createCorporate | Started");
        try {
            Optional<CustomerType> customerType = customerTypeRepository.findById(2L);
            if (customerType.isEmpty()) {
                throw new CustomNotFoundException("CustomerType", 2L);
            }

            long Ucid = customerRepository.count() + 1;

            Customer newCustomer = new Customer();
            newCustomer.setUcid(Ucid);
            newCustomer.setCustomerType(customerType.get());
            LOGGER.info("CustomerServiceImpl | createCorporate | customerTypeId | " + customerType.get().getId());

            //Create Corporate Customer
            CorporateCustomer corporateCustomer = new CorporateCustomer();
            corporateCustomer.setCustomer(newCustomer);
            corporateCustomer.setCompanyName(customer.getCompanyName());
            corporateCustomer.setCompanyEmail(customer.getCompanyEmail());
            corporateCustomer.setCompanyShortName(customer.getCompanyShortName());
            corporateCustomer.setCompanyRegistrationNumber(customer.getCompanyRegistrationNumber());
            corporateCustomer.setCompanyWebsite(customer.getCompanyWebsite());
            corporateCustomer.setCountryOfIncorporation(customer.getCountryOfIncorporation());

            corporateCustomerRepository.save(corporateCustomer);
            Customer customerResponse = customerRepository.save(newCustomer);
            LOGGER.info("CustomerServiceImpl | createCorporate | Success");
            return customerResponse;
        } catch (Exception e) {
            LOGGER.error("CustomerServiceImpl | createCorporate | Exception : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Transactional
    @Override
    public Customer createJoint(CreateJointRequestDto customer) {
        LOGGER.info("CustomerServiceImpl | createJoint | Started");
        try {
            Optional<CustomerType> customerType = customerTypeRepository.findById(3L);
            if (customerType.isEmpty()) {
                throw new CustomNotFoundException("CustomerType", 3L);
            }

            long Ucid = customerRepository.count() + 1;

            Customer newCustomer = new Customer();
            newCustomer.setUcid(Ucid);
            newCustomer.setCustomerType(customerType.get());
            LOGGER.info("CustomerServiceImpl | createJoint | customerTypeId | " + customerType.get().getId());

            //Create Joint Customer
            JointCustomer jointCustomer = new JointCustomer();
            jointCustomer.setCustomer(newCustomer);
            jointCustomer.setJointName(customer.getJointName());
            jointCustomer.setDescription(customer.getDescription());

            if(customer.getJointHolders().size() < 2) {
                throw new RuntimeException("JointCustomerHolders must be at least 2");
            }

            if(Arrays.stream(customer.getJointHolders().stream().mapToDouble(CreateJointCustomerHolderRequestDto::getPercentage).toArray()).sum() != 100) {
                throw new RuntimeException("JointHolders Percentage Sum must be 100");
            }

            for(CreateJointCustomerHolderRequestDto jointCustomerHolderRequestDto : customer.getJointHolders()) {
                JointCustomerHolder jointCustomerHolder = new JointCustomerHolder();
                jointCustomerHolder.setCustomer(newCustomer);
                jointCustomerHolder.setFirstName(jointCustomerHolderRequestDto.getFirstName());
                jointCustomerHolder.setLastName(jointCustomerHolderRequestDto.getLastName());
                jointCustomerHolder.setEmail(jointCustomerHolderRequestDto.getEmail());
                jointCustomerHolder.setPercentage(jointCustomerHolderRequestDto.getPercentage());
                jointCustomerHolderRepository.save(jointCustomerHolder);
            }

            jointCustomerRepository.save(jointCustomer);
            Customer customerResponse = customerRepository.save(newCustomer);
            LOGGER.info("CustomerServiceImpl | createJoint | Success");
            return customerResponse;
        } catch (Exception e) {
            LOGGER.error("CustomerServiceImpl | createJoint | Exception : {}", e.getMessage());
            throw new RuntimeException(e);
        }
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