package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Address extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = AddressType.class)
    @JoinColumn(referencedColumnName = "id")
    AddressType addressType;

    @Column(name = "address_line_1")
    String addressLine1;

    @Column(name = "address_line_2")
    String addressLine2;

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(referencedColumnName = "id")
    City city;

    @ManyToOne(targetEntity = State.class)
    @JoinColumn( referencedColumnName = "id")
    State state;

    @Column(name = "zip")
    String zip;

    @ManyToOne(targetEntity = Country.class, optional = false)
    @JoinColumn(referencedColumnName = "id")
    State country;

    @Column(name = "comment")
    String comment;

    @Column(name = "is_primary")
    Boolean isPrimary;

}
