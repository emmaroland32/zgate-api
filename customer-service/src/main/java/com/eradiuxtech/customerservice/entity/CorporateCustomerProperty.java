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
@Table(name = "corporate_customer_properties")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerProperty extends CoreEntity implements Serializable {

    @Column(name = "director_count", nullable = false)
    private Long directorCount;

    @Column(name = "signatory_count", nullable = false)
    private Long signatoryCount;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CompanyType companyType;

    @OneToOne(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( referencedColumnName = "ucid", name = "ucid", nullable = false)
    private Customer customer;

    @OneToOne(mappedBy = "corporateCustomerProperty", cascade = CascadeType.ALL)
    private CorporateCustomer corporateCustomer;

    @OneToMany(mappedBy = "corporateCustomerProperty", cascade = CascadeType.ALL)
    private List<Signatory> signatories;
}