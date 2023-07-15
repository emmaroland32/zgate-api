package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.Review;
import com.eradiuxtech.customerservice.util.Status;
import jakarta.persistence.*;
import lombok.*;
import org.glassfish.jersey.internal.util.Producer;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE customers SET deleted = true WHERE id=?")
@Filter(name = "deletedCustomerFilter", condition = "deleted = :isDeleted")
@FilterDef(name = "deletedCustomerFilter", parameters = @ParamDef(name = "isDeleted", type = org.hibernate.type.descriptor.java.BooleanJavaType.class))
public class Customer extends Review implements Serializable {
    @Column(name = "ucid", updatable = false, nullable = false, unique = true)
    private Long ucid;

    @OneToOne( targetEntity = CustomerType.class)
    @JoinColumn( referencedColumnName = "id")
    private CustomerType customerType;

    @OneToOne(targetEntity = IndividualCustomerProperty.class)
    @JoinColumn( referencedColumnName = "id")
    private IndividualCustomerProperty individualCustomerProperty;

    @OneToOne(targetEntity = JointCustomerProperty.class)
    @JoinColumn( referencedColumnName = "id")
    private JointCustomerProperty jointCustomerProperty;

    @OneToOne(targetEntity = CorporateCustomerProperty.class)
    @JoinColumn( referencedColumnName = "id")
    private CorporateCustomerProperty corporateCustomerProperty;

    @OneToOne(targetEntity = RelationshipManager.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn( referencedColumnName = "id")
    private RelationshipManager relationshipManager;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn( referencedColumnName = "id")
    private List<Address> addresses;

    @OneToMany(targetEntity = Phone.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(referencedColumnName = "id")
    private List<Phone> phones;

    @OneToMany(targetEntity = NextOfKin.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn( referencedColumnName = "id")
    private List<NextOfKin> nextOfKins;

    @PrePersist
    public void prePersist() {
        this.setUcid(this.getId());
        this.setStatus(Status.PENDING);
    }

}
