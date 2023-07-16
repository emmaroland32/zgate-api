package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.io.Serializable;


@Entity
@Table(name = "cities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City extends CoreEntity implements Serializable {
    @Column(name = "name")
    @NotNull(message = "City name is required")
    protected String name;

    @NaturalId
    @Column(name = "code")
    @NotNull(message = "City code is required")
    protected String code;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull(message = "State is required")
    @JoinColumn(name = "state_code", referencedColumnName = "code", nullable = false)
    @JsonIgnore
    protected State state;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull(message = "Country is required")
    @JoinColumn(name = "country_code", referencedColumnName = "code", nullable = false)
    @JsonIgnore
    protected Country country;

    @Column(name = "description")
    protected String description;
}
