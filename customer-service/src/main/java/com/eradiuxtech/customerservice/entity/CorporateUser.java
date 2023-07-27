package com.eradiuxtech.customerservice.entity;

import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "corporate_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorporateUser extends CoreEntity implements Serializable {
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

    @Column(name = "login_id")
    private String loginId;

    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "ucid" ,name = "ucid" ,nullable = false,unique = true)
    private Customer customer;

    @Column(name = "note")
    private String note;

    @PostPersist
    private void loginIdFormula() {
        String prefix = "MC";
        String suffix = "";
        if (this.id != null) {
            suffix = String.format("%07d", id);
            loginId = prefix + suffix;
        }
    }

    @PrePersist
    private void PrePersist() {
        username = username.toLowerCase();
        email = email.toLowerCase();
    }
}