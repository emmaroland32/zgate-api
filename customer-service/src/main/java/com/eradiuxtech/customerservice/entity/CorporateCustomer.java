package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "corporate_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomer extends CoreEntity implements Serializable {

    @Column(name = "company_name", unique = true)
    String companyName;

    @ManyToOne(targetEntity = CorporateCustomerProperty.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn( referencedColumnName = "id", nullable = false)
    private CorporateCustomerProperty corporateCustomerProperty;

}