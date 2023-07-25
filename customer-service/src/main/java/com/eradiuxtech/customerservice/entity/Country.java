package com.eradiuxtech.customerservice.entity;

import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "countries", uniqueConstraints = { @UniqueConstraint(name = "UniqueNameAndCodeAndPhoneCode", columnNames = { "name", "code", "phone_code" }) })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country extends CoreEntity implements Serializable {

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "phone_code", unique = true)
    private String phoneCode;

    @Column(name = "description")
    private String description;
}