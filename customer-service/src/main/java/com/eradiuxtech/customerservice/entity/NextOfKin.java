package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "next_of_kins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NextOfKin extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = Customer.class, optional = false)
    @JoinColumn( name = "ucid", nullable = false)
    Customer customer;

    @Column(name = "first_name", unique = true)
    String firstName;

    @Column(name = "last_name", unique = true)
    String lastName;

    @Column(name = "email", unique = true)
    String email;

    @OneToMany(mappedBy = "nextOfKin", cascade = CascadeType.ALL, orphanRemoval = true)
    List<NextOfKinPhone> phones;

    @OneToMany(mappedBy = "nextOfKin", cascade = CascadeType.ALL, orphanRemoval = true)
    List<NextOfKinAddress> addresses;

    @ManyToOne(targetEntity = Relationship.class)
    @JoinColumn(referencedColumnName = "id")
    Relationship relationship;

    @Column(name = "comment")
    String comment;
}