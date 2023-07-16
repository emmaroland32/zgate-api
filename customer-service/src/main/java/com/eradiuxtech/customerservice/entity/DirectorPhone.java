package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "director_phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DirectorPhone extends CoreEntity implements Serializable {

    @ManyToOne(targetEntity = Director.class, optional = false )
    @JoinColumn(referencedColumnName = "id", nullable = false)
    Director director;

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
