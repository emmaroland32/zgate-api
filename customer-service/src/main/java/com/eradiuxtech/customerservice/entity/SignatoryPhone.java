package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "signatory_phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignatoryPhone extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = Signatory.class, optional = false )
    @JoinColumn(nullable = false)
    Signatory signatory;

    @ManyToOne(targetEntity = PhoneType.class, optional = false)
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