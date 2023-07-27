package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "joint_customer_holders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JointCustomerHolder extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = Title.class)
    private Title title;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String middleName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private String loginId;

    private Long percentage;

    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ucid" ,name = "ucid" ,nullable = false)
    private Customer customer;

    private String description;

    @PostPersist
    @PostUpdate
    public void prePersist() {
        String prefix = "MJ";
        String suffix = "";
        if (this.id != null) {
            suffix = String.format("%07d", id);
            loginId = prefix + suffix;
        }
    }

}