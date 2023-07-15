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

    @OneToOne(mappedBy = "corporateCustomerProperty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(mappedBy = "corporateCustomerProperty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CorporateCustomer> corporateCustomers;
}
