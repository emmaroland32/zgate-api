package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@Entity
@Table(name = "states")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class State extends CoreEntity implements Serializable {
    @Column(name = "name")
    @NotNull(message = "State name is required")
    protected String name;

    @NaturalId
    @Column(name = "code")
    @NotNull(message = "State code is required")
    protected String code;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @NotNull(message = "Country is required")
    @JoinColumn(name = "country_code", referencedColumnName = "code", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Country country;

    @Column(name = "description")
    protected String description;
}
