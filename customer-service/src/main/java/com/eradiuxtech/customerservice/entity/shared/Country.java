package com.eradiuxtech.customerservice.entity.shared;

import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;


@Entity
@Table(name = "countries", uniqueConstraints = { @UniqueConstraint(name = "UniqueNameAndCodeAndPhoneCode", columnNames = { "name", "code", "phone_code" }) })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Country extends CoreEntity implements Serializable {

    @Column(name = "name", unique = true)
    @NotBlank(message = "Country name must not be empty")
    @NotNull(message = "Country name is required")
    protected String name;

    @NaturalId
    @Column(name = "code", unique = true)
    @NotBlank(message = "Country code must not be empty")
    @NotNull(message = "Country code is required")
    protected String code;

    @Column(name = "phone_code", unique = true)
    @NotBlank(message = "Country Phone Code must not be empty")
    @NotNull(message = "Country phone code is required")
    protected String phoneCode;

    @Column(name = "description")
    protected String description;
}
