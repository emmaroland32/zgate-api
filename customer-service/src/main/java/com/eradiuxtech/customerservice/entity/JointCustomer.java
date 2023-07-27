package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "joint_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JointCustomer extends CoreEntity implements Serializable {

    @Column(name = "joint_name", nullable = false)
    private String jointName;

    private String description;

    @OneToOne(targetEntity = Customer.class, optional = false)
    @JoinColumn( referencedColumnName = "ucid" ,name = "ucid", nullable = false, unique = true)
   private Customer customer;


}