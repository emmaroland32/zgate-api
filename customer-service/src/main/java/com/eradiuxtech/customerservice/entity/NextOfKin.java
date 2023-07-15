package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "customer_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NextOfKin extends CoreEntity {
    @Column(name = "first_name", unique = true)
    String firstName;

    @Column(name = "last_name", unique = true)
    String lastName;

    @Column(name = "email", unique = true)
    String email;

    @OneToMany(targetEntity = Phone.class)
            @JoinColumn(referencedColumnName = "id")
    List<Phone> phones;

    @OneToMany(targetEntity = Address.class)
            @JoinColumn(referencedColumnName = "id")
    List<Address> addresses;

    @ManyToOne(targetEntity = Relationship.class)
            @JoinColumn(referencedColumnName = "id")
    Relationship relationship;

    @Column(name = "comment")
    String comment;
}
