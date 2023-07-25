package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "minor_guardian_addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MinorGuardianAddress extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = MinorGuardian.class)
    MinorGuardian minorGuardian;

    @ManyToOne(targetEntity = AddressType.class)
    AddressType addressType;

    @Column(name = "address_line_1")
    String addressLine1;

    @Column(name = "address_line_2")
    String addressLine2;

    @ManyToOne(targetEntity = City.class)
    City city;

    @ManyToOne(targetEntity = State.class)
    State state;

    @Column(name = "zip")
    String zip;

    @ManyToOne(targetEntity = Country.class, optional = false)
    Country country;

    @Column(name = "comment")
    String comment;

    @Column(name = "is_primary")
    Boolean isPrimary;

}