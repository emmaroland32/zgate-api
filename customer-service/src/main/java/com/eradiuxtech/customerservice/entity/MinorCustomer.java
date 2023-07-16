package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "minor_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MinorCustomer extends CoreEntity implements Serializable {

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "middle_name")
    String middleName;

    @Column(name = "email", unique = true)
    String email;

    @OneToOne(targetEntity = MinorCustomerProperty.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn( referencedColumnName = "ucid", name = "ucid" ,nullable = false)
    private MinorCustomerProperty minorCustomerProperty;

}
