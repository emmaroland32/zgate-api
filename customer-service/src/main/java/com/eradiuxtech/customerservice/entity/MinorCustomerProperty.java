package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "minor_customer_properties")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MinorCustomerProperty extends CoreEntity implements Serializable {

    @OneToOne(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( referencedColumnName = "ucid", name = "ucid" ,nullable = false)
    private Customer customer;

    @OneToOne(mappedBy = "minorCustomerProperty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MinorCustomer minorCustomer;

    @OneToMany(mappedBy = "minorCustomerProperty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MinorGuardianProperty> minorGuardianProperties;
}
