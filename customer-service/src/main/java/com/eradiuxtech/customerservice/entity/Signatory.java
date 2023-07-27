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

    @ManyToOne(targetEntity = Customer.class, optional = false)
    @JoinColumn( referencedColumnName = "ucid" ,name = "ucid", nullable = false)
    private Customer customer;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
   private String lastName;

    @Column(name = "email", unique = true)
   private String email;

    @ManyToOne(targetEntity = SignatoryClass.class)
    @JoinColumn(name = "signatory_class", referencedColumnName = "type")
  private SignatoryClass signatoryClass;

    @OneToMany(targetEntity = SignatoryAddress.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SignatoryAddress> addresses;

    @OneToMany(targetEntity = SignatoryPhone.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SignatoryPhone> phones;

}