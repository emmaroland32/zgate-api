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
@Table(name = "joint_customer_properties")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JointCustomerProperty extends CoreEntity implements Serializable {

    @Column(name = "joint_account_name", unique = true)
    String jointAccountName;

    @Column(name = "customer_count", nullable = false)
    private Long customerCount;

    @Column(name = "even_share", nullable = false, columnDefinition = "boolean default true")
    private Boolean evenShare;

    @OneToOne(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn( referencedColumnName = "ucid", name = "ucid" ,nullable = false)
    private Customer customer;

    @OneToMany( mappedBy = "jointCustomerProperty", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JointCustomer> jointCustomers;

}