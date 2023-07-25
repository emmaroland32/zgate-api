package com.eradiuxtech.zgate.systemservice.entity;


import com.eradiuxtech.zgate.systemservice.entity.core.CoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;



@Entity
@Table(name = "departments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department extends CoreEntity implements Serializable {

    private String name;

    @ManyToOne(optional = false)
    private Company company;

    private String description;

}
