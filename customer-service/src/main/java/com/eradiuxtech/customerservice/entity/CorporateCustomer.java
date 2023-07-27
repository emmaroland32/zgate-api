package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "corporate_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomer extends CoreEntity implements Serializable {

    @Column(name = "company_name", unique = true)
    String companyName;

    @OneToOne(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn( referencedColumnName = "ucid" ,name = "ucid" ,nullable = false)
    private Customer customer;

}