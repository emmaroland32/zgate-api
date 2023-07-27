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

    @Column(unique = true, nullable = false)
    private String companyName;

    private String companyShortName;

    @Column(unique = true, nullable = false)
    private String companyEmail;

    @Column(unique = true)
    private String companyRegistrationNumber;

    @ManyToOne
    private Country countryOfIncorporation;

    @Column
    private String companyWebsite;

    @ManyToOne
    private CompanyType companyType;

    @OneToOne(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn( referencedColumnName = "ucid" ,name = "ucid" ,nullable = false)
    private Customer customer;

}