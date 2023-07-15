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

    @Column(name = "title")
    String title;

    @Column(name = "first_name", nullable = false)
    String firstName;

    @Column(name = "last_name", nullable = false)
    String lastName;

    @Column(name = "email", unique = true)
    String email;

    @ManyToOne(targetEntity = SignatoryClass.class)
    @JoinColumn(name = "signatory_class", referencedColumnName = "type")
    SignatoryClass signatoryClass;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "signatory_id", referencedColumnName = "id")
    private List<Address> addresses;

    @OneToMany(targetEntity = Phone.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "phone_id", referencedColumnName = "id")
    private List<Phone> phones;
}
