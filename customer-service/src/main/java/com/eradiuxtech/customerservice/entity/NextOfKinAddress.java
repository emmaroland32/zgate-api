package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "next_of_kin_addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NextOfKinAddress extends CoreEntity implements Serializable {

    @ManyToOne(optional = false, targetEntity = NextOfKin.class)
    @JoinColumn(nullable = false)
    NextOfKin nextOfKin;

    @ManyToOne(targetEntity = AddressType.class)
    AddressType addressType;

    @Column(name = "address_line_1")
    String addressLine1;

    @Column(name = "address_line_2")
    String addressLine2;

    @ManyToOne(targetEntity = City.class)
    City city;

    @ManyToOne(optional = false, targetEntity = State.class)
    @JoinColumn(nullable = false)
    State state;

    @Column(name = "zip")
    String zip;

    @ManyToOne(optional = false, targetEntity = Country.class)
    @JoinColumn(nullable = false)
    Country country;

    @Column(name = "comment")
    String comment;

    @Column(name = "is_primary")
    Boolean isPrimary;

}