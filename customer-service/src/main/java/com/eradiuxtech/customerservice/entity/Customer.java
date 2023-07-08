package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.io.Serializable;


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

    @Column(name = "first_name", nullable = false)
    protected String firstName;

    @Column(name = "last_name", nullable = false)
    protected String lastName;

    @Column(name = "email", nullable = false, unique = true)
    protected String email;

    @Column(name = "username", nullable = false, unique = true)
    protected String username;

    @Column(name = "ucid", updatable = false, nullable = false, unique = true)
    protected Long ucid;

    @Column(name = "login_id", updatable = false, nullable = false, unique = true, length = 10)
    protected String loginId;

    @Column(name = "note")
    protected String note;

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
