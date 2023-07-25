package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "customer_phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPhone extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = Customer.class, optional = false )
    @JoinColumn(referencedColumnName = "ucid", name = "ucid", nullable = false)
    Customer customer;

    @ManyToOne(targetEntity = PhoneType.class, optional = false)
    PhoneType phoneType;

    @Column(name = "number", unique = true)
    String number;

    @Column(name = "extension")
    String extension;

    @ManyToOne(targetEntity = Country.class, optional = false)
    @JoinColumn(nullable = false)
    Country country;

    @Column(name = "area_code")
    String areaCode;

    @Column(name = "comment")
    String comment;
}