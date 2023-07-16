package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "directors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Director extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = CorporateCustomer.class, optional = false)
    @JoinColumn(referencedColumnName = "ucid", name = "ucid", nullable = false)
    CorporateCustomer corporateCustomer;

    @ManyToOne(targetEntity = Title.class)
    @JoinColumn(referencedColumnName = "id")
    Title title;

    @Column(name = "first_name", unique = true)
    String firstName;

    @Column(name = "last_name", unique = true)
    String lastName;

    @Column(name = "email", unique = true)
    String email;

}
