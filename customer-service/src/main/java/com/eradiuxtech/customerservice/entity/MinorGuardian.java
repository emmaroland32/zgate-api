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
@Table(name = "minor_guardians")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MinorGuardian extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = Title.class)
    private Title title;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "middle_name")
    String middleName;

    @Column(name = "email")
    String email;

    @OneToOne(targetEntity = MinorGuardianProperty.class, cascade = CascadeType.ALL)
    @JoinColumn( referencedColumnName = "id", nullable = false)
    private MinorGuardianProperty minorGuardianProperty;

    @OneToMany(mappedBy = "minorGuardian", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MinorGuardianPhone> phones;

    @OneToMany(mappedBy = "minorGuardian", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MinorGuardianAddress> addresses;
}