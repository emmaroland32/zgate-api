package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "individual_customer_properties")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerProperty extends CoreEntity implements Serializable {

    @OneToOne(cascade = CascadeType.ALL, optional = false, targetEntity = Customer.class)
    @JoinColumn(name = "ucid", referencedColumnName = "ucid", nullable = false)
    private Customer customer;

    @OneToOne(mappedBy = "individualCustomerProperty", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private IndividualCustomer individualCustomer;
}