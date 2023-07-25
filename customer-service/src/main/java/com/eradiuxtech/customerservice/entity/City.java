package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "cities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City extends CoreEntity implements Serializable {
    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private State state;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country country;

    @Column(name = "description")
    private String description;
}