package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "relationship_managers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RelationshipManager extends CoreEntity {

    @Column(name = "first_name", unique = true)
    String firstName;

    @Column(name = "last_name", unique = true)
    String lastName;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "phone_number", unique = true)
    String phoneNumber;

    @ManyToOne(targetEntity = RelationshipManagerType.class)
    @JoinColumn(referencedColumnName = "id")
    RelationshipManagerType relationshipManagerType;

    @Column(name = "comment")
    String comment;

}
