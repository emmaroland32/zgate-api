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
@Table(name = "joint_customers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JointCustomer extends CoreEntity implements Serializable {

    @Column(name = "joint_id", nullable = false)
    Long jointId;

    @Column(name = "login_id", nullable = false)
    String loginId;

    @Column(name = "share")
    Long share;

    @ManyToOne(targetEntity = JointCustomerProperty.class)
    @JoinColumn(referencedColumnName = "id")
    JointCustomerProperty jointCustomerProperty;

    @PrePersist
    @PreUpdate
    public void prePersist() {

        String prefix = "JT";
        String suffix = "";
        if (this.jointId != null) {
            suffix = String.format("%07d", id);
            loginId = prefix + suffix;
            loginId.toUpperCase();
        }
    }

}
