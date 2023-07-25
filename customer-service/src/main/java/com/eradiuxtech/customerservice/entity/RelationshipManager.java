package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "relationship_managers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelationshipManager extends CoreEntity implements Serializable {

    @OneToOne(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( referencedColumnName = "ucid", name = "ucid",nullable = false)
    private Customer customer;

    @Column(name = "first_name", unique = true)
    String firstName;

    @Column(name = "last_name", unique = true)
    String lastName;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "phone_number", unique = true)
    String phoneNumber;

    @ManyToOne(targetEntity = RelationshipManagerType.class)
    RelationshipManagerType relationshipManagerType;

    @Column(name = "comment")
    String comment;

}