package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "minor_guardian_properties")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MinorGuardianProperty extends CoreEntity implements Serializable {

    @Column(name = "is_current_customer", columnDefinition = "boolean default false")
    Boolean isCurrentCustomer;

    @ManyToOne(targetEntity = MinorCustomerProperty.class, cascade = CascadeType.ALL)
    @JoinColumn( referencedColumnName = "ucid", name = "ucid",nullable = false)
    private MinorCustomerProperty minorCustomerProperty;

    @OneToOne(mappedBy = "minorGuardianProperty", cascade = CascadeType.ALL)
    private MinorGuardian guardian;
}
