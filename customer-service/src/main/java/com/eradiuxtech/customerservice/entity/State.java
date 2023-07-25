package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "states")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class State extends CoreEntity implements Serializable {
    @Column(name = "name")
    protected String name;

    @Column(name = "code")
    protected String code;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    protected Country country;

    @Column(name = "description")
    protected String description;
}