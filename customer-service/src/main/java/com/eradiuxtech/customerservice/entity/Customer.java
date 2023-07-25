package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.Review;
import com.eradiuxtech.customerservice.util.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
//@SQLDelete(sql = "UPDATE customers SET deleted = true WHERE id=?")
//@Filter(name = "deletedCustomerFilter", condition = "deleted = :isDeleted")
//@FilterDef(name = "deletedCustomerFilter", parameters = @ParamDef(name = "isDeleted", type = org.hibernate.type.descriptor.java.BooleanJavaType.class))
public class Customer extends Review implements Serializable {

    @Column(name = "ucid", updatable = false, nullable = false, unique = true)
    private Long ucid;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(nullable = false)
    private CustomerType customerType;

    @OneToOne(mappedBy = "customer")
    private IndividualCustomerProperty individualCustomerProperty;

    @OneToOne(mappedBy = "customer")
    private JointCustomerProperty jointCustomerProperty;

    @OneToOne(mappedBy = "customer")
    private CorporateCustomerProperty corporateCustomerProperty;

    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER)
    private MinorCustomerProperty minorCustomerProperty;

    @OneToOne(mappedBy = "customer", fetch = FetchType.EAGER ,cascade = CascadeType.ALL, orphanRemoval = true)
    private RelationshipManager relationshipManager;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerAddress> addresses;

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerPhone> phones;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NextOfKin> nextOfKins;

    @PrePersist
    public void prePersist() {
        this.setUcid(this.getId());
        this.setStatus(Status.PENDING);
    }

}