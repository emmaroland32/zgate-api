package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "minor_guardian_phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MinorGuardianPhone extends CoreEntity implements Serializable {


    @ManyToOne(targetEntity = MinorGuardian.class)
    MinorGuardian minorGuardian;


    @ManyToOne(targetEntity = PhoneType.class)
    PhoneType phoneType;

    @Column(name = "number", unique = true)
    String number;

    @Column(name = "extension")
    String extension;

    @ManyToOne(targetEntity = Country.class)
    Country country;

    @Column(name = "area_code")
    String areaCode;

    @Column(name = "comment")
    String comment;
}
