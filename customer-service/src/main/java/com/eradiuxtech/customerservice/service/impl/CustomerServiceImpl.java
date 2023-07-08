package com.eradiuxtech.customerservice.service.impl;


import com.eradiuxtech.customerservice.convertor.CustomerMapper;
import com.eradiuxtech.customerservice.dto.request.ChangeStatusRequest;
import com.eradiuxtech.customerservice.dto.request.CreateCustomerRequest;
import com.eradiuxtech.customerservice.dto.response.CustomerResponseDto;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final CustomerRepository customerRepository;

    private final WebClient webClient;

    private  final ModelMapper modelMapper;

    private static final String USER_BASE_URL = "http://USER-SERVICE:9000/api/v1/users";
    @Override
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


    public CustomerResponseDto findCustomer(Long id){
        LOGGER.info("CustomerServiceImpl | findCustomer | started");

        try{
            Optional<Customer> customer = this.customerRepository.findById(id);
            if(customer.isEmpty()){
                throw new NotFoundException("Customer Not Found");
            }
            LOGGER.info("CustomerServiceImpl | findCustomer | success");
            return this.modelMapper.map(customer, CustomerResponseDto.class);

        }catch (Exception e){
            LOGGER.info("CustomerServiceImpl | changeStatus | error");
            throw new BadRequestException(e.getMessage());
        }
    }


        public String changeStatus(Long id, Status type, ChangeStatusRequest changeStatusRequest) {

            try {
                Optional<Customer> customer = customerRepository.findById(id);
                if(customer.isEmpty()){
                    throw new BadRequestException("Customer does not exist");
                }

                Customer _customer = customer.get();
                KeycloakUser _user = getAuthUserInfo();


                switch (type){
                    case REVIEW:
                        if(!_customer.getForReview()){
                            throw new BadRequestException("unavailable for review");
                        }
                        if(_customer.getIsReviewed()){
                            throw new BadRequestException("Already reviewed");
                        }
                        if(_customer.getIsApproved()){
                            throw new BadRequestException(("Already approved"));
                        }
                        if(Objects.equals(_customer.getCreatedBy(), _user.getUsername())){
                            throw new BadRequestException("Cannot review an entity created by you");
                        }
                        _customer.setReviewNote(changeStatusRequest.getNote());
                        _customer.setReviewedAt(LocalDateTime.now());
                        _customer.setStatus(Status.REVIEWED);
                        _customer.setReviewedBy(_user.getUsername());
                        _customer.setForApproval(true);

                    case APPROVAL:
                        if(!_customer.getForApproval()){
                            throw new BadRequestException("Unavailable for approval");
                        }
                        if(!_customer.getIsReviewed()){
                            throw new BadRequestException("Entity has not been reviewed");
                        }
                        if(_customer.getIsApproved()){
                            throw new BadRequestException(("Already approved"));
                        }
                        if(Objects.equals(_customer.getCreatedBy(), _user.getUsername())
                                || Objects.equals(_customer.getReviewedBy(), _user.getUsername())){
                            throw new BadRequestException("Cannot approve an Entity created by you");
                        }
                        _customer.setReviewNote(changeStatusRequest.getNote());
                        _customer.setReviewedAt(LocalDateTime.now());
                        _customer.setStatus(Status.APPROVED);
                        _customer.setReviewedBy(_user.getUsername());

                    case REJECTED:
                        if(!_customer.getForReview() ||  !_customer.getForApproval()){
                            throw new BadRequestException("Unavailable for rejection");
                        }
                        if(Objects.equals(_customer.getCreatedBy(), _user.getUsername())
                                || Objects.equals(_customer.getReviewedBy(), _user.getUsername())){
                            throw new BadRequestException("Cannot reject an Entity created by you");
                        }
                        if(_customer.getIsReviewed() && !_customer.getIsApproved()){
                            _customer.setStatus(Status.REVIEWED);
                            _customer.setRejectionStage(Status.APPROVAL);
                        }
                        if(!_customer.getIsReviewed() && !_customer.getIsApproved()){
                            _customer.setStatus(Status.PENDING);
                            _customer.setRejectionStage(Status.REVIEW);
                        }
                        _customer.setRejectionNote(changeStatusRequest.getNote());
                        _customer.setRejectedBy(_user.getUsername());
                        _customer.setRejectedAt(LocalDateTime.now());
                }

                customerRepository.save(_customer);
                LOGGER.info("CustomerServiceImpl | changeStatus | success");
                return "Successfully" + type;
            } catch (Exception e){
                LOGGER.info("CustomerServiceImpl | changeStatus | error");
                throw new BadRequestException(e.getMessage());
            }


        }


    private String pushForReview(Long id){

        LOGGER.info("CustomerServiceImpl | pushForReview | started");

        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isEmpty()) {
                throw new BadRequestException("Entity does not exist");
            }

            Customer _customer = customer.get();
            KeycloakUser _user = getAuthUserInfo();

            _customer.setForReview(true);
            customerRepository.save(_customer);
            LOGGER.info("CustomerServiceImpl | pushForReview | success");
            return "Entity pushed for review";

        } catch (Exception e){
            LOGGER.info("CustomerServiceImpl | pushForReview | error");
            throw new BadRequestException(e.getMessage());
        }


    }

    private KeycloakUser getAuthUserInfo(){

        LOGGER.info("CustomerServiceImpl | getAuthUserInfo started");

        KeycloakUser user = webClient.get().uri(USER_BASE_URL + "/me"
                                                                        ).retrieve()
                                                              .bodyToMono(KeycloakUser.class)
                                                              .block();

        assert user != null;

        LOGGER.info("CustomerServiceImpl | getAuthUserInfo | user result : " + user);

        return user;
    }
}

