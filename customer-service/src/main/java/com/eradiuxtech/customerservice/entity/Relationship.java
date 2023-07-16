package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "relationships")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Relationship extends CoreEntity implements Serializable {

    @Column(name = "name", unique = true)
    String name;
}
