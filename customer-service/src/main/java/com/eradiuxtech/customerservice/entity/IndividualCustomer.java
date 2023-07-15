package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "individual_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IndividualCustomer extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = Title.class)
    @JoinColumn(referencedColumnName = "id")
    Title title;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "login_id", updatable = false, nullable = false, unique = true, length = 10)
    private String loginId;

    @ManyToOne(targetEntity = IndividualCustomerProperty.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private IndividualCustomerProperty individualCustomerProperty;

    @Column(name = "note")
    private String note;

    @PrePersist
    private void PrePersist() {
        username = username.toLowerCase();
        email = email.toLowerCase();
        loginId = loginId.toUpperCase();
        if(note == null){
            note = "Customer Created by " + createdBy + " at " + createdAt;
        }
    }
}
