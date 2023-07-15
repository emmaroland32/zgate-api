package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "individual_customer_properties")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomerProperty extends CoreEntity {

    @OneToOne(mappedBy = "individualCustomerProperty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(mappedBy = "individualCustomerProperty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<IndividualCustomer> individualCustomers;
}
