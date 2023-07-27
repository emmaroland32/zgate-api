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
@Table(name = "signatories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Signatory extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = Title.class)
    private Title title;


    @ManyToOne(targetEntity = CorporateCustomerProperty.class, optional = false)
    @JoinColumn( name = "ucid", nullable = false)
    Customer customer;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "email", unique = true)
    String email;

    @ManyToOne(targetEntity = SignatoryClass.class)
    @JoinColumn(name = "signatory_class", referencedColumnName = "type")
    SignatoryClass signatoryClass;

    @OneToMany(targetEntity = SignatoryAddress.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SignatoryAddress> addresses;

    @OneToMany(targetEntity = SignatoryPhone.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SignatoryPhone> phones;
}