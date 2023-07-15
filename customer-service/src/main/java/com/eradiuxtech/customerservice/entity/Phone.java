package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Phone extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = PhoneType.class, optional = false)
    PhoneType phoneType;

    @Column(name = "number", unique = true)
    String number;

    @Column(name = "extension")
    String extension;

    @Column(name = "country_code")
    Country countryCode;

    @Column(name = "area_code")
    String areaCode;

    @Column(name = "comment")
    String comment;
}
